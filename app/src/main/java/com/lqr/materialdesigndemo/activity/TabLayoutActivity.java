package com.lqr.materialdesigndemo.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.lqr.materialdesigndemo.R;
import com.lqr.materialdesigndemo.fragment.TextFragment;

import java.lang.reflect.Field;


public class TabLayoutActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;

//    private final String[] title = new String[]{
//            "推荐", "热点", "视频", "深圳", "通信",
//            "互联网", "问答", "图片", "电影",
//            "网络安全", "软件"};
//            "abc", "efg"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        // 手动创建Tab
//        for (int i = 0; i < title.length; i++) {
//            TabLayout.Tab tab = mTabLayout.newTab();
//            tab.setText(title[i]);
////            tab.setIcon(R.mipmap.ic_launcher);//icon会显示在文字上面
//            mTabLayout.addTab(tab);
//        }

        // TabLayout与ViewPager结合使用
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
//        // 适配器必须重写getPageTitle()方法
//        mTabLayout.setTabsFromPagerAdapter(adapter);
//        // 监听TabLayout的标签选择，当标签选中时ViewPager切换
//        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
//        // 监听ViewPager的页面切换，当页面切换时TabLayout的标签跟着切换
//        mViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        // 关联TabLayout与ViewPager，且适配器必须重写getPageTitle()方法
        mTabLayout.setupWithViewPager(mViewPager);


        mLinearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        // 在所有子控件的中间显示分割线（还可能只显示顶部、尾部和不显示分割线）
        mLinearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        // 设置分割线的距离本身（LinearLayout）的内间距
        mLinearLayout.setDividerPadding(50);
        // 设置分割线的样式
        mLinearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical));
        mLinearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));


        // 为TabLayout设置不同状态下的字体大小（并不能成功）
//        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//            ((TextView) ((LinearLayout) mLinearLayout.getChildAt(i)).getChildAt(1)).setTextSize(10);
////            ((TextView) ((LinearLayout) ((LinearLayout) mTabLayout.getChildAt(0)).getChildAt(i)).getChildAt(0)).setTextSize(12);
//        }
//        ((TextView) ((LinearLayout) mLinearLayout.getChildAt(mTabLayout.getSelectedTabPosition())).getChildAt(1)).setTextSize(30);
//
//        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                ((TextView) ((LinearLayout) mLinearLayout.getChildAt(tab.getPosition())).getChildAt(1)).setTextSize(30);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                ((TextView) ((LinearLayout) mLinearLayout.getChildAt(tab.getPosition())).getChildAt(1)).setTextSize(10);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        // 自定义指示器（Indicator）的“长度”的两种方法
        // 方法一：反射
//        setIndicator(mTabLayout,10,10);
        // 方法二：查找子控件
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, Resources.getSystem().getDisplayMetrics());
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            View tabView = mLinearLayout.getChildAt(0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            tabView.setLayoutParams(params);
        }
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        private final String[] title = new String[]{
                "推荐", "热点", "视频", "深圳", "通信",
                "互联网", "问答", "图片", "电影",
                "网络安全", "软件"};

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new TextFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title[i]);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
