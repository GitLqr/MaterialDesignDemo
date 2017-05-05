package com.lqr.materialdesigndemo.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lqr.materialdesigndemo.listener.ItemMoveListener;


public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    ItemMoveListener mItemMoveListener;

    public MyItemTouchHelperCallback(ItemMoveListener itemMoveListener) {
        mItemMoveListener = itemMoveListener;
    }

    /**
     * 获取动作标识
     * 动作标识分：dragFlags和swipeFlags
     * dragFlags：列表滚动方向的动作标识（如竖直列表就是上和下，水平列表就是左和右）
     * wipeFlags：与列表滚动方向垂直的动作标识（如竖直列表就是左和右，水平列表就是上和下）
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int flags = makeMovementFlags(dragFlags, swipeFlags);
        return flags;
    }

    /**
     * 是否开启item长按拖拽功能
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 当item拖拽移动时触发
     *
     * @param recyclerView
     * @param viewHolder       当前被拖拽的item的viewHolder
     * @param targetViewHolder 当前被拖拽的item下方的另一个item的viewHolder
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder targetViewHolder) {
        return mItemMoveListener.onItemMove(viewHolder.getAdapterPosition(), targetViewHolder.getAdapterPosition());
    }

    /**
     * 当item侧滑出去时触发（竖直列表是侧滑，水平列表是竖滑）
     *
     * @param viewHolder
     * @param direction  滑动的方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mItemMoveListener.onItemRemove(viewHolder.getAdapterPosition());
    }

    /**
     * 当item被拖拽或侧滑时触发
     *
     * @param viewHolder
     * @param actionState 当前item的状态
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        //不管是拖拽或是侧滑，背景色都要变化
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
    }

    /**
     * 当item的交互动画结束时触发
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(android.R.color.white));

        viewHolder.itemView.setAlpha(1);
        viewHolder.itemView.setScaleY(1);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float value = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(value);
            viewHolder.itemView.setScaleY(value);
        }
    }
}
