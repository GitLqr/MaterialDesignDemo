package com.lqr.materialdesigndemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lqr.materialdesigndemo.adapter.MyAdapter;
import com.lqr.materialdesigndemo.data.Cheeses;
import com.lqr.materialdesigndemo.decoration.MyDecorationThree;
import com.lqr.materialdesigndemo.utils.PinyinUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 RecyclerView的侧边字母提示
 */
public class RecyclerVIewDecorationActivityThree extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRv;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initData();
        initView();
        setRecyclerView();
    }

    private void initView() {
        MyDecorationThree decorationThree = new MyDecorationThree(this, mData);
        mRv.addItemDecoration(decorationThree);
    }

    private void initData() {
        mData = Arrays.asList(Cheeses.NAMES);
        Collections.sort(mData, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return PinyinUtils.getPinyin(o1).compareTo(PinyinUtils.getPinyin(o2));
            }
        });
    }

    private void setRecyclerView() {
        mMyAdapter = new MyAdapter(mData);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mMyAdapter);
    }
}
