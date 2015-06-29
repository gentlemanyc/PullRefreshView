package cc.core.pullrefresh;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * 
 * cc.core.pullrefresh.PullRefreshListView
 * 
 * @author YuanChao <br/>
 *         create at 2015年6月22日 下午3:16:01
 */
public class PullRefreshListView  extends LinearLayout implements
		AbsListView.OnScrollListener {

	private InternalListView listview;

	private View headerView;

	private int firstVisiableItem;

	private int headerHeight;

	private LinearLayout layoutBase;

	private int mLatPointId, mInitBaseY;

	private ImageView headerImg;
	private boolean once;

	private float mLastMotionY;
	private float mInitialMotionY;

	public InternalListView getListview() {
		return listview;
	}

	public PullRefreshListView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		layoutBase = new LinearLayout(context, attrs, defStyleAttr);
		listview = new InternalListView(context, attrs, defStyleAttr);
		initView(context);
	}

	public PullRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		listview = new InternalListView(context, attrs);
		layoutBase = new LinearLayout(context, attrs);
		initView(context);
	}

	public PullRefreshListView(Context context) {
		super(context);
		layoutBase = new LinearLayout(context);
		listview = new InternalListView(context);
		initView(context);
	}

	private void initView(Context context) {
		setOrientation(VERTICAL);
		headerView = LayoutInflater.from(context).inflate(
				R.layout.layout_header, null);
		headerImg = (ImageView) headerView.findViewById(R.id.header_img);
		measureHeight(headerView);
		listview.setOnScrollListener(this);
		layoutBase.setOrientation(VERTICAL);
		layoutBase.addView(headerView);
		layoutBase.addView(listview);
		addView(layoutBase);
		LayoutParams lp = (LayoutParams) layoutBase.getLayoutParams();
		lp.setMargins(0, -headerHeight, 0, 0);
		layoutBase.setLayoutParams(lp);
		getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {

					public void onGlobalLayout() {
						if (once) {
							once = false;
						} else
							return;
						int[] lc = new int[2];
						headerView.getLocationInWindow(lc);
					}

				});
	}

	private void measureHeight(View view) {
		int w = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		int h = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		headerHeight = view.getMeasuredHeight();
	}

	@Override
	public void onScrollStateChanged(AbsListView absListView, int i) {
	}

	@Override
	public void onScroll(AbsListView absListView, int i, int i1, int i2) {
		firstVisiableItem = i;
	}

	private int[] getLc(View view) {
		int[] lc = new int[2];
		view.getLocationInWindow(lc);
		return lc;
	}

	/**
	 * 内部的ListView cc.core.pullrefresh.InternalListView
	 * 
	 * @author YuanChao <br/>
	 *         create at 2015年6月19日 下午3:46:37
	 */
	public class InternalListView extends ListView {

		private float mDis;
		private float mScrollY;// 第一次按下时的y
		private float mInitY;
		private float mHeaderY;
		private boolean headerScrolling;
		private boolean isFirst = true;
		private boolean isPulling;

		public InternalListView(Context context) {
			super(context);
			innerInit(context);
		}

		public InternalListView(Context context, AttributeSet attrs) {
			super(context, attrs);
			innerInit(context);
		}

		public InternalListView(Context context, AttributeSet attrs,
				int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			innerInit(context);
		}

		private void innerInit(Context context) {
			getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {

						@Override
						public void onGlobalLayout() {

							if (!isFirst)
								return;
							isFirst = !isFirst;
							if (getCount() > 0) {
								mInitY = getLc(getChildAt(0))[1];
							}
						}
					});
			// 不显示顶部和底部拖拽时的阴影
			// if (Integer.parseInt(Build.VERSION.SDK) >= 9) {
			// this.setOverScrollMode(View.OVER_SCROLL_NEVER);
			// }
		}

		public boolean onTouchEvent(MotionEvent ev) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mLatPointId = ev.getPointerId(0);
				mScrollY = ev.getY(ev.findPointerIndex(mLatPointId));

				mLastMotionY = mInitialMotionY = ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				mDis = ev.getY(ev.findPointerIndex(mLatPointId)) - mScrollY;
				// doMove(ev);

				mLastMotionY = ev.getY();
				doMove(ev);
				break;
			case MotionEvent.ACTION_UP:
				if (isPulling) {
					startHeadAnim();
					isPulling = false;
				}
				break;
			}
			if (isPulling || headerScrolling)
				return true;
			return super.onTouchEvent(ev);
		}

		int newScrollValue;

		private void doMove(MotionEvent ev) {
			final float initialMotionValue, lastMotionValue;

			initialMotionValue = mInitialMotionY;
			lastMotionValue = mLastMotionY;

			setPressed(false);
			newScrollValue = Math.round(Math.min(initialMotionValue
					- lastMotionValue, 0) / 2.0f);

			if (readyPull(ev)) {
				updateHead();
				ViewHelper.setTranslationY(layoutBase, -newScrollValue);
				ViewHelper.setRotation(headerImg, -newScrollValue);
				// layoutBase.scrollTo(0, newScrollValue);
				invalidate();
			}
		}

		private void updateHead() {
			setPressed(false);
			mHeaderY = getLc(getChildAt(0))[1];
			if ((mHeaderY - mInitY) >= headerHeight) {
				((TextView) headerView.findViewById(R.id.pr_title))
						.setText("释放刷新");
			} else {
				((TextView) headerView.findViewById(R.id.pr_title))
						.setText("下拉刷新");
			}
		}

		private boolean readyPull(MotionEvent ev) {
			if (isPulling)
				return true;
			int[] lc = new int[2];
			getChildAt(0).getLocationInWindow(lc);
			if (mDis > 0 && firstVisiableItem == 0 && mInitY == lc[1]) {
				Log.i("CC", "mDisValue:" + mDis + " lc[1]:" + lc[1]
						+ ",mInitY:" + mInitY);
				mInitialMotionY = ev.getY();
				newScrollValue = 1;
				isPulling = true;
				return true;
			} else {
				isPulling = false;
				return false;
			}
		}

		private void startHeadAnim() {
			Log.d("CC", "mInitY:" + mInitY + ",Y:" + ",headerHeight:"
					+ headerHeight);
			ObjectAnimator anim = ObjectAnimator.ofFloat(layoutBase,
					"translationY", 0.0f).setDuration(300);

			anim.addListener(new Animator.AnimatorListener() {
				public void onAnimationStart(Animator animator) {
					headerScrolling = true;
				}

				public void onAnimationEnd(Animator animator) {
					headerScrolling = false;
				}

				public void onAnimationCancel(Animator animator) {
				}

				public void onAnimationRepeat(Animator animator) {
				}
			});
			anim.addUpdateListener(new AnimatorUpdateListener() {

				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					ViewHelper.setRotation(headerImg,
							(Float) animation.getAnimatedValue());
					updateHead();
				}
			});
			anim.start();
		}
	}
}
