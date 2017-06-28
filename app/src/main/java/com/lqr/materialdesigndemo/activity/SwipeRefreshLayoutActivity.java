package com.lqr.materialdesigndemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lqr.materialdesigndemo.R;

import java.util.ArrayList;
import java.util.List;


public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSrl;
    private ListView mListView;
    private int number = 0;
    private List<String> mData = new ArrayList<>();
    private BaseAdapter mAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mAdapter.notifyDataSetChanged();
            mSrl.setRefreshing(false);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mListView = (ListView) findViewById(R.id.lv);
        setSwipeRefreshLayout();
        setListView();
    }

    private void setSwipeRefreshLayout() {
        //设置 SwipeRefreshLayout 的尺寸
        mSrl.setSize(SwipeRefreshLayout.LARGE);
        //设置 SwipeRefreshLayout 刷新时的颜色切换（可以有无数种）
        mSrl.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN);
        //设置 SwipeRefreshLayout 的背景色
        mSrl.setBackgroundColor(Color.GRAY);
        //设置 SwipeRefreshLayout 的下拉距离
        mSrl.setDistanceToTriggerSync(100);
        //设置 SwipeRefreshLayout 正在刷新监听
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (int i = 0; i < 10; i++) {
                    mData.add(0, (++number) + "");
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(0);
                    }
                }, 3000);
            }
        });
    }

    private void setListView() {
        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mData.size();
            }

            @Override
            public String getItem(int position) {
                return mData.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(parent.getContext());
                AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 100);
                tv.setLayoutParams(params);
                tv.setBackgroundColor(Color.WHITE);
                tv.setText(getItem(position));
                tv.setGravity(Gravity.CENTER);
                return tv;
            }
        };
        mListView.setAdapter(mAdapter);
    }
}
