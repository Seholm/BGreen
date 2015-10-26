package com.bgreen.filips.bgreen.main;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by flarssonn on 2015-10-15.
 */
public class MyViewPager extends ViewPager {
    private boolean mIsEnabledSwipe = true;

    public MyViewPager(Context context){
        super(context);

    }
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mIsEnabledSwipe) {
            return false;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!mIsEnabledSwipe) {
            return false;
        }

        return super.onInterceptTouchEvent(event);
    }


    public void setEnabledSwipe(boolean enabled) {
        mIsEnabledSwipe = enabled;
    }
}
