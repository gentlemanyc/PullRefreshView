package cc.core.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

/**
 * ClassName:PullRefreshBase <br/>
 * Date: 2015年6月22日 下午3:37:57 <br/>
 * 
 * @author YuanChao
 */
public abstract class PullRefreshBase<T extends View> extends LinearLayout implements IPullBase<T> {

	public PullRefreshBase(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public PullRefreshBase(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullRefreshBase(Context context) {
		super(context);
	}

	@Override
	public T getRefreshView() {
		return null;
	}

	@Override
	public void setEmptyView(View view) {
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
	}

	@Override
	public void startHeadAnim(float... value) {
	}

	@Override
	public boolean readyPull(MotionEvent ev) {
		return false;
	}

	public int[] getLc(View view) {
		int[] lc = new int[] { 0, 0 };
		if (view != null)
			view.getLocationInWindow(lc);
		return lc;
	}

	@Override
	public void showHeaderRefresh() {
	}
}
