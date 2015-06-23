package cc.core.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * ClassName:PRListView <br/>
 * Date: 2015年6月22日 下午4:06:52 <br/>
 * 下拉刷新ListView，ListView的实现{@link InnerListView}
 * 
 * @author YuanChao
 */
public class PRListView extends PullRefreshAbsListViewBase<ListView> {
	private static final String TAG = "PRListView";

	public PRListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public PRListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PRListView(Context context) {
		super(context);
	}

	@Override
	protected void initRefreshView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		if (attrs == null && defStyleAttr == 0)
			listview = new InnerListView(context);
		else if (attrs != null && defStyleAttr == 0) {
			listview = new InnerListView(context, attrs);
		} else {
			listview = new InnerListView(context, attrs, defStyleAttr);
		}
	}

	@Override
	public void initFooter(Context context, AttributeSet attr) {
		super.initFooter(context, attr);
		listview.addFooterView(footerView);
	}

	/**
	 * 内部ListView的实现,继承ListView</br> cc.core.pullrefresh.InnerListView
	 * 
	 * @author YuanChao <br/>
	 *         create at 2015年6月22日 下午10:55:53
	 */
	class InnerListView extends ListView {

		public InnerListView(Context context, AttributeSet attrs,
				int defStyleAttr) {
			super(context, attrs, defStyleAttr);
		}

		public InnerListView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public InnerListView(Context context) {
			super(context);
		}

		@Override
		public boolean onTouchEvent(MotionEvent ev) {
			if (childTouchEvnet(ev))
				return true;
			else
				return super.onTouchEvent(ev);
		}

	}

}
