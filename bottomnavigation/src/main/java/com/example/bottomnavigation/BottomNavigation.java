package com.example.bottomnavigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.bottomnavigation.behaviour.BBottomNavigationBehavior;
import com.example.bottomnavigation.behaviour.BottomNavigationBehavior;
import com.example.bottomnavigation.item.BottomNavigationItem;
import com.example.bottomnavigation.item.BottomNavigationItemView;
import com.example.bottomnavigation.item.FixedBottomNavigationItemView;
import com.example.bottomnavigation.item.ShiftingBottomNavigationItemView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9.
 */
public class BottomNavigation extends FrameLayout implements View.OnClickListener {

    private Context mContext;
    private Resources mResources;

    private FrameLayout mContainer;//BottomNavigation最外层
    private View mBackgroundOverlay;//BottomNavigation Ripple动画层
    private LinearLayout mItemContainer;//BottomNavigation item层

    private List<BottomNavigationItem> items = new ArrayList<>();//item实体类列表
    private List<BottomNavigationItemView> itemViews = new ArrayList<>();//item视图列表
    private List<BadgeState> badgeStates = new ArrayList<BadgeState>();//item消息通知标记列表


    public static final int MODE_DEFAULT = 0;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SHIFTING = 2;
    //item视图样式
    private int mMode = MODE_FIXED;

    public static final int BACKGROUND_STYLE_STATIC = 0;
    public static final int BACKGROUND_STYLE_RIPPLE = 1;
    //item背景动画样式
    private int mBackgroundStyle = BACKGROUND_STYLE_STATIC;

    public static final int BADGE_STYLE_STATIC = 0;
    public static final int BADGE_STYLE_NUM = 1;
    //item消息通知标记样式
    private int mBadgeStyle = BADGE_STYLE_STATIC;

    //BottomNavigation属性
    private int mScreenWidth;
    private int mHeight, mShadowHeight;
//    private int mTextColorActive, mTextColorInActive, mBackgroundColor;

    private int mSelectPosition;//当前选中位置
    private BottomNavigationItemClickListener mListener;
    private boolean mBehaviorEnable = true;//是否启用behavior
    private boolean mBadgeAutoHide = true;//当item选中时badge是否自动隐藏
    private Typeface mTypeface;//item文字字体
    private CoordinatorLayout.Behavior mBehavior;//behavior

    public static final int ANIMATION_DURATION = 250;//全局动画时间
    public static final int BADGE_ANIMATION_DURATION = 150;//badge动画时间

    ///////////////////////////////////////////////////////////////////////////
    // 默认构造函数和初始化方法
    ///////////////////////////////////////////////////////////////////////////
    public BottomNavigation(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public BottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public BottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mContext = context;
        mResources = mContext.getResources();

        /*
        //初始化menu.xml中定义的
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigation, defStyleAttr, defStyleRes);
        int menuResId = array.getResourceId(R.styleable.BottomNavigation_bn_entries, 0);
        if (menuResId != 0) {
            inflateMenu(menuResId);
        }
        array.recycle();
        */


        //获得高度
        mHeight = mResources.getDimensionPixelSize(R.dimen.bottom_navigation_height);
        mShadowHeight = mResources.getDimensionPixelSize(R.dimen.bottom_navigation_shadow_height);
//        mTextColorActive = MiscUtils.fetchContextColor(mContext, android.R.attr.colorPrimary);
//        mTextColorInActive = Color.LTGRAY;
//        mBackgroundColor = Color.WHITE;

        mScreenWidth = MiscUtils.getScreenWidth(mContext);



        /*使用代码设置BottomNavigation
        RelativeLayout.LayoutParams shadowParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, shadowHeight);
        //shadowParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        View shadowView = new View(mContext);
        shadowView.setId(1);
        shadowView.setBackgroundResource(R.drawable.bottom_navigation_shadow);
        addView(shadowView,shadowParams);

        RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mHeight);
        containerParams.addRule(RelativeLayout.BELOW,1);
        container = new FrameLayout(mContext);
        addView(container,containerParams);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mHeight);
        //设置背景ripple动画的View对象
        backgroundOverlay = new View(mContext);
        backgroundOverlay.setLayoutParams(params);
        backgroundOverlay.setVisibility(View.GONE);
        container.addView(backgroundOverlay);

        //初始化itemView的父级容器
        itemContainer = new LinearLayout(mContext);
        itemContainer.setLayoutParams(params);
        itemContainer.setGravity(Gravity.CENTER);
        itemContainer.setOrientation(LinearLayout.HORIZONTAL);
        container.addView(itemContainer);
        */


        //初始化BottomNavigation布局
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.bottom_navigation, this, false);
        addView(view);

