/*
package com.subway.railme.home;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.appcompat.widget.AppCompatImageView;

public class ZoomableImageView extends AppCompatImageView {

    private Matrix matrix = new Matrix();
    private float scale = 1.0f;

    private static final float MIN_SCALE = 0.1f;
    private static final float MAX_SCALE = 5.0f;

    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    public ZoomableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setScaleType(ScaleType.MATRIX); // 이미지 뷰의 스케일 타입을 Matrix로 설정
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        matrix.setScale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        setImageMatrix(matrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(MIN_SCALE, Math.min(scale, MAX_SCALE));

            matrix.setScale(scale, scale, getWidth() / 2f, getHeight() / 2f); // 이미지 중심을 기준으로 확대/축소
            setImageMatrix(matrix);

            return true;
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (scale < MAX_SCALE) {
                scale *= 2.0f;
                matrix.setScale(scale, scale, getWidth() / 2f, getHeight() / 2f); // 이미지 중심을 기준으로 확대
                setImageMatrix(matrix);
            } else {
                scale = 1.0f;
                matrix.reset(); // 이미지를 원래 크기로 되돌림
                setImageMatrix(matrix);
            }
            return true;
        }
    }
}
*/