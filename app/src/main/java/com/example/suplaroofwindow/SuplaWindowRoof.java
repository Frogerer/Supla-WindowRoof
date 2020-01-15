package com.example.suplaroofwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
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
    private float mPercentX, mPercentY, mPointX, mPointY;
    private RectF oval3 = new RectF();
    private RectF oval4 = new RectF();
    private float mPointAfterRotationR1, mPointAfterRotationR2, mPointAfterRotationL1, mPointAfterRotationL2;

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

    //Rotacja Y
    public void setPercentX(float percent) {
        if (percent < 0) {
            percent = 0;
        } else if (percent > 100) {
            percent = 100;
        }
        mPercentX = percent;
        invalidate();
    }

    //Rotacja 360
    public void setPercentY(float percent) {
        if (percent < 0) {
            percent = 0;
        } else if (percent > 360) {
            percent = 360;
        }
        mPercentY = percent;
        invalidate();
    }

    //Punkt 360
    public void setPoint(float percent) {
        float angle;
        if (percent < 0) {
            percent = 0;
        } else if (percent > 360) {
            percent = 360;
        }
        angle = (float) (percent * Math.PI / 180);
        mPointY = (100) * 5.18f * (float) Math.cos(angle);
        mPointX = (100 - mPercentX) * 5.18f * (float) Math.sin(angle);
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

    @Override
    public void onDraw(Canvas canvas) {

        //Variables
        float mWidth = getWidth();
        float mHeight = getHeight();
        float cPW = mWidth * 0.5f;
        float cPH = mHeight * 0.5f;
        Log.i("TAG","mWidth: " + mWidth);
        Log.i("TAG","mHeight: " + mHeight);
        float radiusBC = mWidth * 0.48f;
        float radiusSC = mWidth * 0.27f;

        //Black
        blackPaint.setColor(mBlackColor);
        blackPaint.setStrokeWidth(1);
        blackPaint.setStyle(Paint.Style.STROKE);

        //White
        whitePaint.setColor(mWhiteColor);
        whitePaint.setStrokeWidth(5);
        whitePaint.setStyle(Paint.Style.STROKE);

        //Green
        greenPaint.setColor(mGreenColor);
        greenPaint.setStrokeWidth(5);
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
        canvas.rotate(mPercentY, cPW, cPH);





/*
        oval4.set((int) (cPW - radiusSC + (mPercentX * (radiusSC/100)-1)), (int) (cPH - radiusSC), (int) (cPW + radiusSC - (mPercentX * (radiusSC/100)-1)), (int) (cPH + radiusSC));
        canvas.drawOval(oval4, greenPaint);
*/
        canvas.restore();


        // (1080 * 0,5) - (1080 * 0,3) + (100 * 3.25) = 540 - 324 + 325
        // 540 - 291,6 + 292,5

        //Point circle
        canvas.save();
        canvas.rotate(mPercentY, mWidth * 0.4f, cPH);
        oval3.set((int) (mWidth * 0.4f - radiusBC + (mPercentX * (radiusBC/100)-1)), (int) (cPH - radiusBC), (int) (mWidth * 0.4f + radiusBC - (mPercentX * (radiusBC/100)-1)), (int) (cPH + radiusBC));
        canvas.drawOval(oval3, greenPaint);
        canvas.drawCircle(mWidth * 0.4f + mPointX,cPH + mPointY,20,whitePaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(mPercentY, mWidth * 0.6f, cPH);
        oval4.set((int) (mWidth * 0.6f - radiusBC + (mPercentX * (radiusBC/100)-1)), (int) (cPH - radiusBC), (int) (mWidth * 0.6f + radiusBC - (mPercentX * (radiusBC/100)-1)), (int) (cPH + radiusBC));
        canvas.drawOval(oval4, greenPaint);
        canvas.drawCircle(mWidth * 0.6f + mPointX,cPH + mPointY,20,whitePaint);
        canvas.restore();

        //lewe
        canvas.save();
        canvas.rotate(mPercentY, mWidth * 0.4f, cPH);
        canvas.drawLine(
                (int) (mWidth * 0.4f),
                (int) (mHeight * 0.5f),
                (int) (mWidth * 0.4f + mPointX),
                (int) (cPH + mPointY),
                whitePaint);
        mPointAfterRotationL1 = (mWidth * 0.4f + mPointX);
        mPointAfterRotationL2 = (cPH + mPointY);
        Log.i("TAG", "Punkt L1 " + mPointAfterRotationL1);
        Log.i("TAG", "Punkt L2 " + mPointAfterRotationL2);
        canvas.restore();

        //prawe
        canvas.save();
        canvas.rotate(mPercentY, mWidth * 0.6f, cPH);
        canvas.drawLine(
                (int) (mWidth * 0.6f),
                (int) (mHeight * 0.5f),
                (int) (mWidth * 0.6f + mPointX),
                (int) (cPH + mPointY),
                whitePaint);
        mPointAfterRotationR1 = (mWidth * 0.6f + mPointX);
        mPointAfterRotationR2 = (cPH + mPointY);
        canvas.restore();

        //belka
        canvas.save();
        canvas.rotate(mPercentY, mWidth * 0.5f, cPH);
        canvas.drawLine(
                (int) (mWidth * 0.4f + mPointX),
                (int) (cPH + mPointY),
                (int) (mWidth * 0.6f + mPointX),
                (int) (cPH + mPointY),
                whitePaint);
        //canvas.restore();

//L

//R

//T
        canvas.drawLine(
                (int) (mWidth * 0.4f),
                (int) (mHeight * 0.5f),
                (int) (mWidth * 0.6f),
                (int) (mHeight * 0.5f),
                whitePaint);
//D




        //Win Frame
// L
        canvas.drawLine(
                (int) (mWidth * 0.38f),
                (int) (mHeight * 0.48f),
                (int) (mWidth * 0.43f),
                (int) (mHeight * 0.92f),
                whitePaint);
//R
        canvas.drawLine(
                (int) (mWidth * 0.62f),
                (int) (mHeight * 0.48f),
                (int) (mWidth * 0.67f),
                (int) (mHeight * 0.92f),
                whitePaint);
//T
        canvas.drawLine(
                (int) (mWidth * 0.38f),
                (int) (mHeight * 0.48f),
                (int) (mWidth * 0.62f),
                (int) (mHeight * 0.48f),
                whitePaint);
//R
        canvas.drawLine(
                (int) (mWidth * 0.43f),
                (int) (mHeight * 0.92f),
                (int) (mWidth * 0.67f),
                (int) (mHeight * 0.92f),
                whitePaint);

        /*
        //Window Line Left
        canvas.drawLine(
                (int) (mWidth * 0.3f),
                (int) (mHeight * 0.3f),
                (int) (mWidth * 0.4f),
                (int) (mHeight * 0.7f),
                whitePaint);

        //Window Line Right
        canvas.drawLine(
                (int) (mWidth * 0.5f),
                (int) (mHeight * 0.3f),
                (int) (mWidth * 0.6f),
                (int) (mHeight * 0.7f),
                whitePaint);

        //Window Line Top
        canvas.drawLine(
                (int) (mWidth * 0.3f),
                (int) (mHeight * 0.3f),
                (int) (mWidth * 0.5f),
                (int) (mHeight * 0.3f),
                whitePaint);

        //Window Line Down
        canvas.drawLine(
                (int) (mWidth * 0.4f),
                (int) (mHeight * 0.7f),
                (int) (mWidth * 0.6f),
                (int) (mHeight * 0.7f),
                whitePaint);

        //Window Line Small Left
        canvas.drawLine(
                (int) (mWidth * 0.32f),
                (int) (mHeight * 0.32f),
                (int) (mWidth * 0.41f),
                (int) (mHeight * 0.68f),
                whitePaint);

        //Window Line Small Right
        canvas.drawLine(
                (int) (mWidth * 0.49f),
                (int) (mHeight * 0.32f),
                (int) (mWidth * 0.58f),
                (int) (mHeight * 0.68f),
                whitePaint);

        //Window Line Small Top
        canvas.drawLine(
                (int) (mWidth * 0.32f),
                (int) (mHeight * 0.32f),
                (int) (mWidth * 0.49f),
                (int) (mHeight * 0.32f),
                whitePaint);

        //Window Line Small Top
        canvas.drawLine(
                (int) (mWidth * 0.41f),
                (int) (mHeight * 0.68f),
                (int) (mWidth * 0.58f),
                (int) (mHeight * 0.68f),
                whitePaint);
        */
    }
}
