package com.lqr.materialdesigndemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @创建者 CSDN_LQR
 * @描述 悬浮动作按钮、水波纹、按压效果
 * <p>
 * app:elevation="10dp" 阴影效果（值越大，效果越明显）
 * app:fabSize="normal"按钮大小（auto、normal、mini）
 * 需要注意的是FloatingActionButton需要在4.x上默认存在margin值(默认16dp)，所以需要进行适配，让5.x以上的设备margin为16dp，4.x的设备margin为0dp
 * <p>
 * 水波纹：
 * app:pressedTranslationZ="10dp"
 * <p>
 * 按压效果：
 * android:clickable="true"
 * app:rippleColor="#ff0"
 */
public class FloatingActionButtonActivity extends AppCompatActivity {

    private boolean isReverse = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
    }

    /**
     * 点击FloatingActionButton时旋转
     */
    public void rotate(View view) {
        int rotate = isReverse ? 180 : -180;
        ObjectAnimator.ofFloat(view, "rotation", 0.0f, rotate)
                .setDuration(400).start();
        isReverse = !isReverse;
    }
}
