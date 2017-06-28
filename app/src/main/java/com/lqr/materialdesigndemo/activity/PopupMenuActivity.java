package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lqr.materialdesigndemo.R;

import java.lang.reflect.Field;

public class PopupMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);
    }

    public void showPopupMenu(View view) throws NoSuchFieldException, IllegalAccessException {
        PopupMenu popupMenu = new PopupMenu(this, view);
        //设置 PopupMenu 的显示菜单项
        popupMenu.inflate(R.menu.main);
        // popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());//与一行没什么区别
        //默认 PopupMenu 不显示条目icon，可以通过反射来强制使其显示icon
        Field field = popupMenu.getClass().getDeclaredField("mPopup");
        field.setAccessible(true);
        MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popupMenu);
        mHelper.setForceShowIcon(true);
        //设置 PopupMenu 的条目点击事件（点击后会自动dismiss）
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //显示 PopupMenu
        popupMenu.show();
    }
}
