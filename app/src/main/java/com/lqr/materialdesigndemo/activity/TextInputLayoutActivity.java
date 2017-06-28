package com.lqr.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import com.lqr.materialdesigndemo.R;


public class TextInputLayoutActivity extends AppCompatActivity {

    private TextInputLayout mTextInputLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text_input_layout);
        mTextInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);

        //开启计数
        mTextInputLayout.setCounterEnabled(true);
        mTextInputLayout.setCounterMaxLength(6);

        //定义错误提示
        mTextInputLayout.getEditText().addTextChangedListener(new MyTextWatch(mTextInputLayout, "长度不能超过6个字符"));
    }

    class MyTextWatch implements TextWatcher {

        TextInputLayout mTextInputLayout;
        String mErrorTip;

        public MyTextWatch(TextInputLayout textInputLayout, String errorTip) {
            mTextInputLayout = textInputLayout;
            mErrorTip = errorTip;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mTextInputLayout.getEditText().getText().toString().trim().length() > 6) {
                mTextInputLayout.setErrorEnabled(true);
                mTextInputLayout.setError(mErrorTip);
            } else {
                mTextInputLayout.setErrorEnabled(false);
            }
        }
    }

}
