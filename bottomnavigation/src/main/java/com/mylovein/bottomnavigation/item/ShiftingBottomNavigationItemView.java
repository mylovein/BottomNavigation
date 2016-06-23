package com.mylovein.bottomnavigation.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylovein.bottomnavigation.BottomNavigation;
import com.mylovein.bottomnavigation.MiscUtils;
import com.mylovein.bottomnavigation.R;

/**
 * Created by Administrator on 2016/5/25.
 */
public class ShiftingBottomNavigationItemView extends BottomNavigationItemView {
    private Context mContext;
    private BottomNavigationItem mItem;
    private View mView;
    private ImageView mIcon;
    private TextView mTitle;
    private int mColor,mColorActive;
    private int mPaddingTop,mPaddingTopActive,mTextSize,mTextSizeActive;
    private int mItemWidth,mItemWidthActive;

    public ShiftingBottomNavigationItemView(Context context) {
        super(context);
        init();
    }

    public ShiftingBottomNavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShiftingBottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShiftingBottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //初始化
        mContext = getContext();
        int mHeight = mContext.getResources().getDimensionPixelSize(R.dimen.bottom_navigation_height);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,mHeight));

        mPaddingTop = mContext.getResources().getDimensionPixelSize(R.dimen.shifting_bottom_navigation_item_with_badge_padding_top_inactive);
        mPaddingTopActive = mContext.getResources().getDimensionPixelSize(R.dimen.shifting_bottom_navigation_item_with_badge_padding_top_active);
        mTextSize = mContext.getResources().getDimensionPixelSize(R.dimen.bottom_navigation_item_text_size_inactive);
        mTextSizeActive = mContext.getResources().getDimensionPixelSize(R.dimen.bottom_navigation_item_text_size_active);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        mView = inflater.inflate(R.layout.shifting_bottom_navigation_item, this, false);
        mIcon = (ImageView) mView.findViewById(R.id.shifting_bottom_navigation_item_icon);
        mTitle = (TextView) mView.findViewById(R.id.shifting_bottom_navigation_item_title);
        mBadgeView = (TextView) mView.findViewById(R.id.bottom_navigation_badge);

        addView(mView);
    }

    @Override
    public void setItem(BottomNavigationItem item,int backgroundStyle) {
        mItem = item;
//        if (item.getColor() == 0) {
////            color = ContextCompat.getColor(mContext, R.color.bottom_nagvigation_item_text_color_gray);
//            mColor = Color.LTGRAY;
//            mColorActive = MiscUtils.fetchContextColor(mContext, R.attr.colorPrimary);
//        }else {
////            color = ContextCompat.getColor(mContext, R.color.bottom_nagvigation_item_text_color_gray);
//            mColor = Color.LTGRAY;
//            mColorActive = Color.WHITE;
//        }

        if (backgroundStyle == BottomNavigation.BACKGROUND_STYLE_RIPPLE){
            mColorActive = Color.WHITE;
            mColor = ContextCompat.getColor(mContext, R.color.bottom_nagvigation_item_text_color_white_inactive);
        }else{
            if (mItem.getColor() == 0) {
                mColorActive = MiscUtils.fetchContextColor(mContext, R.attr.colorPrimary);
            }else {
                mColorActive = ContextCompat.getColor(mContext,mItem.getColor());
            }
            mColor = ContextCompat.getColor(mContext, R.color.bottom_nagvigation_item_text_color_gray);
        }

        mIcon.setImageDrawable(ContextCompat.getDrawable(mContext, item.getIcon()));
        mTitle.setText(item.getTitle());
        mIcon.setColorFilter(mColor);
        mTitle.setTextColor(mColor);
        mBadge.setTextView(mBadgeView);


    }

    @Override
    public void setActive(boolean animate) {
        if (animate) {
            MiscUtils.changeItemWidth(this, mItemWidth, mItemWidthActive);
            MiscUtils.changeTopPadding(mView, mPaddingTop, mPaddingTopActive);
            MiscUtils.changeImageColor(mIcon, mColor, mColorActive);
            MiscUtils.changeTextColor(mTitle, mColor, mColorActive);
            MiscUtils.changeTextAlphaWithDelay(mTitle, 0, 1);
        }else{
            ViewGroup.LayoutParams params = getLayoutParams();
            params.width = mItemWidthActive;
            setLayoutParams(params);
            mView.setPadding(mView.getPaddingLeft(),
                    mPaddingTopActive,
                    mView.getPaddingRight(),
                    mView.getPaddingBottom());
            mIcon.setColorFilter(mColorActive);
            mTitle.setTextColor(mColorActive);
            mTitle.setAlpha(1);
        }
    }

    @Override
    public void setInactive(boolean animate) {
        if (animate){
            MiscUtils.changeItemWidth(this,mItemWidthActive,mItemWidth);
            MiscUtils.changeTopPadding(mView, mPaddingTopActive,mPaddingTop );
            MiscUtils.changeImageColor(mIcon,mColorActive,mColor);
            MiscUtils.changeTextColor(mTitle,mColorActive,mColor);
            MiscUtils.changeTextAlphaWithDelay(mTitle,1,0);
        }else{
            ViewGroup.LayoutParams params = getLayoutParams();
            params.width = mItemWidth;
            setLayoutParams(params);
            mView.setPadding(mView.getPaddingLeft(),
                    mPaddingTopActive,
                    mView.getPaddingRight(),
                    mView.getPaddingBottom());
            mIcon.setColorFilter(mColorActive);
            mTitle.setTextColor(mColorActive);
            mTitle.setAlpha(1);
        }

    }

    public void setItemWidth(int itemWidth,int itemWidthActive){
        this.mItemWidth = itemWidth;
        this.mItemWidthActive = itemWidthActive;
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = mItemWidth;
        setLayoutParams(params);
    }

    @Override
    public void setTypeFace(Typeface typeFace) {
        if (typeFace != null){
            mTitle.setTypeface(typeFace);
        }
    }
}
