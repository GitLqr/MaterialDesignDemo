package com.lqr.materialdesigndemo.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lqr.materialdesigndemo.R;

/**
 * @创建者 CSDN_LQR
 * @描述 沉浸式
 */
public class ImmersiveActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private View mNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFlags();
        setContentView(R.layout.activity_immersive);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNav = findViewById(R.id.nav);

        setImmersive(mToolbar, mNav, Color.parseColor("#D33A31"));
    }

    private void addFlags() {
        // 4.4
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            // 透明状态栏、透明底部导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void setImmersive(View toolbar, View nav, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 5.0 以上自带沉浸式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 设置状态栏与导航栏的颜色
                getWindow().setStatusBarColor(color);
                getWindow().setNavigationBarColor(color);
            }
            // 4.4
            else {
                // 设置 Toolbar 高度（多出一个状态栏的高度）
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
                int statusBarHeight = getStatusBarHeight();
                params.height += statusBarHeight;
                toolbar.setLayoutParams(params);

                // 设置设置Toolbar顶部内间距（一个状态栏的高度）
                toolbar.setPadding(toolbar.getPaddingLeft(), toolbar.getPaddingTop() + statusBarHeight, toolbar.getPaddingRight(), toolbar.getPaddingBottom());

                // 设置底部导航栏的高度及背景色
                if (nav != null && hasNavigationBar()) {
                    LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) nav.getLayoutParams();
                    p.height += getNavigationBarHeight();
                    nav.setLayoutParams(p);
                    nav.setBackgroundColor(color);
                }
            }
        }
    }

    private int getStatusBarHeight() {
        return getSystemComponentDimen("status_bar_height");
    }

    private int getNavigationBarHeight() {
        return getSystemComponentDimen("navigation_bar_height");
    }

    private int getSystemComponentDimen(String dimensName) {
        int dimen = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object instance = clazz.newInstance();
            int dimenId = (int) clazz.getField(dimensName).get(instance);
            // dp --> px
            dimen = getResources().getDimensionPixelSize(dimenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dimen;
    }

    /**
     * 判断是否手机是否有导航栏
     */
    private boolean hasNavigationBar() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // 获取设备的硬件屏幕高度
            display.getRealMetrics(displayMetrics);
            int realHeightPixels = displayMetrics.heightPixels;
            int realWidthPixels = displayMetrics.widthPixels;

            // 内容区域的屏幕高度
            displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            int heightPixels = displayMetrics.heightPixels;
            int widthPixels = displayMetrics.widthPixels;

            int dHight = realHeightPixels - heightPixels;
            int dWidth = realWidthPixels - widthPixels;
            return dHight > 0 || dWidth > 0;
        }
        return false;
    }
}
