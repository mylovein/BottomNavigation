package com.mylovein.bottomnavigation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/5/9.
 */
public class MiscUtils {
    /**
     * This method can be extended to get all android attributes color, string, dimension ...etc
     *
     * @param context          used to fetch android attribute
     * @param androidAttribute attribute codes like R.attr.colorAccent
     * @return in this case color of android attribute
     */
    public static int fetchContextColor(Context context, int androidAttribute) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{androidAttribute});
        int color = a.getColor(0, 0);
        a.recycle();

        return color;
    }

    /**
     * @param context used to get system services
     * @return screenWidth in pixels
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.x;
    }

    /**
     * 修改顶部padding
     * @param view
     * @param fromPadding
     * @param toPadding
     */
    public static void changeTopPadding(final View view, int fromPadding, int toPadding) {
        ValueAnimator animator = ValueAnimator.ofInt(fromPadding, toPadding);
        animator.setDuration(BottomNavigation.ANIMATION_DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                view.setPadding(view.getPaddingLeft(),
                        animatedValue,
                        view.getPaddingRight(),
                        view.getPaddingBottom());
            }
        });
        animator.start();
    }

    /**
     * 修改icon颜色
     *
     * @param image
     * @param fromColor
     * @param toColor
     */
    public static void changeImageColor(final ImageView image, int fromColor, int toColor) {
        ValueAnimator imageColorChangeAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        imageColorChangeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                image.setColorFilter((Integer) animator.getAnimatedValue(), PorterDuff.Mode.SRC_IN);
            }
        });
        imageColorChangeAnimation.setDuration(BottomNavigation.ANIMATION_DURATION);
        imageColorChangeAnimation.start();
    }

    /**
     * 修改Text大小（弃用）
     *
     * @param textView
     * @param from
     * @param to
     */
    public static void changeTextSize(final TextView textView, float from, float to) {
        ValueAnimator textSizeChangeAnimator = ValueAnimator.ofFloat(from, to);
        textSizeChangeAnimator.setDuration(BottomNavigation.ANIMATION_DURATION);
        textSizeChangeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) valueAnimator.getAnimatedValue());
            }
        });
        textSizeChangeAnimator.start();
    }


    /**
     * 修改Text颜色
     *
     * @param textView
     * @param fromColor
     * @param toColor
     */
    public static void changeTextColor(final TextView textView, int fromColor, int toColor) {
        ValueAnimator changeTextColorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        changeTextColorAnimation.setDuration(BottomNavigation.ANIMATION_DURATION);
        changeTextColorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                textView.setTextColor((Integer) animator.getAnimatedValue());
            }
        });
        changeTextColorAnimation.start();
    }

    /**
     * 修改Text透明度
     *
     * @param textView
     * @param fromAlpha
     * @param toAlpha
     */
    public static void changeTextAlphaWithDelay(final TextView textView, float fromAlpha, float toAlpha) {
        PropertyValuesHolder pvhAlpha = null;
        if (fromAlpha<toAlpha) {
            //            Keyframe kf0 = Keyframe.ofFloat(0f, fromAlpha);
//            Keyframe kf1 = Keyframe.ofFloat(0.3f,fromAlpha);
//            Keyframe kf2 = Keyframe.ofFloat(1f, toAlpha);
//            pvhRotation = PropertyValuesHolder.ofKeyframe("alpha",kf0,kf1,kf2);
            pvhAlpha = PropertyValuesHolder.ofFloat(
                    "alpha", fromAlpha, fromAlpha, toAlpha);
        }else{
//            Keyframe kf0 = Keyframe.ofFloat(0f, fromAlpha);
//            Keyframe kf1 = Keyframe.ofFloat(0.7f,toAlpha);
//            Keyframe kf2 = Keyframe.ofFloat(1f, toAlpha);
//            pvhRotation = PropertyValuesHolder.ofKeyframe("alpha",kf0,kf1,kf2);
            pvhAlpha = PropertyValuesHolder.ofFloat(
                    "alpha", fromAlpha, toAlpha, toAlpha);
        }
//        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe(
//                "alpha", kf0, kf1, kf2);
        ValueAnimator changeTextAlphaAnimation = ValueAnimator.ofPropertyValuesHolder(pvhAlpha);
        changeTextAlphaAnimation.setDuration(BottomNavigation.ANIMATION_DURATION);
//        if (isDelay){
//            changTextAlphaAnimation.setInterpolator(new RPInterpolator(Easing.EXPO_IN));
//        }else{
//            changTextAlphaAnimation.setInterpolator(new RPInterpolator(Easing.EXPO_OUT));
//        }
        changeTextAlphaAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                textView.setAlpha((float) animator.getAnimatedValue());
            }
        });
        changeTextAlphaAnimation.start();
    }


    /**
     * 修改item宽
     *
     * @param view
     * @param fromWidth
     * @param toWidth
     */
    public static void changeItemWidth(final View view, int fromWidth, int toWidth) {
//        int width = view.getWidth();
        ValueAnimator changeItemWidthAnimation = ValueAnimator.ofInt(fromWidth, toWidth);
        changeItemWidthAnimation.setDuration(BottomNavigation.ANIMATION_DURATION);
        changeItemWidthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.getLayoutParams().width = (int) animator.getAnimatedValue();
                view.requestLayout();
//                view.setLayoutParams(new LinearLayout.LayoutParams((int) animator.getAnimatedValue(),view.getLayoutParams().height));
            }
        });
        changeItemWidthAnimation.start();
    }

    /**
     * 设置ripple动画
     *
     */
    public static void setBackgroundWithRipple(View view, final View backgroundView,
                                               final View backgroundOverlay, final int newColor) {
        int centerX = (int) (view.getX() + (view.getMeasuredWidth() / 2));
        int centerY = view.getMeasuredHeight() / 2;
        int finalRadius = backgroundOverlay.getWidth();

        backgroundOverlay.clearAnimation();
        backgroundView.clearAnimation();

        Animator circularReveal;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularReveal = ViewAnimationUtils
                    .createCircularReveal(backgroundOverlay, centerX, centerY, 0, finalRadius);
        } else {
            backgroundOverlay.setAlpha(0);
            circularReveal = ObjectAnimator.ofFloat(backgroundOverlay, "alpha", 0, 1);
//            backgroundColorChange(backgroundView,,newColor);
        }

        circularReveal.setDuration(BottomNavigation.ANIMATION_DURATION);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                onCancel();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                onCancel();
            }

            private void onCancel() {
                backgroundView.setBackgroundColor(newColor);
                backgroundOverlay.setVisibility(View.GONE);
            }
        });

        backgroundOverlay.setBackgroundColor(newColor);
        backgroundOverlay.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    /*
    public static void backgroundColorChange(final View view, int fromColor, int toColor) {
        ValueAnimator imageColorChangeAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        imageColorChangeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((Integer) animator.getAnimatedValue());
            }
        });
        imageColorChangeAnimation.setDuration(BottomNavigation.BADGE_ANIMATION_DURATION);
        imageColorChangeAnimation.start();
    }
    */


}
