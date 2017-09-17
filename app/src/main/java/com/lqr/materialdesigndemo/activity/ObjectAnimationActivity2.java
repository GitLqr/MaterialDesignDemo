package com.lqr.materialdesigndemo.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lqr.materialdesigndemo.R;

/**
 * @创建者 CSDN_LQR
 * @描述 属性动画综合应用
 */
public class ObjectAnimationActivity2 extends AppCompatActivity {

    private View mView1;
    private View mView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation2);
        mView1 = findViewById(R.id.view_1);
        mView2 = findViewById(R.id.view_2);

    }

    public void startFirstAnimation(View view) {
        // 翻转动画
        ObjectAnimator rotationXAnimation = ObjectAnimator.ofFloat(mView1, "rotationX", 0f, 25f);
        // 透明动画
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mView1, "alpha", 1f, 0.5f);
        // 缩放动画
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(mView1, "scaleX", 1f, 0.8f);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(mView1, "scaleY", 1f, 0.8f);
        // 位移动画
        ObjectAnimator translationYAnimation = ObjectAnimator.ofFloat(mView1, "translationY", 0f, -0.1f * mView1.getHeight());
        // 翻转动画
        ObjectAnimator rerotationXAnimation = ObjectAnimator.ofFloat(mView1, "rotationX", 25f, 0f);
        rerotationXAnimation.setStartDelay(200);
        // 位移动画
        ObjectAnimator translationYAnimation2 = ObjectAnimator.ofFloat(mView2, "translationY", mView2.getHeight(), 0);
        translationYAnimation2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mView2.setVisibility(View.VISIBLE);
            }
        });

        AnimatorSet as = new AnimatorSet();
        as.playTogether(rotationXAnimation, alphaAnimation, scaleXAnimation, scaleYAnimation, translationYAnimation, rerotationXAnimation, translationYAnimation2);
        as.setDuration(200);
        as.start();
    }

    public void startSecondAnimation(View view) {
        ObjectAnimator rotationXAnimation = ObjectAnimator.ofFloat(mView1, "rotationX", 0f, 25f);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mView1, "alpha", 0.5f, 1f);
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(mView1, "scaleX", 0.8f, 1f);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(mView1, "scaleY", 0.8f, 1f);
        ObjectAnimator translationYAnimation = ObjectAnimator.ofFloat(mView1, "translationY", -0.1f * mView1.getHeight(), 0f);
        ObjectAnimator rerotationXAnimation = ObjectAnimator.ofFloat(mView1, "rotationX", 25f, 0f);
        rerotationXAnimation.setStartDelay(200);
        ObjectAnimator translationYAnimation2 = ObjectAnimator.ofFloat(mView2, "translationY", 0, mView2.getHeight());
        translationYAnimation2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView2.setVisibility(View.INVISIBLE);
            }
        });

        AnimatorSet as = new AnimatorSet();
        as.playTogether(rotationXAnimation, alphaAnimation, scaleXAnimation, scaleYAnimation,
                translationYAnimation,
                rerotationXAnimation, translationYAnimation2);
        as.setDuration(200);
        as.start();
    }
}
