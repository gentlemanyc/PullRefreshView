package cc.core.pullrefresh;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	private PRListView listview;
	private List<String> data;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			listview.onRefreshComplete();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data = new ArrayList<String>();
		addData();
		listview = (PRListView) findViewById(R.id.listview);
		listview.setMode(PullRefreshAbsListViewBase.Mode.PULL_FROM_BOTTOM);
		listview.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data));
		listview.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onHeaderRefresh() {
				Toast.makeText(MainActivity.this, "RefreshIng....",
						Toast.LENGTH_SHORT).show();
				handler.sendEmptyMessageDelayed(0, 3000);
			}
			@Override
			public void onFooterRefresh() {
				Toast.makeText(MainActivity.this, "Footer Refreshing...",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	

	private void addData() {
		for (int i = 0; i < 20; i++) {
			data.add("just it!man" + i);
		}
	}
}
