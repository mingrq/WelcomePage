package com.ming.welcome_lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2018/12/14 16:57
 */
public class WelcomePage extends FrameLayout {


    /**
     * 指示器区域高度
     */
    private float indicatorHeight;


    /**
     * 指示器背景色
     */
    private int indicatorBgColor;

    /**
     * 指示器大小
     */
    private float indicatorSize;


    /**
     * 指示器选择器
     */
    int indicatorSelecter;

    private TypedArray array;
    private Context context;
    private ViewPager viewPager;
    private RadioGroup indicator;
    private List<Info> infoList;

    public WelcomePage(@NonNull Context context) {
        this(context, null);
    }

    public WelcomePage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WelcomePage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.welcome, this);
        viewPager = findViewById(R.id.vp_welcome);
        indicator = findViewById(R.id.indicator);
        array = context.obtainStyledAttributes(attrs, R.styleable.WelcomePage);
        init();
    }

    private void init() {
        setIndicatorSelecter(array.getResourceId(R.styleable.WelcomePage_indicatorSelecter, R.drawable.selecter_slideindicator));
        setIndicatorBgColor(array.getColor(R.styleable.WelcomePage_indicatorBgColor, 0x2A000000));
        setIndicatorSize(array.getDimension(R.styleable.WelcomePage_indicatorSize, dp2px(10)));
        setIndicatorHeight(array.getDimension(R.styleable.WelcomePage_indicatorHeight, dp2px(50)));
        array.recycle();
    }

    /**
     * 获取指示器大小
     *
     * @return
     */
    public float getIndicatorSize() {
        return indicatorSize;
    }

    /**
     * 设置指示器大小
     *
     * @param indicatorSize
     */
    public void setIndicatorSize(float indicatorSize) {
        this.indicatorSize = indicatorSize;
    }

    /**
     * 获取指示器背景色
     */
    public int getIndicatorBgColor() {
        return indicatorBgColor;
    }

    /**
     * 设置指示器背景色
     */
    public void setIndicatorBgColor(int indicatorBgColor) {
        this.indicatorBgColor = indicatorBgColor;
        indicator.setBackgroundColor(indicatorBgColor);
    }

    /**
     * 获取指示器区域高度
     */
    public float getIndicatorHeight() {
        return indicatorHeight;
    }

    /**
     * 设置指示器区域高度
     */
    public void setIndicatorHeight(float indicatorHeight) {
        this.indicatorHeight = indicatorHeight;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) indicatorHeight);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        indicator.setLayoutParams(params);
    }

    /**
     * 设置指示器
     *
     * @param indicator   当前显示状态
     * @param unIndicator 未显示状态
     */
    public void setIndicatorSelecter(int indicator, int unIndicator) {
        StateListDrawable drawable = new StateListDrawable();
        Drawable normal = context.getResources().getDrawable(unIndicator);
        Drawable checked = context.getResources().getDrawable(indicator);
        drawable.addState(new int[]{android.R.attr.checkable}, checked);
        drawable.addState(new int[]{-android.R.attr.checkable}, normal);
    }

    /**
     * 设置指示器
     *
     * @param indicatorSelecter 状态选择器
     */
    public void setIndicatorSelecter(int indicatorSelecter) {
        this.indicatorSelecter = indicatorSelecter;
        Drawable drawable = context.getResources().getDrawable(indicatorSelecter);
    }

    /**
     * dp转px
     */
    private int dp2px(float dpValues) {
        dpValues = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValues, context.getResources().getDisplayMetrics());
        return (int) (dpValues + 0.5f);
    }

    /**
     * 设置轮播图数据
     *
     * @param infoList
     */
    public void setData(List<Info> infoList) {
        this.infoList = infoList;
    }


}
