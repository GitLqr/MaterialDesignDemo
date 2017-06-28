package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lqr.materialdesigndemo.R;
import com.lqr.materialdesigndemo.adapter.ItemTouchHelperAdapter;
import com.lqr.materialdesigndemo.callback.MyItemTouchHelperCallback;
import com.lqr.materialdesigndemo.data.Cheeses;
import com.lqr.materialdesigndemo.listener.ItemDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ItemTouchHelperActivity extends AppCompatActivity implements ItemDragListener {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRv;
    private ItemTouchHelperAdapter mAdapter;
    private MyItemTouchHelperCallback mCallback;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initData();
        setRecyclerView();
    }

    private void initData() {
        for (int i = 0; i < Cheeses.sCheeseStrings.length; i++) {
            mData.add(Cheeses.sCheeseStrings[i]);
        }
        Collections.sort(mData, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void setRecyclerView() {
        mAdapter = new ItemTouchHelperAdapter(mData, this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);

        mCallback = new MyItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRv);
    }

    @Override
    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
