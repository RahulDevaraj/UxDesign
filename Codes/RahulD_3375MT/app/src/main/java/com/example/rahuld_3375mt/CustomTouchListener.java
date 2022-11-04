package com.example.rahuld_3375mt;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;

public class CustomTouchListener implements View.OnTouchListener {
    GestureDetectorCompat gestureDetectorCompat;
    Context context;

    public CustomTouchListener(Context context) {
        this.context = context;
        gestureDetectorCompat=new GestureDetectorCompat(context,new CustomGestureListener());
    }

    public class CustomGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            onSingleClick();
            return super.onSingleTapConfirmed(e);
        }
    }

    public void onSingleClick() {
    }

    public void onDoubleClick() {
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetectorCompat.onTouchEvent(motionEvent);
    }
}
