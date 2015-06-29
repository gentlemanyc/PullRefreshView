package cc.core.pullrefresh.extra;

import cc.core.pullrefresh.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * cc.core.pullrefresh.extra.FooterLayout
 * @author YuanChao <br/>
 * create at 2015年6月22日 下午1:43:59
 */
public class FooterLayout extends LinearLayout implements ILayoutBase {
	private static final String TAG = "FooterLayout";

	public FooterLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context, attrs);
	}

	public FooterLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public FooterLayout(Context context) {
		super(context);
		initView(context, null);
	}

	@Override
	public void initView(Context context, AttributeSet attrs) {
		LayoutInflater.from(context).inflate(R.layout.layout_footer, this);
	}

	@Override
	public void updateTitle() {
	}

	@Override
	public void setAnimValue(float value) {
	}

}
