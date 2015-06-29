package cc.core.pullrefresh;

import android.view.View;

/**
 * cc.core.pullrefresh.OnRefreshListener
 * @author YuanChao <br/>
 *  create at 2015年6月22日 下午1:37:55
 */
public abstract class OnRefreshListener {
	public abstract void onHeaderRefresh();

	public void onFooterRefresh() {
	};

	public void onHeaderRefresh(View view) {
	};

	public void onFooterRefresh(View view) {
	};
}
