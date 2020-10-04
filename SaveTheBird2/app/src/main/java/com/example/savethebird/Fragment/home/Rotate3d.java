package com.example.savethebird.Fragment.home;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;
//https://blog.csdn.net/android_mnbvcxz/article/details/78570594
//https://blog.csdn.net/beijingshi1/article/details/10142553
//https://blog.csdn.net/marrn/article/details/51758500?utm_medium=distribute.pc_relevant_download.none-task-blog-baidujs-1.nonecase&depth_1-utm_source=distribute.pc_relevant_download.none-task-blog-baidujs-1.nonecase

public class Rotate3d extends Animation {
    // Start angle
    private final float mFromDegrees;
    // End angle
    private final float mToDegrees;
    // Middle point
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    // Reverse effect
    private final boolean mReverse;
    // Camera
    private Camera mCamera;

    public Rotate3d(float fromDegrees, float toDegrees, float centerX,
                    float centerY, float depthZ, boolean reverse) {
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    // Generate Transformation
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        // Generate degrees
        float degrees = fromDegrees
                + ((mToDegrees - fromDegrees) * interpolatedTime);

        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;

        final Matrix matrix = t.getMatrix();

        camera.save();
        if (mReverse) {
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
        }
        camera.rotateY(degrees);
        // get transform matrix
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }

}
