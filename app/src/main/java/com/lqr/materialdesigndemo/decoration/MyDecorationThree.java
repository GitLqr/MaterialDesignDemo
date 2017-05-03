package com.lqr.materialdesigndemo.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.lqr.materialdesigndemo.utils.PinyinUtils;

import java.util.List;


public class MyDecorationThree extends RecyclerView.ItemDecoration {

    Context mContext;
    List<String> mData;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public MyDecorationThree(Context context, List<String> data) {
        mContext = context;
        mData = data;
        paint.setTextSize(sp2px(16));
        paint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawLetterToItemLeft(c, parent);
    }

    private void drawLetterToItemLeft(Canvas c, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager))
            return;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            int position = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() + i;
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            float left = 0;
            float top = child.getTop() - params.topMargin;
            float right = child.getLeft() - params.leftMargin;
            float bottom = child.getBottom() + params.bottomMargin;
            float width = right - left;
            float height = bottom - (bottom - top) / 2;
            //当前名字拼音的第一个字母
            String letter = PinyinUtils.getPinyin(mData.get(position)).charAt(0) + "";
            if (position == 0) {
                drawLetter(letter, width, height, c, parent);
            } else {
                String preLetter = PinyinUtils.getPinyin(mData.get(position - 1)).charAt(0) + "";
                if (!letter.equalsIgnoreCase(preLetter)) {
                    drawLetter(letter, width, height, c, parent);
                }
            }
        }
    }

    private void drawLetter(String letter, float width, float height, Canvas c, RecyclerView parent) {
        float fontLength = getFontLength(paint, letter);
        float fontHeight = getFontHeight(paint);
        float tx = (width - fontLength) / 2;
        float ty = height - fontHeight / 2 + getFontLeading(paint);
        c.drawText(letter, tx, ty, paint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(dip2px(40), 0, 0, 0);
    }

    private int dip2px(int dip) {
        float density = mContext.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    public int sp2px(int sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, mContext.getResources().getDisplayMetrics()) + 0.5f);
    }


    /**
     * 返回指定笔和指定字符串的长度
     */
    private float getFontLength(Paint paint, String str) {
        return paint.measureText(str);
    }

    /**
     * 返回指定笔的文字高度
     */
    private float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }


    /**
     * 返回指定笔离文字顶部的基准距离
     */
    private float getFontLeading(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.leading - fm.ascent;
    }
}
