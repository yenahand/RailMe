package com.subway.railme.home;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.appcompat.widget.AppCompatImageView;

public class ImageViewZoomable extends AppCompatImageView {

    private Matrix matrix = new Matrix();
    private float scale = 1.0f;

    private static final float MIN_SCALE = 0.1f;
    private static final float MAX_SCALE = 5.0f;

    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    public ImageViewZoomable(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.MATRIX);
        init(context);
    }

    private void init(Context context) {
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new GestureListener());
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

            matrix.setScale(scale, scale);
            setImageMatrix(matrix);

            return true;
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //더블탭하면 줌인
            if (scale < MAX_SCALE) {
                scale *= 2.0f;
                matrix.setScale(scale, scale, e.getX(), e.getY());
                setImageMatrix(matrix);
            } else {
               //이미 줌인 최대상태면 다시 돌아감
                scale = 1.0f;
                matrix.reset();
                setImageMatrix(matrix);
            }
            return true;
        }
    }
}
