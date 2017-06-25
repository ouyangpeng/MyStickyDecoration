package com.oyp.recyclerview.sticky.decoration.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

import com.oyp.recyclerview.sticky.decoration.listener.GroupListener;

/**
 * Created by OuyangPeng on 2017/6/25.
 * 文字悬浮：利用分割线实现悬浮功能
 */

public class StickyDecoration extends RecyclerView.ItemDecoration {
    /**
     * group背景色，默认灰色
     */
    @ColorInt
    private int mGroupBackgroud = Color.GRAY;
    /**
     * 字体颜色，默认白色
     */
    @ColorInt
    private int mGroupTextColor = Color.WHITE;
    /**
     * 悬浮栏高度
     */
    private int mGroupHeight = 80;
    /**
     * 文字距离左边的距离
     */
    private int mLeftMargin = 10;
    /**
     * 字体大小
     */
    private int mTextSize = 40;
    /**
     * 回调接口
     */
    private GroupListener mGroupListener;
    /**
     * 悬浮栏中文本的画笔
     */
    private TextPaint mTextPaint;
    /**
     * 悬浮栏的画笔
     */
    private Paint mGroupPaint;

    private StickyDecoration(GroupListener groupListener) {
        this.mGroupListener = groupListener;
        //设置悬浮栏的画笔
        mGroupPaint = new Paint();
        mGroupPaint.setColor(mGroupBackgroud);
        //设置悬浮栏中文本的画笔
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mGroupTextColor);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
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
        int left = parent.getLeft() + parent.getPaddingLeft();
        int right = parent.getRight() - parent.getPaddingRight();

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
            //根据top绘制group
            c.drawRect(left, bottom - mGroupHeight, right, bottom, mGroupPaint);

            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            //文字竖直居中显示
            float baseLine = bottom - (mGroupHeight - (fontMetrics.bottom - fontMetrics.top)) / 2 - fontMetrics.bottom;
            c.drawText(currentGroupName, left + mLeftMargin, baseLine, mTextPaint);
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

    public static class Builder {
        private StickyDecoration mStickyDecoration;

        private Builder(GroupListener groupListener) {
            mStickyDecoration = new StickyDecoration(groupListener);
        }

        /**
         * 初始化方法
         */
        public static Builder init(GroupListener groupListener) {
            return new Builder(groupListener);
        }
        /**
         * 设置Group背景
         *
         * @param background 背景色
         */
        public Builder setGroupBackGroup(@ColorInt int background){
            mStickyDecoration.mGroupBackgroud = background;
            mStickyDecoration.mGroupPaint.setColor(mStickyDecoration.mGroupBackgroud);
            return this;
        }

        /**
         * 设置字体大小
         *
         * @param textSize 字体大小
         */
        public Builder setGroupTextSize(int textSize) {
            mStickyDecoration.mTextSize = textSize;
            mStickyDecoration.mTextPaint.setTextSize(mStickyDecoration.mTextSize);
            return this;
        }

        /**
         * 设置Group高度
         *
         * @param groutHeight 高度
         * @return this
         */
        public Builder setGroupHeight(int groutHeight) {
            mStickyDecoration.mGroupHeight = groutHeight;
            return this;
        }

        /**
         * 组TextColor
         *
         * @param color 颜色
         * @return this
         */
        public Builder setGroupTextColor(@ColorInt int color) {
            mStickyDecoration.mGroupTextColor = color;
            return this;
        }

        /**
         * 设置左边距
         *
         * @param leftMargin 左边距
         * @return this
         */
        public Builder setTextLeftMargin(int leftMargin) {
            mStickyDecoration.mLeftMargin = leftMargin;
            return this;
        }

        public StickyDecoration build() {
            return mStickyDecoration;
        }
    }
}
