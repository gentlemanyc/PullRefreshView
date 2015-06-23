package cc.core.pullrefresh.extra;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import cc.core.pullrefresh.R;
import android.R.attr;
import android.R.raw;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ClassName:HeaderLayout <br/>
 * Date: 2015年6月22日 下午11:45:00 <br/>
 * 
 * @author YuanChao
 */
public class HeaderLayout extends LinearLayout implements ILayoutBase {
	private static final String TAG = "HeaderLayout";
	private TextView refreshTitle;
	private ImageView headerImg;
	private ObjectAnimator rotation;
	private int textColor;
	private float textSize = 14;
	private int imgSrc;

	public HeaderLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context, attrs);
	}

	public HeaderLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public HeaderLayout(Context context) {
		super(context);
		initView(context, null);
	}

	@Override
	public void initView(Context context, AttributeSet attrs) {

		LayoutInflater.from(context).inflate(R.layout.layout_header, this);
		refreshTitle = (TextView) findViewById(R.id.pr_title);

		headerImg = (ImageView) findViewById(R.id.header_img);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		if (attrs != null) {
			TypedArray ta = getContext().obtainStyledAttributes(attrs,
					R.styleable.PullRefresh);
			textColor = ta.getColor(R.styleable.PullRefresh_header_textColor,
					Color.BLACK);
			textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
					textSize, dm);
			textSize = ta.getDimensionPixelSize(
					R.styleable.PullRefresh_header_textSize, (int) textSize);// 默认字体大小14sp
			imgSrc = ta.getResourceId(R.styleable.PullRefresh_header_img,
					R.drawable.ic_launcher);
			ta.recycle();
			refreshTitle.setTextColor(textColor);
			refreshTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
			headerImg.setImageResource(imgSrc);
		}

		rotation = ObjectAnimator.ofFloat(headerImg, "rotation", 90f,
				360f + 90f).setDuration(2000);
		rotation.setRepeatCount(Integer.MAX_VALUE);
		rotation.setInterpolator(new LinearInterpolator());
	}

	@Override
	public void updateTitle() {
	}

	public void setRefreshing() {
		refreshTitle.setText("正在刷新");
		rotation.start();
	}

	public void setPullToRefresh() {
		rotation.cancel();
		refreshTitle.setText("下拉刷新");
	}

	public void setReleaseToRefresh() {
		rotation.cancel();
		refreshTitle.setText("释放刷新");
	}

	@Override
	public void setAnimValue(float value) {
		rotation.cancel();
		ViewHelper.setRotation(headerImg, value);
	}

}
