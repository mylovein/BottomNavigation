package com.example.bottomnavigation.item;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomnavigation.BottomNavigation;
import com.example.bottomnavigation.MiscUtils;
import com.example.bottomnavigation.R;

/**
 * Created by Administrator on 2016/5/25.
 */
public class FixedBottomNavigationItemView  extends BottomNavigationItemView {
    private Context mContext;
    private BottomNavigationItem mItem;
    private View mView;
    private ImageView mIcon;
    private TextView mTitle;
    private int mColor,mColorActive;
    private int mPaddingTop,mPaddingTopActive;
    private float mTextSize,mTextSizeActive;
    private float mTextSizeScale;
    private int mItemWidth;


    public FixedBottomNavigationItemView(Context context) {
        super(context);
        init();
    }

    public FixedBottomNavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FixedBottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FixedBottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //初始化
        mContext = getContext();
        int mHeight = mContext.getResources().getDimensionPixelSize(R.dimen.bottom_navigation_height);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,mHeight));

        mPaddingTop = mContext.getResources().getDimensionPixelSize(R.dimen.fixed_bottom_navigation_item_with_badge_padding_top_inactive);
        mPaddingTopActive = mContext.getResources().getDimensionPixelSize(R.dimen.fixed_bottom_navigation_item_with_badge_padding_top_active);
        mTextSize = mContext.getResources().getDimension(R.dimen.bottom_navigation_item_text_size_inactive);
        mTextSizeActive = mContext.getResources().getDimension(R.dimen.bottom_navigation_item_text_size_active);
        mTextSizeScale = mTextSize/mTextSizeActive;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        mView = inflater.inflate(R.layout.fixed_bottom_navigation_item, this, false);
        mIcon = (ImageView) mView.findViewById(R.id.fixed_bottom_navigation_item_icon);
        mTitle = (TextView) mView.findViewById(R.id.fixed_bottom_navigation_item_title);
        mBadgeView = (TextView) mView.findViewById(R.id.bottom_navigation_badge);
        mBadge.setTextView(mBadgeView);
        addView(mView);
    }

    @Override
    public void setItem(BottomNavigationItem item,int backgroundStyle) {
        mItem = item;
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
        mIcon.setColorFilter(mColor, PorterDuff.Mode.SRC_IN);
        mTitle.setTextColor(mColor);
        mTitle.setScaleX(mTextSizeScale);
        mTitle.setScaleY(mTextSizeScale);

    }

    /**
     *  item选中动画
     */
    @Override
    public void setActive(boolean animate) {
        if (animate) {
            MiscUtils.changeTopPadding(mView, mPaddingTop, mPaddingTopActive);
            MiscUtils.changeImageColor(mIcon, mColor, mColorActive);
            MiscUtils.changeTextColor(mTitle, mColor, mColorActive);
//        MiscUtils.changeTextSize(mTitle,mTextSize,mTextSizeActive);
            mTitle.animate().setDuration(250).scaleX(1).scaleY(1).start();
        }else{
            mView.setPadding(mView.getPaddingLeft(),
                    mPaddingTopActive,
                    mView.getPaddingRight(),
                    mView.getPaddingBottom());
            mIcon.setColorFilter(mColorActive,PorterDuff.Mode.SRC_IN);
            mTitle.setTextColor(mColorActive);
//        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSizeActive);
            mTitle.setScaleX(1);
            mTitle.setScaleY(1);
        }
    }

    /**
     *  item取消选中动画
     */
    @Override
    public void setInactive(boolean animate) {
        if (animate) {
            MiscUtils.changeTopPadding(mView, mPaddingTopActive, mPaddingTop);
            MiscUtils.changeImageColor(mIcon, mColorActive, mColor);
            MiscUtils.changeTextColor(mTitle, mColorActive, mColor);
//        MiscUtils.changeTextSize(mTitle,mTextSizeActive,mTextSize);
            mTitle.animate().setDuration(BottomNavigation.ANIMATION_DURATION).scaleX(mTextSizeScale).scaleY(mTextSizeScale).start();
        }else{
            mView.setPadding(mView.getPaddingLeft(),
                    mPaddingTop,
                    mView.getPaddingRight(),
                    mView.getPaddingBottom());
            mIcon.setColorFilter(mColor,PorterDuff.Mode.SRC_IN);
            mTitle.setTextColor(mColor);
            mTitle.setScaleX(mTextSizeScale);
            mTitle.setScaleY(mTextSizeScale);
        }
    }

    /**
     * 设置item宽
     */
    public void setItemWidth(int itemWidth){
        this.mItemWidth = itemWidth;
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = mItemWidth;
        setLayoutParams(params);
    }


    /**
     * 设置字体
     */
    @Override
    public void setTypeFace(Typeface typeFace) {
        if (typeFace != null){
            mTitle.setTypeface(typeFace);
        }
    }

}
