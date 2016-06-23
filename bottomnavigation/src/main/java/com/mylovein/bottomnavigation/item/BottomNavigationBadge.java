package com.mylovein.bottomnavigation.item;

import android.animation.Animator;
import android.view.View;
import android.widget.TextView;

import com.mylovein.bottomnavigation.BottomNavigation;
import com.mylovein.bottomnavigation.R;

/**
 * Created by Administrator on 2016/6/6.
 */
public class BottomNavigationBadge {
    private int badgeStyle = BottomNavigation.BADGE_STYLE_STATIC;
    private TextView badgeTextView;
    private BottomNavigation.BadgeState badgeState;

    /**
     * 设置badge状态
     */
    public void setBadgeState(BottomNavigation.BadgeState badgeState) {
        this.badgeState = badgeState;
        badgeTextView.setText(badgeState.getText());
        if (badgeState.isShow()){
            badgeTextView.setScaleX(1);
            badgeTextView.setScaleY(1);
            badgeTextView.setVisibility(View.VISIBLE);
        }
    }

    public BottomNavigation.BadgeState getBadgeState() {
        return badgeState;
    }

    /**
     * 设置badge的textview
     */
    public void setTextView(TextView textView) {
        badgeTextView = textView;
    }

    /**
     * 设置badge显示的数字
     */
    public void setText(String text) {
        if (badgeTextView == null || text == null || badgeStyle != BottomNavigation.BADGE_STYLE_NUM){
            return;
        }
        badgeTextView.setText(text);
    }


    public String getText() {
        if (badgeTextView == null) {
            return null;
        }
        return badgeTextView.getText().toString();
    }

    /**
     * 设置显示的是带数字的badge
     */
    public void setBadgeStyleWithNum(int size) {
        if (badgeTextView == null) {
            return;
        }
        badgeStyle = BottomNavigation.BADGE_STYLE_NUM;
        badgeTextView.getLayoutParams().width = size;
        badgeTextView.getLayoutParams().height = size;
        badgeTextView.setBackgroundResource(R.drawable.bottom_navigation_badge_with_num_background);
    }

    /**
     * 显示
     */
    public void show(String text) {
        if (badgeTextView == null) {
            return;
        }

        if (badgeStyle == BottomNavigation.BADGE_STYLE_NUM) {
            setText(text);
            badgeState.setText(text);
        }

        badgeState.setShow(true);
        badgeTextView.setVisibility(View.VISIBLE);
        badgeTextView.animate().setDuration(150).setListener(null).scaleX(1).scaleY(1).start();

    }

    /**
     * 隐藏
     */
    public void hide() {
        if (badgeTextView == null) {
            return;
        }

        badgeTextView.setVisibility(View.VISIBLE);
        badgeTextView.animate().setDuration(150).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                badgeTextView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                badgeTextView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).scaleX(0).scaleY(0).start();
    }


    public void hideWithNum(String text) {
        if (badgeTextView == null) {
            return;
        }
        if (badgeStyle == BottomNavigation.BADGE_STYLE_NUM) {
            setText(text);
            hide();
        }
    }


}
