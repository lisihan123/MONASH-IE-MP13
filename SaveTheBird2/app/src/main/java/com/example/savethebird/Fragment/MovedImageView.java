package com.example.savethebird.Fragment;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

public class MovedImageView extends AppCompatImageView {

    private OnLongClickListener onLongClickListener;
    float downX, downY;
    float moveX, moveY;

    public MovedImageView(Context context) {
        super(context);
    }

    public void setOnLongClick(OnLongClickListener l)
    {
        this.onLongClickListener = l;
    }

    long time = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getRawX();
            downY = event.getRawY();
            time = System.currentTimeMillis();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            moveX = event.getRawX();
            moveY = event.getRawY();

            this.setX(getX() + (moveX - downX));
            this.setY(getY() + (moveY - downY));
            downX = moveX;
            downY = moveY;
            time = System.currentTimeMillis();
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            long delay = System.currentTimeMillis()-time;
            if(delay > 100){
                if(onLongClickListener != null){
                    onLongClickListener.onLongClick(this);
                }
            }
        }
        return super.onTouchEvent(event);
    }


    public interface OnLongClickListener
    {
        void onLongClick(View view);
    }
}