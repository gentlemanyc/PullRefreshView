package cc.core.pullrefresh.extra;

import android.content.Context;
import android.util.AttributeSet;

/**
 * cc.core.pullrefresh.extra.ILayoutBase
 * @author YuanChao <br/>
 * create at 2015年6月22日 下午1:44:13
 */
public interface ILayoutBase {
	public void initView(Context context,AttributeSet attr);
	public void updateTitle();
	public void setAnimValue(float value);
}
