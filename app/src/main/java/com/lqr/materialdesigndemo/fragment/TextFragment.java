package com.lqr.materialdesigndemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @创建者 CSDN_LQR
 * @描述 只有一个文本控件的Fragment
 */
public class TextFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        Bundle bundle = getArguments();
        String title = (String) bundle.get("title");
        textView.setText(title);
        return textView;
    }
}
