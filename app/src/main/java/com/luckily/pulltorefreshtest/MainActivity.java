package com.luckily.pulltorefreshtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView lv_refresh;
    private ArrayList<String[]> datas;
    private String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private String[] addData = {"111", "112", "113", "114", "115", "116", "117", "118", "119"};
    private RefreshAdapter refreshAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_refresh = (PullToRefreshListView) findViewById(R.id.lv_refresh);

        datas = new ArrayList<>();
        datas.add(data);

        refreshAdapter = new RefreshAdapter(this, datas);
        lv_refresh.setAdapter(refreshAdapter);
        lv_refresh.setMode(PullToRefreshBase.Mode.BOTH);//下拉和上拉都支持;
        lv_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
            }
        });

        ILoadingLayout startLabels = lv_refresh.getLoadingLayoutProxy(true, false);//两个参数，第一个true，第二个false只修改下拉时的文本

        startLabels.setPullLabel("继续下拉...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在刷新...");// 刷新时
        startLabels.setReleaseLabel("松开刷新...");// 下来达到一定距离时，显示的提示
        ILoadingLayout endLabels = lv_refresh.getLoadingLayoutProxy(false, true);//两个参数，第一个false，第二个true只修改下拉时的文本
        endLabels.setPullLabel("你上拉吧...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在加载...");// 刷新时
        endLabels.setReleaseLabel("松开加载更多...");// 下来达到一定距离时，显示的提示

        lv_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
                refreshAdapter.notifyDataSetChanged();
                if (lv_refresh.isRefreshing()) {
                    lv_refresh.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
                if (lv_refresh.isRefreshing()) {
                    lv_refresh.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                }
            }

        });
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                lv_refresh.onRefreshComplete();
        }
    }

}
//            if (lv_refresh.isRefreshing()) {
//                datas.add(addData);
//                refreshAdapter.notifyDataSetChanged();
//                lv_refresh.setMode(PullToRefreshBase.Mode.BOTH);
//            }
