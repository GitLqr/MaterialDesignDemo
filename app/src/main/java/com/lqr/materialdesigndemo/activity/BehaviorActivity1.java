package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lqr.materialdesigndemo.R;


public class BehaviorActivity1 extends AppCompatActivity {

    private TextView mTv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior1);
        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(mTv1, 200);
            }
        });
    }
}
