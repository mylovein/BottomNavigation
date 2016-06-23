package com.mylovein.bottomnavigation.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mylovein.bottomnavigation.BottomNavigation;
import com.mylovein.bottomnavigation.R;

/**
 * Created by Administrator on 2016/5/27.
 */
public abstract class BottomNavigationItemView extends FrameLayout {
    protected BottomNavigationBadge mBadge = new BottomNavigationBadge();
    protected TextView mBadgeView;

    public BottomNavigationItemView(Context context) {
        super(context);
    }

    public BottomNavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public abstract  void setItem(BottomNavigationItem item,int backgroundStyle);
    public abstract void setActive(boolean animate);
    public abstract void setInactive(boolean animate);

    public abstract void setTypeFace(Typeface typeFace);

    public BottomNavigationBadge getBadge(){
        return mBadge;
    }
    public void setBadgeStyle(int badgeStyle){
        if (badgeStyle == BottomNavigation.BADGE_STYLE_NUM){
            int size = getResources().getDimensionPixelSize(R.dimen.bottom_navigation_badge_with_num_width);
            mBadge.setBadgeStyleWithNum(size);
        }
    }

}
