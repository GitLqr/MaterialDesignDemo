package com.lqr.materialdesigndemo.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lqr.materialdesigndemo.R;
import com.lqr.materialdesigndemo.data.Cheeses;

/**
 * @创建者 CSDN_LQR
 * @描述 调色板网格适配器
 */
public class PaletteGridAdapter extends RecyclerView.Adapter<PaletteGridAdapter.PaletteGridViewHolder> {

    final int[] picResId = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R.mipmap.p4, R.mipmap.p5,
            R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R.mipmap.p10, R.mipmap.p11, R.mipmap.p12,
            R.mipmap.p13, R.mipmap.p14, R.mipmap.p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18,
            R.mipmap.p19, R.mipmap.p20, R.mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24,
            R.mipmap.p25, R.mipmap.p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30,
            R.mipmap.p31, R.mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36,
            R.mipmap.p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R.mipmap.p43, R.mipmap.p44};

    @Override
    public PaletteGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_palette, parent, false);
        return new PaletteGridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PaletteGridViewHolder holder, final int position) {
        holder.mIvPic.setImageResource(picResId[position]);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.mIvPic.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // 对于一张图片，它可能分析不出来暗、亮色，返回值为空，我这里采取的方案是当获取不到色调样品，则获取其他色调样品。
                Palette.Swatch swatch = palette.getDarkMutedSwatch();
                if (swatch == null) {
                    swatch = palette.getDarkVibrantSwatch();
                }
                if (swatch == null) {
                    swatch = palette.getLightMutedSwatch();
                }
                if (swatch == null) {
                    swatch = palette.getLightVibrantSwatch();
                }
                if (swatch == null) {
                    swatch = palette.getMutedSwatch();
                }
                if (swatch == null) {
                    swatch = palette.getVibrantSwatch();
                }
                int titleTextColor = swatch.getTitleTextColor();
                int rgb = swatch.getRgb();

                holder.mTvTitle.setText(Cheeses.sCheeseStrings[position]);
                holder.mTvTitle.setTextColor(titleTextColor);
                holder.mTvTitle.setBackgroundColor(generateTransparentColor(0.5f, rgb));

            }
        });
    }

    private int generateTransparentColor(float percent, int rgb) {
        int red = Color.red(rgb);
        int green = Color.green(rgb);
        int blue = Color.blue(rgb);
        int alpha = Color.alpha(rgb);
        alpha = (int) (percent * alpha);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    public int getItemCount() {
        return picResId.length;
    }

    class PaletteGridViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvPic;
        TextView mTvTitle;

        public PaletteGridViewHolder(View itemView) {
            super(itemView);
            mIvPic = (ImageView) itemView.findViewById(R.id.ivPic);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
