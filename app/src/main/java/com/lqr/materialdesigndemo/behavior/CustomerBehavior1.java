package com.lqr.materialdesigndemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class CustomerBehavior1 extends CoordinatorLayout.Behavior<View> {

    // 一定要重写构造函数
    public CustomerBehavior1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 用来决定需要监听哪些控件或者容器的状态（1、知道监听谁；2、什么状态改变）
     *
     * @param parent     父容器
     * @param child      子控件--需要监听dependency这个view的视图们--观察者
     * @param dependency 你要监听的那个view
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        // 还可以根据ID或者Tag来判断
        return dependency instanceof TextView || super.layoutDependsOn(parent, child, dependency);
    }

    /**
     * 当被监听的view发生改变时回调
     * 可以在此方法里面做一些响应的联动动画等效果
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offset = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child, offset);
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
