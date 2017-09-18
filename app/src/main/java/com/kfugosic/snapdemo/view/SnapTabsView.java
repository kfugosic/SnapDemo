package com.kfugosic.snapdemo.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kfugosic.snapdemo.R;

/**
 * Created by Kristijan on 18.9.2017..
 */

public class SnapTabsView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ImageView centerImage;
    private ImageView startImage;
    private ImageView endImage;
    private ImageView bottomImage;
    private View indicator;

    // Minimum distance between bottom  circle and left/right icon
    private static final int MIN_DISTANCE = 80;

    private ArgbEvaluator argbEvaluator;
    private int centerColor;
    private int sideColor;

    private int endViewsTranslationX;
    private int indicatorTranslationX;
    private int centerTranslationY;


    public SnapTabsView(@NonNull Context context) {
        this(context,null);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_snap_tabs, this, true);
        centerImage = (ImageView) findViewById(R.id.vst_center_image);
        startImage = (ImageView) findViewById(R.id. vst_start_image);
        endImage = (ImageView) findViewById(R.id.vst_end_image);
        bottomImage = (ImageView) findViewById(R.id.vst_bottom_image);
        indicator = findViewById(R.id.vst_indicator);

        argbEvaluator = new ArgbEvaluator();

        centerColor = ContextCompat.getColor(getContext(), R.color.white);
        sideColor = ContextCompat.getColor(getContext(), R.color.dark_grey);

        // Convert 80 dip to pixels
        indicatorTranslationX = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                MIN_DISTANCE,
                getResources().getDisplayMetrics()
                );

        // Do calculation when bottom image is drawn
        bottomImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                endViewsTranslationX = (int)(bottomImage.getX() - startImage.getX() -  indicatorTranslationX);
                centerTranslationY = getHeight() - bottomImage.getBottom();
                bottomImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void setupWithViewPager(final ViewPager viewPager){
        viewPager.addOnPageChangeListener(this);
        startImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager.getCurrentItem() != 0){
                    viewPager.setCurrentItem(0);
                }
            }
        });
        endImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager.getCurrentItem() != 2){
                    viewPager.setCurrentItem(2);
                }
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position == 0) {
            setColor(1-positionOffset);
            moveViews(1-positionOffset);

            indicator.setTranslationX( -1 * (1-positionOffset) * indicatorTranslationX);

        } else if(position == 1){
            setColor(positionOffset);
            moveViews(positionOffset);

            indicator.setTranslationX(positionOffset * indicatorTranslationX);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    private void moveViews(float fractionFromCenter) {
        moveAndScaleCenter(fractionFromCenter);

        startImage.setTranslationX(fractionFromCenter * endViewsTranslationX);
        endImage.setTranslationX(-fractionFromCenter * endViewsTranslationX);

        indicator.setAlpha(fractionFromCenter);
        indicator.setScaleX(fractionFromCenter);

    }

    private void moveAndScaleCenter(float fractionFromCenter){
        float scale = 0.7f + ((1-fractionFromCenter) * .3f);

        centerImage.setScaleX(scale);
        centerImage.setScaleY(scale);

        int translation = (int) (fractionFromCenter * centerTranslationY);

        centerImage.setTranslationY(translation);
        bottomImage.setTranslationY(translation);

        bottomImage.setAlpha(1-fractionFromCenter);

    }

    private void setColor(float fractionFromCenter) {
        int color = (int) argbEvaluator.evaluate(fractionFromCenter, centerColor, sideColor);
        centerImage.setColorFilter(color);
        bottomImage.setColorFilter(color);
        startImage.setColorFilter(color);
        endImage.setColorFilter(color);
        indicator.setBackgroundColor(color);
    }

}
