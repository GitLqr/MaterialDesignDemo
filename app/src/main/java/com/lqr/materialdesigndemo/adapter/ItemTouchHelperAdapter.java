package com.lqr.materialdesigndemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lqr.materialdesigndemo.R;
import com.lqr.materialdesigndemo.listener.ItemDragListener;
import com.lqr.materialdesigndemo.listener.ItemMoveListener;

import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemTouchHelperViewHolder> implements ItemMoveListener {

    private List<String> mData;
    private ItemDragListener mItemDragListener;

    public ItemTouchHelperAdapter(List<String> data, ItemDragListener itemDragListener) {
        mData = data;
        mItemDragListener = itemDragListener;
    }

    @Override
    public ItemTouchHelperViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_touch_helper, viewGroup, false);
        return new ItemTouchHelperViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemTouchHelperViewHolder viewHolder, int position) {
        viewHolder.mTvStr.setText(mData.get(position));
        viewHolder.mIvDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mItemDragListener.onStartDrags(viewHolder);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //1、交换数据
        Collections.swap(mData, fromPosition, toPosition);
        //2、刷新
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        //1、删除数据
        mData.remove(position);
        //2、刷新
        notifyItemRemoved(position);
        return true;
    }

    class ItemTouchHelperViewHolder extends RecyclerView.ViewHolder {

        TextView mTvStr;
        ImageView mIvDrag;

        public ItemTouchHelperViewHolder(View itemView) {
            super(itemView);
            mTvStr = (TextView) itemView.findViewById(R.id.tvStr);
            mIvDrag = (ImageView) itemView.findViewById(R.id.ivDrag);
        }
    }

}
