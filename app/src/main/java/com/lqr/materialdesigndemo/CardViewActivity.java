package com.lqr.materialdesigndemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @创建者 CSDN_LQR
 * @描述 学习CardView、水波纹、按压效果
 * <p>
 * 一、CardView：
 * app:cardCornerRadius="10dp" 圆角大小（值越大，角越圆）
 * app:cardElevation="5dp" 阴影效果（值越大，效果越明显）
 * 1、边框圆角效果：
 * 1）5.x图片的布局都可以很好的呈现圆角效果，图片默认也可以变圆角
 * 2）4.x图片不能变圆角，如果要做成5.x一样的效果：通过加载图片的时候自己处理成圆角
 * 2、margin：
 * 1）5.x上面，边距阴影比较小，需要手动添加边距（16dp最好）
 * 2）4.x上面，边距太大，手动修改边距为0dp（原因：兼容包里面设置阴影效果自动设置了margin来处理，边距为16dp）
 * <p>
 * 二、水波纹（4.x不支持）：
 * android:clickable="true"
 * android:foreground="?attr/selectableItemBackground"
 * <p>
 * 三、按压效果（4.x不支持）：
 * 创建cardview_z_translation.xml，其中包含属性动画（objectAnimator，它的所有属性不会有提示，请参考着手写）
 * 在需要按压效果的控件上设置：android:stateListAnimator="@drawable/cardview_z_translation"(cardview_z_translation.xml可以放在drawable，或者anim)
 */
public class CardViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
    }
}
