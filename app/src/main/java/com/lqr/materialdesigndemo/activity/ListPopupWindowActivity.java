package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lqr.materialdesigndemo.R;

public class ListPopupWindowActivity extends AppCompatActivity {

    private ListPopupWindow mListPopupWindow;
    private ArrayAdapter<String> mAdapter;
    private String[] mItemArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_popup_window);
        mItemArr = new String[]{"item 1", "item 2", "item 3", "item 4", "item 5", "item 6", "item 7", "item 8"};
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItemArr);
    }

    public void showPopupWindow(View view) {
        if (mListPopupWindow == null)
            mListPopupWindow = new ListPopupWindow(this);
        //设置 ListPopupWindow 的数据适配器
        mListPopupWindow.setAdapter(mAdapter);
        //设置 ListPopupWindow 的显示位置（在指定控件下方）
        mListPopupWindow.setAnchorView(view);
        //设置 ListPopupWindow 的宽度
        mListPopupWindow.setWidth(200);
        //设置 ListPopupWindow 的高度
        mListPopupWindow.setHeight(500);
        //设置 ListPopupWindow 的条目点击事件（必须在show方法前设置，否则无效）
        mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), mItemArr[position], Toast.LENGTH_SHORT).show();
                mListPopupWindow.dismiss();
            }
        });
        mListPopupWindow.show();

    }
}
