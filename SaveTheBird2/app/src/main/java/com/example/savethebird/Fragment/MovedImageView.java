package com.example.savethebird.Fragment;
import android.content.Context;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;


public class MovedImageView extends AppCompatImageView {

    private OnLongClickListener onLongClickListener;
    private OnDoubleClickListener onDoubleClickListener;
    float downX, downY;
    float moveX, moveY;
    private int mode = 0;// initial
    private static final int MODE_DRAG = 1;//drag
    private static final int MODE_ZOOM = 2;//zoom
    private float spacing;
    private float scale = 1;
    private int count = 0;
    private long firstClick = 0;
    private long secondClick = 0;

    public MovedImageView(Context context) {
        super(context);
    }

    public void setOnLongClick(OnLongClickListener l)
    {
        this.onLongClickListener = l;
    }

    public void  setOnDoubleClick(OnDoubleClickListener l){
        this.onDoubleClickListener = l;
    }

    long time = 0;

    @Override
    public void setImageMatrix(Matrix matrix) {

        super.setImageMatrix(matrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction()& MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_DOWN) {
            count++;


            Log.d("count", "onTouchEvent: "+ count);
            if(count == 1){
            firstClick = System.currentTimeMillis();
                mode = MODE_DRAG;
                downX = event.getRawX();
                downY = event.getRawY();

            }
            else if(count ==2){
                secondClick = System.currentTimeMillis();
                if (secondClick - firstClick<1000){
                    Log.d("Mode", "onTouchEvent: Double Click ");
                    if(onDoubleClickListener != null){
                        onDoubleClickListener.onDoubleClick(this);
                    }
                    count = 0;
                    firstClick = 0;
                    return true;
                }
                else {
                    firstClick = secondClick;
                    count = 1;
                }


            }

            secondClick = 0;



        }

        if ( action == MotionEvent.ACTION_POINTER_DOWN){
            mode = MODE_ZOOM;
            spacing = getSpacing(event);
            Log.d("Mode", "Zoom ");

        }

        if (action == MotionEvent.ACTION_MOVE) {
            if(mode == MODE_DRAG){
                Log.d("Mode", "drag ");
            moveX = event.getRawX();
            moveY = event.getRawY();
            this.setX(getX() + (moveX - downX));
            this.setY(getY() + (moveY - downY));
            downX = moveX;
            downY = moveY;
            count = 0;
            }
            if(mode == MODE_ZOOM && event.getPointerCount()==2){
                scale = scale * getSpacing(event) / spacing;
                setScaleX(scale);
                setScaleY(scale);
            }
        }

        if(action == MotionEvent.ACTION_UP){
            mode = 0;
            long delay = System.currentTimeMillis()-time;
            if(delay > 2000){
                if(onLongClickListener != null){
                    onLongClickListener.onLongClick(this);
                }
            }
        }


        if(action == MotionEvent.ACTION_POINTER_UP){
            mode = 0;
        }
        return super.onTouchEvent(event);
    }


    public interface OnLongClickListener
    {
        void onLongClick(View view);
    }

    public interface OnDoubleClickListener{
        void onDoubleClick(View view);
    }

    // get spacing
    private float getSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }


}