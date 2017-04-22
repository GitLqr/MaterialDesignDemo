package com.lqr.materialdesigndemo.adapter;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MyStaggeredAdapter extends RecyclerView.Adapter<MyStaggeredAdapter.MyViewHolder> {

    private List<String> mData;
    private Random mRandom = new Random();

    public MyStaggeredAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mRandom.nextInt(200) + 200);
        holder.mTv.setLayoutParams(params);
        holder.mTv.setBackgroundColor(Color.argb(255, 180 + mRandom.nextInt(60) + 30, 180 + mRandom.nextInt(60) + 30, 180 + mRandom.nextInt(60) + 30));
        holder.mTv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

}
