package com.oyp.recyclerview.sticky.decoration.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.oyp.recyclerview.sticky.decoration.listener.PowerfulGroupListener;

/**
 * Created by OuyangPeng on 2017/6/25.
 * 自定义View悬浮：利用分割线实现悬浮功能
 */

public class PowerfulStickyDecoration extends RecyclerView.ItemDecoration {
    /**
     * 悬浮栏高度
     */
    private int mGroupHeight = 80;
    /**
     * 回调接口
     */
    private PowerfulGroupListener mGroupListener;
    /**
     * 是否靠左边
     */
    private boolean isAlignLeft = true;

    private PowerfulStickyDecoration(PowerfulGroupListener groupListener) {
        this.mGroupListener = groupListener;
    }

    /**
     * 重写 getItemOffsets 方法，为悬浮栏预留空间
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        String groupName = getGroupName(position);
        if (groupName == null) {
            return;
        }
        //只有是同一组的第一个或者是新的组才显示悬浮栏
        if (position == 0 || isFirstInGroup(position)) {
            outRect.top = mGroupHeight;
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        //标记上一个item对应的Group
        String prevGroupName;
        //当前item对应的Group
        String currentGroupName = null;

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            prevGroupName = currentGroupName;
            currentGroupName = getGroupName(position);
            if (currentGroupName == null || TextUtils.equals(currentGroupName, prevGroupName)) {
                continue;
            }
            int viewBottom = view.getBottom();
            //决定当前顶部第一个悬浮Group的bottom
            //根据当前Item的位置确定绘制分组的位置。top将在mGroupHeight和view.getTop()中取最大值，
            //也就是说top将不会小于mGroupHeight，这样就能实现吸顶效果。
            float bottom = Math.max(mGroupHeight, view.getTop());
            int nextPosition = position + 1;
            if (nextPosition < itemCount) {
                //获取下个GroupName
                String nextGroupName = getGroupName(nextPosition);
                //下一组的第一个View接近头部
                //当下个分组的顶部（当前Item的底部viewBottom可近似认为下个Item的顶部）距离RecyclerView顶部小于bottom时，
                //偏移当前分组位置。实现下一组上滑时候，当前分组上移；上一组下滑的时候，当前分组下移。
                if (!currentGroupName.equals(nextGroupName) && viewBottom < bottom) {
                    bottom = viewBottom;
                }
            }
            //根据position获取View
            View groupView = getGroupView(position);
            if (groupView == null){
                return;
            }
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,mGroupHeight);
            groupView.setLayoutParams(layoutParams);
            groupView.setDrawingCacheEnabled(true);
            groupView.measure(
                    View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED));
            //指定高度、宽度的groupView
            groupView.layout(0,0,right,mGroupHeight);
            groupView.buildDrawingCache();
            Bitmap bitmap = groupView.getDrawingCache();
            int marginLeft = isAlignLeft ? 0: right - groupView.getMeasuredWidth();
            c.drawBitmap(bitmap,left + marginLeft , bottom - mGroupHeight ,null);
        }
    }

    /**
     * 判断是不是组中的第一个位置
     * 根据前一个组名，判断当前是否为新的组
     *
     * @param position 位置
     * @return 是不是组中的第一个位置
     */
    private boolean isFirstInGroup(int position) {
        //如果是第一个位置，直接返回true
        if (position == 0) {
            return true;
        }
        //如果是其他位置，则判断当前位置的组名和前一个位置的组名是否相同，如果不同则代表为新的分组
        String prevGroupName = getGroupName(position - 1);
        String groupName = getGroupName(position);
        return !TextUtils.equals(prevGroupName, groupName);
    }

    /**
     * 获取组名
     *
     * @param position 位置
     * @return 组名
     */
    private String getGroupName(int position) {
        if (mGroupListener == null) {
            return null;
        }
        return mGroupListener.getGroupName(position);
    }

    private View getGroupView(int position) {
        if (mGroupListener == null){
            return null;
        }
        return mGroupListener.getGroupView(position);
    }

    public static class Builder {
        private PowerfulStickyDecoration mPowerfulStickyDecoration;

        private Builder(PowerfulGroupListener groupListener) {
            mPowerfulStickyDecoration = new PowerfulStickyDecoration(groupListener);
        }

        /**
         * 初始化方法
         */
        public static Builder init(PowerfulGroupListener groupListener) {
            return new Builder(groupListener);
        }

        /**
         * 设置Group高度
         *
         * @param groutHeight 高度
         * @return this
         */
        public Builder setGroupHeight(int groutHeight) {
            mPowerfulStickyDecoration.mGroupHeight = groutHeight;
            return this;
        }

        /**
         * 是否靠左边
         * true 靠左边（默认）、false 靠右边
         * @param isAlignLeft 是否靠左边
         * @return  this
         */
        public Builder isAlignLeft(boolean isAlignLeft){
            mPowerfulStickyDecoration.isAlignLeft = isAlignLeft;
            return this;
        }

        public PowerfulStickyDecoration build() {
            return mPowerfulStickyDecoration;
        }
    }
}
