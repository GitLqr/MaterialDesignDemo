package com.lqr.materialdesigndemo.listener;

import android.support.v7.widget.RecyclerView;

/**
 * @创建者 CSDN_LQR
 * @描述 仿Fab与RecyclerView的交互动画的RecyclerView滚动监听
 */
public class FakeFabScrollListener extends RecyclerView.OnScrollListener {

    private static final int THRESHOLD = 20;
    private int distance = 0;
    private boolean visiable = true;
    private FakeFabActionListner mFakeFabActionListner;

    public FakeFabScrollListener(FakeFabActionListner fakeFabActionListner) {
        mFakeFabActionListner = fakeFabActionListner;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (distance > THRESHOLD && visiable) {
            // 隐藏
            distance = 0;
            visiable = false;
            if (mFakeFabActionListner != null)
                mFakeFabActionListner.hide();
        } else if (distance < -THRESHOLD && !visiable) {
            // 显示
            distance = 0;
            visiable = true;
            if (mFakeFabActionListner != null)
                mFakeFabActionListner.show();
        }
        if (visiable && dy > 0 || (!visiable) && dy < 0)
            distance += dy;
    }
}
