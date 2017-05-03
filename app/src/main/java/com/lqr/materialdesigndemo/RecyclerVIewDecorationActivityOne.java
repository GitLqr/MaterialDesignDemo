package com.lqr.materialdesigndemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lqr.materialdesigndemo.adapter.MyAdapter;
import com.lqr.materialdesigndemo.decoration.MyDecorationOne;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 RecyclerView的分割线
 */
public class RecyclerVIewDecorationActivityOne extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRv;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initView();
        initData();
        setRecyclerView();
    }

    private void initView() {
        MyDecorationOne decorationOne = new MyDecorationOne(this, LinearLayoutManager.VERTICAL);
        mRv.addItemDecoration(decorationOne);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("item " + i);
        }
    }

    private void setRecyclerView() {
        mMyAdapter = new MyAdapter(mData);
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRv.setAdapter(mMyAdapter);
    }
}
