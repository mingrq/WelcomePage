package com.ming.welcome_lib;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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


    /**
     * 指示器间距
     */
    private float indicatorMargin;
    private int indicatorEn;

    private TypedArray array;
    private Activity activity;
    private ViewPager viewPager;
    private LinearLayout indicator;
    private List<Info> infoList;
    private List<ImageView> imageViewList;
    private Drawable drawable;

    public WelcomePage(@NonNull Context context) {
        this(context, null);
    }

    public WelcomePage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WelcomePage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.welcome, this);
        viewPager = findViewById(R.id.vp_welcome);
        indicator = findViewById(R.id.indicator);
        array = context.obtainStyledAttributes(attrs, R.styleable.WelcomePage);
        init();
    }

    private void init() {
        setIndicatorSelecter(array.getResourceId(R.styleable.WelcomePage_indicatorSelecter, R.drawable.selecter_slideindicator));
        setIndicatorBgColor(array.getColor(R.styleable.WelcomePage_indicatorBgColor, 0x2A000000));
        setIndicatorSize(array.getDimension(R.styleable.WelcomePage_indicatorSize, dp2px(30)));
        setIndicatorHeight(array.getDimension(R.styleable.WelcomePage_indicatorHeight, dp2px(50)));
        setIndicatorMargin(array.getDimension(R.styleable.WelcomePage_indicatorMargin, dp2px(6)));
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
     * 获取指示器选择器
     */
    public float getIndicatorMargin() {
        return indicatorMargin;
    }

    /**
     * 设置指示器选择器
     */
    public void setIndicatorMargin(float indicatorMargin) {
        this.indicatorMargin = indicatorMargin;
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
        Drawable normal = activity.getResources().getDrawable(unIndicator);
        Drawable checked = activity.getResources().getDrawable(indicator);
        drawable.addState(new int[]{android.R.attr.enabled}, checked);
        drawable.addState(new int[]{-android.R.attr.enabled}, normal);
    }


    /**
     * 设置指示器
     *
     * @param indicatorSelecter 状态选择器
     */
    public void setIndicatorSelecter(int indicatorSelecter) {
        this.indicatorSelecter = indicatorSelecter;
        drawable = activity.getResources().getDrawable(indicatorSelecter);
    }

    /**
     * 设置轮播图数据
     *
     * @param infoList
     */
    public void setData(List<Info> infoList) {
        this.infoList = infoList;
    }

    public void commit() {
        initImage();
        initIndicator();
        bindingViewPager();
        viewPager.setAdapter(new WelcomePagerAdapter());
    }
/**
 * -------------------------------------------------------------------------------------------------
 */
    /**
     * dp转px
     */
    private int dp2px(float dpValues) {
        dpValues = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValues, activity.getResources().getDisplayMetrics());
        return (int) (dpValues + 0.5f);
    }

    /**
     * viewpager绑定指示器
     */
    private void bindingViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                indicator.getChildAt(i).setEnabled(true);
                indicator.getChildAt(indicatorEn).setEnabled(false);
                indicatorEn = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    /**
     * 初始化图片
     */
    private void initImage() {
        imageViewList = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            String uri = infoList.get(i).bannerUri;
            ImageView imageView = new ImageView(activity);
            imageView.setId(i);
            imageView.setBackgroundColor(0x303F9F);
            if (activity != null && imageView != null) {
                Picasso.with(activity).load(uri).error(R.drawable.unslideindicator).into(imageView);
                imageViewList.add(imageView);
            }
        }
    }

    /**
     * 初始化指示器
     */
    private void initIndicator() {
        for (int k = 0; k < infoList.size(); k++) {
            View vIndicator = new View(activity);
            vIndicator.setId(k);
            int size = (int) (indicatorSize + 0.5f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.leftMargin = (int) (indicatorMargin / 2 + 0.5f);
            params.rightMargin = (int) (indicatorMargin / 2 + 0.5f);
            vIndicator.setLayoutParams(params);
            vIndicator.setBackgroundResource(indicatorSelecter);
            vIndicator.setEnabled(false);
            indicator.addView(vIndicator);
        }
        indicatorEn = 0;
        indicator.getChildAt(indicatorEn).setEnabled(true);
    }


    /**
     * =================================================================
     * <p>
     * 轮播图适配器
     * =================================================================
     */

    class WelcomePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return infoList == null ? 0 : infoList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView view = imageViewList.get(position);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(imageViewList.get(position));
        }
    }
}
