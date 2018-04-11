package com.caah.mppclient.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * 作者：赵亚坤 on 2018/4/11 16:00
 */
public class RectOnCamera extends View {
    private static final String TAG = "CameraSurfaceView";
    private int mScreenWidth;
    private int mScreenHeight;
    private Paint mPaint;
    private RectF mRectF;
    // 圆
    private Point centerPoint;
    private int radio;
    private Paint mPaintText;
    private Rect mBound;
    private String mText = "请将面部填充整个红色框内";
    private Context mContext;
    private int mMarginLeft;
    private int mMarginTop;

    public RectOnCamera(Context context) {
        this(context, null);
        mContext = context;
    }

    public RectOnCamera(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public RectOnCamera(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getScreenMetrix();
        initView();
    }

    private void getScreenMetrix() {
        WindowManager WM = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);// 抗锯齿
        mPaint.setDither(true);// 防抖动
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);// 空心
        mMarginLeft = (int) (mScreenWidth * 0.15);
        mMarginTop = (int) (mScreenHeight * 0.15);
        mRectF = new RectF(mMarginLeft, mMarginTop, mScreenWidth - mMarginLeft, (float) (mScreenHeight - mMarginTop * 2));

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);// 抗锯齿
        mPaintText.setDither(true);// 防抖动
        mPaintText.setColor(Color.RED);
        mPaintText.setStrokeWidth(5);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setTextSize(sp2px(22));
        //获得绘制文本的宽和高
        mBound = new Rect();
        mPaintText.getTextBounds(mText, 0, mText.length(), mBound);
    }

    public int sp2px(float spValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRectF, mPaint);
        float y = (float) (mScreenHeight - mMarginTop * 1.7 + mBound.height() / 2);
        int x = mScreenWidth / 2 - mBound.width() / 2;
        canvas.drawText(mText, x, y, mPaintText);
    }
}
