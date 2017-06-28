package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.lqr.materialdesigndemo.R;

/**
 * @创建者 CSDN_LQR
 * @描述 LinearLayoutCompat可以给子控件添加分隔线
 */
public class LinearLayoutCompatActivity extends AppCompatActivity {

    private LinearLayoutCompat mLlc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_compat);
        mLlc = (LinearLayoutCompat) findViewById(R.id.llc);
    }

    public void beginning(View view) {
        mLlc.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_BEGINNING);
    }

    public void middle(View view) {
        mLlc.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_MIDDLE);
    }

    public void end(View view) {
        mLlc.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_END);
    }

    public void beginning_middle(View view) {
        mLlc.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_BEGINNING | LinearLayoutCompat.SHOW_DIVIDER_MIDDLE);

    }

    public void end_middle(View view) {
        mLlc.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_MIDDLE | LinearLayoutCompat.SHOW_DIVIDER_END);

    }
}
