package com.example.savethebird.Fragment;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

public class MovedImageView extends AppCompatImageView {

    private OnLongClickListener onLongClickListener;
    float downX, downY;
    float moveX, moveY;
    private int mode = 0;// 初始状态
    private static final int MODE_DRAG = 1;//平移
    private static final int MODE_ZOOM = 2;//缩放

    public MovedImageView(Context context) {
        super(context);
    }

    public void setOnLongClick(OnLongClickListener l)
    {
        this.onLongClickListener = l;
    }

    long time = 0;

    @Override
    public void setImageMatrix(Matrix matrix) {

        super.setImageMatrix(matrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mode = MODE_DRAG;
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
            mode = 0;
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            long delay = System.currentTimeMillis()-time;
            if(delay > 1000){
                if(onLongClickListener != null){
                    onLongClickListener.onLongClick(this);
                }
            }
        }
        if (event.getAction() == MotionEvent.ACTION_POINTER_DOWN){
            mode = MODE_ZOOM;



        }
        return super.onTouchEvent(event);
    }


    public interface OnLongClickListener
    {
        void onLongClick(View view);
    }

    private float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private PointF mid(MotionEvent event) {
        float midX = (event.getX(1) + event.getX(0)) / 2;
        float midY = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(midX, midY);
    }
}