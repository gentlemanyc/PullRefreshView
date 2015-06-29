package cc.core.pullrefresh;

import cc.core.pullrefresh.PullRefreshAbsListViewBase;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

/**
 * PRExpandableListView.java</br>
 * classes : com.example.testhttp.PRExpandableListView</br>
 * @author YuanChao
 * Create at 2015年6月29日 上午11:40:35
 */
public class PRExpandableListView extends PullRefreshAbsListViewBase<ExpandableListView> {

	public PRExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public PRExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PRExpandableListView(Context context) {
		super(context);
	}

	@Override
	protected void initRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
		if (attrs != null) {
			listview = new InnerExpandableListView(context, attrs, defStyleAttr);
		} else {
			listview = new InnerExpandableListView(context);
		}
	}

	@Override
	public void initFooter(Context context, AttributeSet attr) {
		super.initFooter(context, attr);
		listview.addFooterView(footerView);
	}

	public void setAdapter(ExpandableListAdapter adapter) {
		listview.setAdapter(adapter);
		innerInit(getContext());
	}

	class InnerExpandableListView extends ExpandableListView {

		public InnerExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
		}

		public InnerExpandableListView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public InnerExpandableListView(Context context) {
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
