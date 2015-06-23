package cc.core.pullrefresh;

import android.view.View;

/**
 * ClassName:OnRefreshListener <br/>
 * Date: 2015年6月22日 下午3:11:47 <br/>
 * 
 * @author YuanChao
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
