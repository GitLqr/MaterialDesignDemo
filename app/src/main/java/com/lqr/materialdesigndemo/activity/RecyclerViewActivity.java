package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lqr.materialdesigndemo.R;
import com.lqr.materialdesigndemo.adapter.MyAdapter;
import com.lqr.materialdesigndemo.adapter.MyStaggeredAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewActivity extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRv;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initData();
        setRecyclerView();
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("item " + i);
        }
    }

    private void setRecyclerView() {
        mMyAdapter = new MyAdapter(mData);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mMyAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.linear_ver:
                mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                mRv.setAdapter(mMyAdapter);
                break;
            case R.id.linear_hor:
                mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                mRv.setAdapter(mMyAdapter);
                break;
            case R.id.grid_ver:
                mRv.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
                mRv.setAdapter(mMyAdapter);
                break;
            case R.id.grid_hor:
                mRv.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false));
                mRv.setAdapter(mMyAdapter);
                break;
            case R.id.staggered_ver:
                mRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                mRv.setAdapter(new MyStaggeredAdapter(mData));
                break;
            case R.id.staggered_hor:
                mRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
                mRv.setAdapter(new MyStaggeredAdapter(mData));
                break;
        }
        return true;
    }
}
