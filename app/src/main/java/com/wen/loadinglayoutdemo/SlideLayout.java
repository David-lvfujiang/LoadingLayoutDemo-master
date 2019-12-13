package com.wen.loadinglayoutdemo;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/10/29
 * @Describe:
 */

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * @Created by xww.
 * @Creation time 2018/8/21.
 */

public class SlideLayout extends FrameLayout {

    private View mContentView;
    private View mMenuView;

    private int mMenuWidth;
    private int mMenuHeight;
    private int mContentWidth;
    private int mContentHeight;

    private Scroller mScroller;

    private float startX;
    private float startY;

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
        mMenuView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContentWidth = getMeasuredWidth();
        mContentHeight = getMeasuredHeight();
        mMenuWidth = mMenuView.getMeasuredWidth();
        mMenuHeight = mMenuView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //将menu布局到右侧不可见（屏幕外）
        mMenuView.layout(mContentWidth, 0, mContentWidth + mMenuWidth, mMenuHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                final float dx = (int) (x - startX);
                final float dy = (int) (startY - y);

                int disX = (int) (getScrollX() - dx);
                if (disX <= -10) {
                    disX = 0;
                } else if (disX >= mMenuWidth) {
                    disX = mMenuWidth;
                }

                scrollTo(disX, getScrollY());

                final float moveX = Math.abs(x - startX);
                final float moveY = Math.abs(y - startY);
                if (moveX > moveY && moveX > 10f) {
                    //剥夺ListView对touch事件的处理权
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                startX = x;
                startY = y;
                break;

            case MotionEvent.ACTION_UP:
                if (getScrollX() < mMenuWidth / 2) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public final void openMenu() {
        mScroller.startScroll(getScrollX(), getScrollY(), mMenuWidth - getScrollX(), 0);
        invalidate();
    }

    public final void closeMenu() {
        mScroller.startScroll(getScrollX(), getScrollY(), 0 - getScrollX(), 0);
        invalidate();
    }
}

