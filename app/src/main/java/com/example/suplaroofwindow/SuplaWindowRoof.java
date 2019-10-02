package com.example.suplaroofwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SuplaWindowRoof extends View {

    //Variables
    private final Paint paint = new Paint();
    private final Paint linePaint = new Paint();
    private final Rect MainRect = new Rect();
    private int mLineColor = 0x049629;

    public SuplaWindowRoof(Context context) { super(context); }

    public SuplaWindowRoof(Context context, AttributeSet attrs) { super(context, attrs); }

    public SuplaWindowRoof(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

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

        //Paint Stroke Line
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(mLineColor);

        //Paint Fill
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(mLineColor);

        float mWidth = getWidth();
        float mHeight = getHeight();

        Log.i("TAG", "mWidth: " + mWidth);
        Log.i("TAG", "mHeight: " + mHeight);

        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(0, 0, 0));
        myPaint.setStrokeWidth(5);

        MainRect.set((int) (mWidth * 0.4f), (int) (mHeight * 0.2f), (int) (mWidth * 0.6f), (int) (mHeight * 0.8f));
        canvas.drawRect(MainRect, linePaint);

        //Window line
        canvas.drawLine(
                (int) (mWidth * 0.3f),
                (int) (mHeight * 0.3f),
                (int) (mWidth * 0.4f),
                (int) (mHeight * 0.7f),
                myPaint);

        canvas.drawLine(
                (int) (mWidth * 0.5f),
                (int) (mHeight * 0.3f),
                (int) (mWidth * 0.6f),
                (int) (mHeight * 0.7f),
                myPaint);

        canvas.drawLine(
                (int) (mWidth * 0.3f),
                (int) (mHeight * 0.3f),
                (int) (mWidth * 0.5f),
                (int) (mHeight * 0.3f),
                myPaint);

        canvas.drawLine(
                (int) (mWidth * 0.4f),
                (int) (mHeight * 0.7f),
                (int) (mWidth * 0.6f),
                (int) (mHeight * 0.7f),
                myPaint);

        //Window rotate
        canvas.drawLine(
                (int) (mWidth * 0.32f),
                (int) (mHeight * 0.32f),
                (int) (mWidth * 0.41f),
                (int) (mHeight * 0.68f),
                myPaint);

        canvas.drawLine(
                (int) (mWidth * 0.49f),
                (int) (mHeight * 0.32f),
                (int) (mWidth * 0.58f),
                (int) (mHeight * 0.68f),
                myPaint);

        canvas.drawLine(
                (int) (mWidth * 0.32f),
                (int) (mHeight * 0.32f),
                (int) (mWidth * 0.49f),
                (int) (mHeight * 0.32f),
                myPaint);

        canvas.drawLine(
                (int) (mWidth * 0.41f),
                (int) (mHeight * 0.68f),
                (int) (mWidth * 0.58f),
                (int) (mHeight * 0.68f),
                myPaint);
    }
}