        mContainer = (FrameLayout) view.findViewById(R.id.bottom_navigation_container);
        mBackgroundOverlay = view.findViewById(R.id.bottom_navigation_background_overlay);
        mItemContainer = (LinearLayout) view.findViewById(R.id.bottom_navigation_item_container);


        //设置高度
        ViewCompat.setElevation(this, mResources.getDimension(R.dimen.bottom_navigation_elevation));

        setClipToPadding(false);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", superState);
        bundle.putInt("selectPosition", mSelectPosition);
        bundle.putSerializable("badgeStates", (Serializable) badgeStates);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mSelectPosition = bundle.getInt("selectPosition");
            badgeStates = (List<BadgeState>) bundle.getSerializable("badgeStates");
            state = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //初始化item视图
        initItemView();
    }


    private void initItemView() {
        if (items.size() == 0) {
            return;
        }

        //清除原始数据
        itemViews.clear();
        mItemContainer.removeAllViews();

        //背景模式为Ripple模式时设置背景颜色
        if (mBackgroundStyle == BACKGROUND_STYLE_RIPPLE) {
            //Ripple模式设置背景
            mContainer.setBackgroundColor(ContextCompat.getColor(mContext,items.get(mSelectPosition).getColor()));
        } else {
            //Fixed模式设置背景白色
            mContainer.setBackgroundColor(Color.WHITE);
        }


        //初始化item的view
        if (mMode == MODE_FIXED) {
            //计算item的view的宽
            int itemWidth = getMeasurementsForFixedMode();
            for (int i = 0; i < items.size(); i++) {
                FixedBottomNavigationItemView itemView = new FixedBottomNavigationItemView(mContext);
                itemView.setItem(items.get(i),mBackgroundStyle);
                itemView.setTag(i);
                itemView.setOnClickListener(this);
                itemView.setItemWidth(itemWidth);
                itemView.setTypeFace(mTypeface);
                itemView.setBadgeStyle(mBadgeStyle);
                itemView.getBadge().setBadgeState(badgeStates.get(i));
                itemViews.add(itemView);
                mItemContainer.addView(itemView);
            }
            itemViews.get(mSelectPosition).setActive(false);
        } else if (mMode == MODE_SHIFTING) {
            int[] widths = getMeasurementsForShiftingMode();
            int itemWidth = widths[0];
            int itemActiveWidth = widths[1];
            for (int i = 0; i < items.size(); i++) {
                ShiftingBottomNavigationItemView itemView = new ShiftingBottomNavigationItemView(mContext);
                itemView.setItem(items.get(i),mBackgroundStyle);
                itemView.setTag(i);
                itemView.setOnClickListener(this);
                itemView.setItemWidth(itemWidth, itemActiveWidth);
                itemView.setTypeFace(mTypeface);
                itemView.setBadgeStyle(mBadgeStyle);
                itemView.getBadge().setBadgeState(badgeStates.get(i));
                itemViews.add(itemView);
                mItemContainer.addView(itemView);
            }
            itemViews.get(mSelectPosition).setActive(false);
        } else {
            //默认风格的item的view
        }


//        mBehavior = new BBottomNavigationBehavior(mHeight, 0, true, false);
        mBehavior = new BottomNavigationBehavior(mHeight,true);
        if (mBehaviorEnable){
            ((CoordinatorLayout.LayoutParams) getLayoutParams())
                    .setBehavior(mBehavior);
        }

    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int oldPosition = mSelectPosition;
        int newPosition = (int) v.getTag();
        if (oldPosition == newPosition) {
            if (mListener !=null){
                mListener.onBottomNavigationItemReselected(mSelectPosition);
            }
        } else {
            itemViews.get(oldPosition).setInactive(true);
            itemViews.get(newPosition).setActive(true);
            if (mBackgroundStyle == BACKGROUND_STYLE_RIPPLE) {
                MiscUtils.setBackgroundWithRipple(itemViews.get(newPosition), mContainer, mBackgroundOverlay, ContextCompat.getColor(mContext,items.get(newPosition).getColor()));
            }
            if (mBadgeAutoHide) {
                itemViews.get(newPosition).getBadge().hide();
            }
            mSelectPosition = newPosition;
            if (mListener !=null){
                mListener.onBottomNavigationItemSelected(mSelectPosition);
            }
        }

    }


    ///////////////////////////////////////////////////////////////////////////
    // 解析XML中定义的menu
    ///////////////////////////////////////////////////////////////////////////
    /*
    private void inflateMenu(int menuRes) {
        BottomNavigationItem item = null;

        XmlResourceParser parser = null;
        try {
            parser = mContext.getResources().getLayout(menuRes);
            AttributeSet attrs = Xml.asAttributeSet(parser);

            int eventType = parser.getEventType();
            String tagName;
            boolean lookingForEndOfUnknownTag = false;
            String unknownTagName = null;

            // This loop will skip to the menu start tag
            do {
                if (eventType == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("menu")) {
                        // Go to next tag
                        eventType = parser.next();
                        break;
                    }

                    throw new RuntimeException("Expecting menu, got " + tagName);
                }
                eventType = parser.next();
            } while (eventType != XmlPullParser.END_DOCUMENT);


            boolean reachedEndOfMenu = false;
            while (!reachedEndOfMenu) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (lookingForEndOfUnknownTag) {
                            break;
                        }

                        tagName = parser.getName();
                        if (tagName.equals("item")) {
                            item = readItem(attrs);
                        } else {
                            lookingForEndOfUnknownTag = true;
                            unknownTagName = tagName;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if (lookingForEndOfUnknownTag && tagName.equals(unknownTagName)) {
                            lookingForEndOfUnknownTag = false;
                            unknownTagName = null;
                        } else if (tagName.equals("item")) {
                            // Add the item if it hasn't been added (if the item was
                            // a submenu, it would have been added already)
                            if (item != null) {
                                items.add(item);
                            }
                        } else if (tagName.equals("menu")) {
                            reachedEndOfMenu = true;
                        }
                        break;

                    case XmlPullParser.END_DOCUMENT:
                        throw new RuntimeException("Unexpected end of document");
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            throw new InflateException("Error inflating menu XML", e);
        } catch (IOException e) {
            throw new InflateException("Error inflating menu XML", e);
        } finally {
            if (parser != null) parser.close();
        }
    }

    private BottomNavigationItem readItem(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.BottomNavigationItem);

        int itemId = a.getResourceId(R.styleable.BottomNavigationItem_android_id, 0);
        CharSequence itemTitle = a.getText(R.styleable.BottomNavigationItem_android_title);
        int icon = a.getResourceId(R.styleable.BottomNavigationItem_android_icon, 0);
        int color = a.getColor(R.styleable.BottomNavigationItem_android_color, 0);
        a.recycle();

        BottomNavigationItem item = new BottomNavigationItem(itemId, itemTitle.toString(), icon, color);
        return item;
    }
    */

    ///////////////////////////////////////////////////////////////////////////
    // 计算itemView宽
    ///////////////////////////////////////////////////////////////////////////
    public int getMeasurementsForFixedMode() {
        int itemWidth = mScreenWidth / items.size();
        int minWidth = mResources.getDimensionPixelSize(R.dimen.fixed_bottom_navigation_item_min_width);
        int maxWidth = mResources.getDimensionPixelSize(R.dimen.fixed_bottom_navigation_item_max_width);
        if (itemWidth < minWidth) {
            itemWidth = minWidth;
        } else if (itemWidth > maxWidth) {
            itemWidth = maxWidth;
        }
        return itemWidth;
    }

    public int[] getMeasurementsForShiftingMode() {
        int[] result = new int[2];
        int minWidth = mResources.getDimensionPixelSize(R.dimen.shifting_bottom_navigation_item_min_width_inactive);
        int minWidthActive = mResources.getDimensionPixelSize(R.dimen.shifting_bottom_navigation_item_min_width_active);
        int maxWidth = mResources.getDimensionPixelSize(R.dimen.shifting_bottom_navigation_item_max_width_inactive);
        int maxWidthActive = mResources.getDimensionPixelSize(R.dimen.shifting_bottom_navigation_item_max_width_active);

        int num = items.size();
        int minPossibleWidth = minWidth * (num - 1) + minWidthActive;
        int maxPossibleWidth = maxWidth * (num - 1) + maxWidthActive;
        int itemWidth = 0;
        int itemActiveWidth = 0;

        if (mScreenWidth <= minPossibleWidth) {
            itemWidth = minWidth;
            itemActiveWidth = minWidthActive;
        } else if (mScreenWidth >= maxPossibleWidth) {
            itemWidth = maxWidth;
            itemActiveWidth = maxWidthActive;
        } else {
            itemWidth = (int) (mScreenWidth / (num + 0.5));
            itemActiveWidth = (int) (itemWidth * 1.5);
        }

        result[0] = itemWidth;
        result[1] = itemActiveWidth;

        return result;
    }


    ///////////////////////////////////////////////////////////////////////////
    // 获得方法
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 获得选中的视图
     *
     * @param position
     */
    public BottomNavigationItemView getItemView(int position) {
        return itemViews.get(position);
    }


    ///////////////////////////////////////////////////////////////////////////
    // 设置方法
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 添加菜单
     *
     * @param item
     */
    public BottomNavigation addItem(BottomNavigationItem item) {
        items.add(item);
        //添加通知的小圆点标记的默认状态
        badgeStates.add(new BadgeState());
        return this;
    }

    /**
     * 设置item模式
     *
     * @param mode
     */
    public void setMode(int mode) {
        mMode = mode;
    }

    /**
     * 设置背景动画风格
     *
     * @param backgroundStyle
     */
    public void setBackgroundStyle(int backgroundStyle) {
        this.mBackgroundStyle = backgroundStyle;
    }

    /**
     * 设置badge风格
     *
     * @param badgeStyle
     */
    public void setBadgeStyle(int badgeStyle) {
        this.mBadgeStyle = badgeStyle;
    }

    /**
     * 设置初始选中项
     *
     * @param position
     */
    public void setFirstSelectPostion(int position) {
        mSelectPosition = position;
    }

