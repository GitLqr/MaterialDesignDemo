package com.lqr.materialdesigndemo.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.lqr.materialdesigndemo.R;


public class PaletteActivity extends AppCompatActivity {

    private ImageView mIvPic;
    private TextView mTvBody;
    private TextView mTvTitle;
    private TextView mTvDarkMutedColor;
    private TextView mTvLightMutedColor;
    private TextView mTvDarkVibrantColor;
    private TextView mTvLightVibrantColor;
    private TextView mTvMutedColor;
    private TextView mTvVibrantColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        mIvPic = (ImageView) findViewById(R.id.ivPic);
        mTvBody = (TextView) findViewById(R.id.tvBody);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvDarkMutedColor = (TextView) findViewById(R.id.tvDarkMutedColor);
        mTvLightMutedColor = (TextView) findViewById(R.id.tvLightMutedColor);
        mTvDarkVibrantColor = (TextView) findViewById(R.id.tvDarkVibrantColor);
        mTvLightVibrantColor = (TextView) findViewById(R.id.tvLightVibrantColor);
        mTvMutedColor = (TextView) findViewById(R.id.tvMutedColor);
        mTvVibrantColor = (TextView) findViewById(R.id.tvVibrantColor);

        BitmapDrawable drawable = (BitmapDrawable) mIvPic.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Palette.Builder builder = Palette.from(bitmap);
        builder.maximumColorCount(32); // 构建Palette时使用的最大颜色数，默认是16，风景图推荐取值8-16，人脸图像推荐取值24-32（值越大，花费的时间越长，可选择的色彩越多）
//                .setRegion(0, 0, bitmap.getWidth() - 1, bitmap.getHeight()) // 设置Palette颜色分析的图像区域
//                .addFilter(new Palette.Filter() { // 设置过滤器来过滤不需要的颜色（目前还不清楚怎么用，网上也找不到任何教程及代码，无从学习~）
//                    @Override
//                    public boolean isAllowed(int rgb, float[] hsl) {
//                        return false;
//                    }
//                })
//                .clearRegion()
//                .clearFilters();

        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // 暗、柔和
                int darkMutedColor = palette.getDarkMutedColor(Color.BLACK);
                // 亮、柔和
                int lightMutedColor = palette.getLightMutedColor(Color.BLACK);
                // 暗、鲜艳
                int darkVibrantColor = palette.getDarkVibrantColor(Color.BLACK);
                // 亮、鲜艳
                int lightVibrantColor = palette.getLightVibrantColor(Color.BLACK);
                // 柔和
                int mutedColor = palette.getMutedColor(Color.BLACK);
                // 鲜艳
                int vibrantColor = palette.getVibrantColor(Color.BLACK);

                // 获取某种色调的样品（这里指柔和的暗色）
                Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                // 获取图片的整体颜色rgb混合值---主色调
                int rgb = darkMutedSwatch.getRgb();
                // 获取图片中间的文字颜色
                int bodyTextColor = darkMutedSwatch.getBodyTextColor();
                // 获取作为标题的颜色（有一定的和图片的对比度的颜色值）
                int titleTextColor = darkMutedSwatch.getTitleTextColor();
                // 颜色向量
                float[] hsl = darkMutedSwatch.getHsl();
                // 分析该颜色在图片中所占像素值
                int population = darkMutedSwatch.getPopulation();

                mTvBody.setBackgroundColor(generateTransparentColor(0.2f, rgb));
                mTvBody.setTextColor(bodyTextColor);
                mTvTitle.setBackgroundColor(generateTransparentColor(0.6f, rgb));
                mTvTitle.setTextColor(titleTextColor);

                mTvDarkMutedColor.setBackgroundColor(darkMutedColor);
                mTvLightMutedColor.setBackgroundColor(lightMutedColor);
                mTvDarkVibrantColor.setBackgroundColor(darkVibrantColor);
                mTvLightVibrantColor.setBackgroundColor(lightVibrantColor);
                mTvMutedColor.setBackgroundColor(mutedColor);
                mTvVibrantColor.setBackgroundColor(vibrantColor);
            }
        });
    }

    private int generateTransparentColor(float percent, int rgb) {
        int red = Color.red(rgb);
        int green = Color.green(rgb);
        int blue = Color.blue(rgb);
        int alpha = Color.alpha(rgb);
        alpha = (int) (alpha * percent);
        return Color.argb(alpha, red, green, blue);
    }
}
