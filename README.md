#PullRefreshListView
###运行截图：
![输入图片说明](http://git.oschina.net/uploads/images/2015/0624/124319_5f69e388_134008.png "下拉刷新")
![输入图片说明](http://git.oschina.net/uploads/images/2015/0624/124340_ca3e2075_134008.png "上拉加载更多")
### 最低版本要求：
android 2.3.3(API level=10)
### 需要导入的jar包：
nineoldandroids-2.4.0.jar
android-support-v4.jar
### 使用方法：
1.在xml中字义：

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res/cc.core.pullrefresh"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="cc.core.pullrefresh.MainActivity" >

    <cc.core.pullrefresh.PRListView
        android:id="@+id/listview"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:dividerHeight="10dp"
        app:header_img="@drawable/jb"
        app:header_textSize="14sp" />

</LinearLayout>
```

2.在java代码中使用：

```
    listview = (PRListView) findViewById(R.id.listview);
        listview.setMode(PullRefreshAbsListViewBase.Mode.PULL_FROM_BOTTOM);
        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data));
        listview.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onHeaderRefresh() {
            //刷新操作
            }
            @Override
            public void onFooterRefresh() {
            //加载更多
            }
        });
```
###注意事项
1. 代码的字符编码为utf-8

### 更多API请你查看查看源码，欢迎你联系我给我提出bug和意见，此项目继续开发中


