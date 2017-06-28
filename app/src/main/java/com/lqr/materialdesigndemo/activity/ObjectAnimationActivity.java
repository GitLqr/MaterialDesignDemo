package com.lqr.materialdesigndemo.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;

import com.lqr.materialdesigndemo.R;

/**
 * @创建者 CSDN_LQR
 * @描述 属性动画
 */
public class ObjectAnimationActivity extends AppCompatActivity {

    private ImageView mIvPic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        mIvPic = (ImageView) findViewById(R.id.ivPic);
        mIvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }

    // 单个属性动画
//    private void startAnimation() {
//        ObjectAnimator oa = ObjectAnimator.ofFloat(mIvPic, "translationX", 0, 400);
//        oa.setDuration(1000);
//        oa.start();
//    }

    // 多个属性动画一起（方法1）
//    private void startAnimation() {
//        ValueAnimator va = ValueAnimator.ofFloat(0, 400);
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
////                animation.getAnimatedFraction();// 百分比
////                animation.getAnimatedValue();// 0-400（类型由ValueAnimator.ofXXX决定）
////                animation.getTotalDuration();// 动画总时长（要求api>=24）
////                animation.getCurrentPlayTime();// 当前播放时间
//                float value = (float) animation.getAnimatedValue();
//                mIvPic.setAlpha(value / 400);// 0~1
//                mIvPic.setScaleX(value / 400);// 0~1
//                mIvPic.setScaleY(value / 400);// 0~1
//            }
//        });
//        va.setDuration(1000);
//        va.start();
//    }

    // 多个属性动画一起（方法2）
//    private void startAnimation() {
//        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0, 1);
//        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
//        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
//        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(mIvPic, alpha, scaleX, scaleY);
//        oa.setDuration(500);
//        oa.start();
//    }

    // 多个属性动画一起（方法3）
//    private void startAnimation() {
//        ObjectAnimator alpha = ObjectAnimator.ofFloat(mIvPic, "alpha", 0, 1);
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mIvPic, "scaleX", 0, 1);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mIvPic, "scaleY", 0, 1);
//        AnimatorSet as = new AnimatorSet();
//
//        // 设置同时执行的动画
//        as.playTogether(alpha, scaleX, scaleY);
//        // 设置依次执行的动画
////        as.playSequentially(alpha,scaleX,scaleY);
//
//        // 设置单独的动画时间
//        alpha.setDuration(100);
//        scaleX.setDuration(400);
//        scaleY.setDuration(700);
//        // 设置统一的动画时间
////        as.setDuration(500);
//
//        as.start();
//    }

    // 抛物线
//    private void startAnimation() {
//        final ValueAnimator va = new ValueAnimator();
//        va.setDuration(1000);
//        va.setObjectValues(new PointF(0, 0));
//        va.setEvaluator(new TypeEvaluator() {
//            @Override
//            public Object evaluate(float fraction, Object startValue, Object endValue) {
//                float t = fraction * (va.getDuration() / 1000); // 注意时间单位应该是秒
//                float v = getResources().getDisplayMetrics().widthPixels / (va.getDuration() / 1000) / 2; // x轴速度
//                float g = v * 2;// y轴重力加速度（9.8太慢了）
////                float g = 9.8f
//                // x：匀速（v*t）
//                // y：加速（1/2*g*t*t）;
//                float x = v * t;
//                float y = 0.5f * g * t * t;
//                Log.e("CSDN_LQR", "(" + x + "," + y + ")");
//                return new PointF(x, y);
//            }
//        });
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                PointF pointF = (PointF) animation.getAnimatedValue();
//                mIvPic.setX(pointF.x);
//                mIvPic.setY(pointF.y);
//            }
//        });
//        va.start();
//    }

    // 插值器
    private void startAnimation() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(mIvPic, "translationY", 0, 400);
        // 加速减速插值器
//        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        // 加速插值器
//        AccelerateInterpolator interpolator = new AccelerateInterpolator(0.8f);
        // 回荡秋千插值器
        AnticipateInterpolator interpolator = new AnticipateInterpolator(0.8f);
        oa.setInterpolator(interpolator);
        oa.start();
    }


}
