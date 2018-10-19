package com.mhky.yaolinwang.customview;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.cb.ratingbar.CBRatingBar;

import java.math.BigDecimal;

/**
 * Created by Administrator  on  2018/10/12
 * Describe：
 */
public class CustomRatingBar extends CBRatingBar{

    int starSize = getStarSize();
    int starSpace = getStarSpace();
    int touchCount = getTouchCount();
    int starCount = getStarCount();
    float starMaxProgress = getStarMaxProgress();
    private OnStarTouchListener onStarTouchListener;

    public CustomRatingBar(Context context) {
        super(context);
    }

    public CustomRatingBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                checkTouch(new PointF(event.getX(), event.getY()));
                break;
        }
        return true;
    }

    private void checkTouch(PointF touch) {
//        if (touch.x > (starSize * starCount + (starCount - 1) * starSpace) || touch.y > starSize) {
//            return;
//        }
        int nowTouchCount;
        float nowTouchCount_new;
        nowTouchCount = (int) (touch.x / (starSize + starSpace)) + 1;
        nowTouchCount_new = touch.x / (starSize + starSpace);
        if (touch.x > (nowTouchCount * (starSize + starSpace) - starSpace)) {
            nowTouchCount = 0;
        }
        if (nowTouchCount > 0) {
            touchCount = nowTouchCount;
            if (getCoverDir() == CoverDir.rightToLeft) {
                touchCount = starCount - touchCount + 1;
            }
//            setStarProgress(starMaxProgress / starCount * touchCount);
            BigDecimal b = new BigDecimal(nowTouchCount_new);
            float f = b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
            Log.e("移动",touch.x + "--" + starSize + "--" + nowTouchCount_new + "--" + f);
            if(f > 4.5){
                f = 5.0f;
                setStarProgress(100);
            }else if(f < 0) {
                f = 0f;
            }else {
                setStarProgress((starMaxProgress / starCount * f));
            }
            if (onStarTouchListener != null) {
//                onStarTouchListener.onStarTouch(touchCount);
                onStarTouchListener.onStarTouch(f);
            }
        }
    }

    public CustomRatingBar setStarTouchListener(OnStarTouchListener onStarTouchListener){
        this.onStarTouchListener = onStarTouchListener;
        return this;
    }

    public interface OnStarTouchListener{
        void onStarTouch(float touchCount);
    }
}
