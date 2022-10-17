package com.example.viewdemo;

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
        gestureDetectorCompat = new GestureDetectorCompat(context,
                new CustomGestureListener());
    }

    public class CustomGestureListener extends
    GestureDetector.SimpleOnGestureListener{

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            onLongClick();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
              final int SWIPE_DIST_THRESHOLD=10;
              final int SWIPE_VEL_THRESHOLD=50;
              float distX=e2.getX()-e1.getX();
              float distY=e2.getY()-e1.getY();

              if(Math.abs(distX)>Math.abs(distY)&&
                      Math.abs(distX)>SWIPE_DIST_THRESHOLD
              && Math.abs(velocityX)>SWIPE_VEL_THRESHOLD){
                  //horizontal swipe
                  if(distX>0){onRightSwipe();}
                  else{onLeftSwipe();}
              }
              else if(Math.abs(distY)>Math.abs(distX)&&
                      Math.abs(distY)>SWIPE_DIST_THRESHOLD
                      && Math.abs(velocityY)>SWIPE_VEL_THRESHOLD){
                  //vert swipe
                  if(distY>0){onDownSwipe();}
                  else{onUpSwipe();}
              }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;//should be true for activity to occur
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

    public void onUpSwipe() {
    }

    public void onDownSwipe() {
    }

    public void onLeftSwipe() {
    }

    public void onRightSwipe() {
    }

    public void onDoubleClick() {
    }

    public void onSingleClick() {
    }

    public void onLongClick() {
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetectorCompat.onTouchEvent(motionEvent);//imp
    }
}