//    /**
//     * 设置选中项
//     *
//     * @param position
//     */
//    public void setSelectPosition(int position) {
////        mSelectPosition = position;
//    }

    /**
     * 设置BottomNavigation的listener
     * @param listener
     */
    public void setOnBottomNavigationClickListener(BottomNavigationItemClickListener listener){
        mListener = listener;
    }


    /**
     * 设置badge选中时自动隐藏
     *
     * @param autoHide
     */
    public void setBadgeAutoHide(boolean autoHide) {
        mBadgeAutoHide = autoHide;
    }


    /**
     * 设置badge显示
     *
     * @param position
     */
    public void showBadge(int position) {
        badgeStates.get(position).setShow(true);

        if (itemViews.size() == 0) {
            return;
        }
        itemViews.get(position).getBadge().show(null);
    }


    /**
     * 设置badge显示，带数字
     *
     * @param position
     */
    public void showBadge(int position, String numText) {
        int num = Integer.parseInt(numText);
        if (num > 99){
            numText = "...";
        }
        badgeStates.get(position).setShow(true);
        badgeStates.get(position).setText(numText);

        if (itemViews.size() == 0) {
            return;
        }
        itemViews.get(position).getBadge().show(numText);
    }

    /**
     * 设置BottomNavigation是否自动隐藏
     *
     * @param enable
     */
    public void setBehaviorEnable(boolean enable) {
        this.mBehaviorEnable = enable;
    }


    /**
     * 设置字体
     *
     * @param typeFacePath 字体名称
     */
    public void setTypeFace(String typeFacePath) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(),
                typeFacePath);

    }

    /**
     * 设置BottomNavigation显示隐藏，临时使用
     *
     * @param visible 是否显示
     */
    public void toggleVisibility(boolean visible) {
        BBottomNavigationBehavior<BottomNavigation> from = BBottomNavigationBehavior.from(this);
        if (from != null) {
            from.setHidden(this, visible);
        }
    }

    /**
     * badge的状态类
     */
    public static class BadgeState implements Serializable {
        private boolean show;
        private String text;

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
