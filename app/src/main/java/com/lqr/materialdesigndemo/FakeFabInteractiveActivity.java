package com.lqr.materialdesigndemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;

import com.lqr.materialdesigndemo.adapter.MyAdapter;
import com.lqr.materialdesigndemo.data.Cheeses;
import com.lqr.materialdesigndemo.listener.FakeFabActionListner;
import com.lqr.materialdesigndemo.listener.FakeFabScrollListener;

import java.util.Arrays;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 仿Fab与RecyclerView的交互动画
 */
public class FakeFabInteractiveActivity extends AppCompatActivity implements FakeFabActionListner {

    private List<String> mData = Arrays.asList(Cheeses.sCheeseStrings);
    private Toolbar mToolbar;
    private RecyclerView mRv;
    private ImageButton mFab;
    private int mToolbarBottomMargin;
    private int mFabBottomMargin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_fab_interactive);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mFab = (ImageButton) findViewById(R.id.fab);


        mToolbarBottomMargin = ((ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams()).bottomMargin;
        mFabBottomMargin = ((ViewGroup.MarginLayoutParams) mFab.getLayoutParams()).bottomMargin;

        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(new MyAdapter(mData));
        mRv.addOnScrollListener(new FakeFabScrollListener(this));
    }

    @Override
    public void hide() {
        mToolbar.animate().translationY(-(mToolbar.getHeight() + mToolbarBottomMargin)).setInterpolator(new AccelerateInterpolator(3));
        mFab.animate().translationY(mFab.getHeight() + mFabBottomMargin).setInterpolator(new AccelerateInterpolator(3));
    }

    @Override
    public void show() {
        mToolbar.animate().translationY(0).setInterpolator(new AccelerateInterpolator(3));
        mFab.animate().translationY(0).setInterpolator(new AccelerateInterpolator(3));
    }
}
