package cc.core.pullrefresh.extra;

import android.content.Context;
import android.util.AttributeSet;

/**
 * ClassName:ILayoutBase <br/>
 * Date: 2015年6月22日 下午11:47:25 <br/>
 * 
 * @author YuanChao
 */
public interface ILayoutBase {
	public void initView(Context context,AttributeSet attr);
	public void updateTitle();
	public void setAnimValue(float value);
}
