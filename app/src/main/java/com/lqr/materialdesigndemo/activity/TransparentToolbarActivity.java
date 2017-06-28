package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lqr.materialdesigndemo.R;
import com.lqr.materialdesigndemo.data.Cheeses;

/**
 * @创建者 CSDN_LQR
 * @描述 仿微博上滑时Toolbar透明
 */
public class TransparentToolbarActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private Toolbar mToolbar;
    private ListView mLv;
    private int mScreenHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        Log.e("CSDN_LQR", "screenHeight = " + mScreenHeight);
        mLv = (ListView) findViewById(R.id.lv);
        mLv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Cheeses.NAMES));
        mLv.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View childAt = view.getChildAt(0);
        if (childAt == null)
            return;
        int scrollY = firstVisibleItem * childAt.getHeight() - childAt.getTop();
        if (scrollY <= (mScreenHeight / 3f)) {
            float alpha = 1f - (scrollY / (mScreenHeight / 3f));
            Log.e("CSDN_LQR", "scrollY = " + scrollY);
            Log.e("CSDN_LQR", "alpha = " + alpha);
            mToolbar.setAlpha(alpha);
        }
    }
}
