package com.example.suplaroofwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SuplaWindowRoof extends View {

    //Variables
    private final Paint blackPaint = new Paint();
    private final Paint greenPaint =  new Paint();
    private final Paint whitePaint = new Paint();
    private int mBlackColor = 0x000000;
    private int mWhiteColor = 0xFFFFFF;
    private int mGreenColor = 0x05AA37;
    private float mPercentX, mPercentY;
    private RectF oval = new RectF();
    private RectF workSpace = new RectF();
    private float radAngle;
    private boolean rotateVersion;

    public SuplaWindowRoof(Context context) {
        super(context);
    }

    public SuplaWindowRoof(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuplaWindowRoof(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //public int getBlackColor() { return mBlackColor; }

    public void setBlackColor(int blackColor) {
        mBlackColor = blackColor;
        invalidate();
    }

    //public int getGreenColor() { return mGreenColor; }

    public void setGreenColor(int greenColor) {
        mGreenColor = greenColor;
        invalidate();
    }

    //public int getWhiteColor() { return mWhiteColor; }

    public void setWhiteColor(int whiteColor) {
        mWhiteColor = whiteColor;
        invalidate();
    }

    //Drawing line
    private void drawLine(Canvas canvas, float startX, float startY, float stopX, float stopY, Paint paint) {
        canvas.drawLine(workSpace.width() * startX + workSpace.left,
                        workSpace.height() * startY,
                        workSpace.width() * stopX + workSpace.left,
                        workSpace.height() * stopY,
                        paint);
    }

    //Drawing moving line
    private void drawMovingLine(Canvas canvas, float startX, float startY, float stopX, float stopY, Paint paint) {
        canvas.drawLine(startX + workSpace.left, startY,stopX + workSpace.left, stopY, paint);
    }

    //Drawing inner window frame
    private void drawFrame(Canvas canvas, float startX, float startY, float stopX, float stopY, float shift, Paint paint) {
        float radius, mPointX1, mPointY1, mPointX2, mPointY2;

        if (stopY >= startY) {
            radius = stopY - startY + shift;
        } else {
            radius = startY - stopY + shift;
        }

        mPointX1 = workSpace.height() * radius * (float) Math.cos(radAngle);
        mPointY1 = workSpace.height() * radius * (float) Math.sin(radAngle);
        mPointX2 = workSpace.height() * shift * (float) Math.cos(radAngle);
        mPointY2 = workSpace.height() * shift * (float) Math.sin(radAngle);

        drawMovingLine(canvas,
                workSpace.width() * startX + (mPointX2 * (mPercentX)),
                workSpace.height() * (startY - shift) - mPointY2,
                workSpace.width() * startX + (mPointX1 * (mPercentX)),
                workSpace.height() * (stopY - radius) - mPointY1,
                paint);

        drawMovingLine(canvas,
                workSpace.width() * stopX + (mPointX2 * (mPercentX)),
                workSpace.height() * (startY - shift) - mPointY2,
                workSpace.width() * stopX + (mPointX1 * (mPercentX)),
                workSpace.height() * (stopY - radius) - mPointY1,
                paint);

        drawMovingLine(canvas,
                workSpace.width() * startX + (mPointX1 * (mPercentX)),
                workSpace.height() * (stopY - radius) - mPointY1,
                workSpace.width() * stopX + (mPointX1 * (mPercentX)),
                workSpace.height() * (stopY - radius) - mPointY1,
                paint);

        drawMovingLine(canvas,
                workSpace.width() * startX + (mPointX2 * (mPercentX)),
                workSpace.height() * (startY - shift) - mPointY2,
                workSpace.width() * stopX + (mPointX2 * (mPercentX)),
                workSpace.height() * (startY - shift) - mPointY2,
                paint);
    }

    //Drawing outer window frame
    private void drawFrame(Canvas canvas, float startX, float startY, float stopX, float stopY, Paint paint) {
        float radius, mPointX, mPointY;

        if (rotateVersion) {
            if (stopY >= startY) {
                radius = (stopY - startY)/2;
            } else {
                radius = (startY - stopY)/2;
            }

            mPointX = workSpace.height() * radius * (float) Math.cos(radAngle);
            mPointY = workSpace.height() * radius * (float) Math.sin(radAngle);

            drawMovingLine(canvas,
                    workSpace.width() * startX - (mPointX * (mPercentX)),
                    workSpace.height() * (startY + radius) + mPointY,
                    workSpace.width() * startX + (mPointX * (mPercentX)),
                    workSpace.height() * (stopY - radius) - mPointY,
                    paint);

            drawMovingLine(canvas,
                    workSpace.width() * stopX - (mPointX * (mPercentX)),
                    workSpace.height() * (startY + radius) + mPointY,
                    workSpace.width() * stopX + (mPointX * (mPercentX)),
                    workSpace.height() * (stopY - radius) - mPointY,
                    paint);

            drawMovingLine(canvas,
                    workSpace.width() * startX + (mPointX * (mPercentX)),
                    workSpace.height() * (stopY - radius) - mPointY,
                    workSpace.width() * stopX + (mPointX * (mPercentX)),
                    workSpace.height() * (stopY - radius) - mPointY,
                    paint);

            drawMovingLine(canvas,
                    workSpace.width() * startX - (mPointX * (mPercentX)),
                    workSpace.height() * (startY + radius) + mPointY,
                    workSpace.width() * stopX - (mPointX * (mPercentX)),
                    workSpace.height() * (startY + radius) + mPointY,
                    paint);

            /*
            //ovals
            oval.set(workSpace.width() * startX - workSpace.height() * (radius * mPercentX) + workSpace.left,
                    workSpace.height() * startY,
                    workSpace.width() * startX + workSpace.height() * (radius * mPercentX) + workSpace.left,
                    workSpace.height() * stopY);
            canvas.drawOval(oval,greenPaint);

            //ovals
            oval.set(workSpace.width() * stopX - workSpace.height() * (radius * mPercentX) + workSpace.left,
                    workSpace.height() * startY,
                    workSpace.width() * stopX + workSpace.height() * (radius * mPercentX) + workSpace.left,
                    workSpace.height() * stopY);
            canvas.drawOval(oval,greenPaint);
             */

        } else {
            if (stopY >= startY) {
                radius = stopY - startY;
            } else {
                radius = startY - stopY;
            }

            mPointX = workSpace.height() * radius * (float) Math.cos(radAngle);
            mPointY = workSpace.height() * radius * (float) Math.sin(radAngle);

            drawMovingLine(canvas,
                    workSpace.width() * startX,
                    workSpace.height() * startY,
                    workSpace.width() * startX + (mPointX * (mPercentX)),
                    workSpace.height() * (stopY - radius) - mPointY,
                    paint);

            drawMovingLine(canvas,
                    workSpace.width() * stopX,
                    workSpace.height() * startY,
                    workSpace.width() * stopX + (mPointX * (mPercentX)),
                    workSpace.height() * (stopY - radius) - mPointY,
                    paint);

            drawMovingLine(canvas,
                    workSpace.width() * startX + (mPointX * (mPercentX)),
                    workSpace.height() * (stopY - radius) - mPointY,
                    workSpace.width() * stopX + (mPointX * (mPercentX)),
                    workSpace.height() * (stopY - radius) - mPointY,
                    paint);

            drawMovingLine(canvas,
                    workSpace.width() * startX,
                    workSpace.height() * startY,
                    workSpace.width() * stopX,
                    workSpace.height() * startY,
                    paint);

            /*
            //ovals
            oval.set(workSpace.width() * startX - workSpace.height() * (radius * mPercentX) + workSpace.left,
                    workSpace.height() * (startY - radius),
                    workSpace.width() * startX + workSpace.height() * (radius * mPercentX) + workSpace.left,
                    workSpace.height() * stopY);
            canvas.drawOval(oval,greenPaint);

            //ovals
            oval.set(workSpace.width() * stopX - workSpace.height() * (radius * mPercentX) + workSpace.left,
                    workSpace.height() * (startY - radius),
                    workSpace.width() * stopX + workSpace.height() * (radius * mPercentX) + workSpace.left,
                    workSpace.height() * stopY);
            canvas.drawOval(oval,greenPaint);
             */

        }
    }

    //Changing rotation axis point
    public void setAxis (boolean check) {
        rotateVersion = check;
    }

    //Changing opening percent
    public void setPoint(float percent) {
        if (percent < 0) {
            percent = 0;
        } else if (percent > 360) {
            percent = 360;
        }
        radAngle = (float) (percent * Math.PI / 180);
        invalidate();
    }

    //Changing 360 rotation
    public void setFullCircle(float percent) {
        if (percent < 0) {
            percent = 0;
        } else if (percent > 360) {
            percent = 360;
        }
        mPercentY = percent;
        invalidate();
    }

    //Changing vertical rotation
    public void setVertical(float percent) {
        if (percent < 0) {
            percent = 0;
        } else if (percent > 100) {
            percent = 100;
        }
        mPercentX = percent / 100;
        invalidate();
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return false;
    }

    private void calculateConstants() {
        workSpace = new RectF(0 + (getWidth()/2 * mPercentX),0, getWidth() - (getWidth()/2 * mPercentX), getHeight());
    }

    @Override
    public void onDraw(Canvas canvas) {

        calculateConstants();
        canvas.drawRect(workSpace, greenPaint);

        //Variables
        float FrameLineWidth = 5f;
        float mWidth = getWidth();
        float mHeight = getHeight();

        //Black
        blackPaint.setColor(mBlackColor);
        blackPaint.setStrokeWidth(1);
        blackPaint.setStyle(Paint.Style.STROKE);

        //White
        whitePaint.setColor(mWhiteColor);
        whitePaint.setStrokeWidth(FrameLineWidth);
        whitePaint.setStyle(Paint.Style.STROKE);

        //Green
        greenPaint.setColor(mGreenColor);
        greenPaint.setStrokeWidth(FrameLineWidth);
        greenPaint.setStyle(Paint.Style.STROKE);

        //Grid
        for(int i= 0; i<=10; i++) {
            canvas.drawLine(
                    (int) (mWidth * 0f),
                    (int) (mHeight * i / 10),
                    (int) (mWidth * 1f),
                    (int) (mHeight * i / 10),
                    blackPaint);

            canvas.drawLine(
                    (int) (mWidth * i / 10),
                    (int) (mHeight * 0f),
                    (int) (mWidth * i / 10),
                    (int) (mHeight * 1f),
                    blackPaint);
        }

        canvas.save();
        canvas.rotate(mPercentY, mWidth * 0.5f, mHeight * 0.5f);

        //Big frame
        drawLine(canvas, 0.2f, 0.1f, 0.2f, 0.9f, whitePaint);
        drawLine(canvas, 0.8f, 0.1f, 0.8f, 0.9f, whitePaint);
        drawLine(canvas, 0.2f, 0.1f, 0.8f, 0.1f, whitePaint);
        drawLine(canvas, 0.2f, 0.9f, 0.8f, 0.9f, whitePaint);

        //Moving outer frame
        drawFrame(canvas, 0.3f, 0.2f, 0.7f, 0.8f, greenPaint);

        //Moving inner frame
        if (rotateVersion) {
            drawFrame(canvas,0.35f, 0.25f, 0.65f, 0.75f, greenPaint);
        } else {
            drawFrame(canvas,0.35f, 0.25f, 0.65f, 0.75f, 0.05f, greenPaint);
        }

        canvas.restore();
    }
}
