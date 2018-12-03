package com.xter.slimnews.presenstation.component.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xter.slimnews.R;
import com.xter.support.util.L;

import java.util.concurrent.TimeUnit;

/**
 * Created by XTER on 2018/11/9.
 * 画个圈
 */

public class CircleSplash extends View {

	private int mRadiusIn;
	private int mRadiusOut;
	private int mRadiusWidth;

	private int mWidth;
	private int mHeight;

	private Paint mPaint;

	private RectF mRectF;

	private int mDegreeStart;
	private int mDegreeRange;

	public CircleSplash(Context context) {
		super(context);
	}

	public CircleSplash(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public CircleSplash(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs);
	}

	private void initView(Context context, @Nullable AttributeSet attrs) {
		if (attrs != null) {
			final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleSplash);
			final int count = a.getIndexCount();
			for (int i = 0; i < count; i++) {
				int attr = a.getIndex(i);
				switch (attr) {
					case R.styleable.CircleSplash_radius_in:
						mRadiusIn = a.getDimensionPixelOffset(attr, 100);
						break;
					case R.styleable.CircleSplash_radius_out:
						mRadiusOut = a.getDimensionPixelOffset(attr, 120);
						break;
					case R.styleable.CircleSplash_radius_width:
						mRadiusWidth = a.getDimensionPixelOffset(attr, 2);
						break;
					case R.styleable.CircleSplash_degree_range:
						mDegreeRange = a.getInteger(attr, 20);
						break;
				}
			}
		}


		L.d("in=" + mRadiusIn + ",out=" + mRadiusOut + ",sw=" + mRadiusWidth + ",ds=" + mDegreeStart + ",ra=" + mDegreeRange);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(mRadiusWidth);

		mRectF = new RectF(0, 0, (mRadiusOut + mRadiusWidth * 2) * 2, (mRadiusOut + mRadiusWidth * 2) * 2);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mWidth = MeasureSpec.getMode(widthMeasureSpec);
		mHeight = MeasureSpec.getMode(heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawFixed(canvas);
		drawDynamic(canvas);
	}

	private void drawFixed(Canvas canvas) {
		mPaint.setColor(Color.GRAY);
		mPaint.setStyle(Paint.Style.FILL);
		canvas.drawCircle(mRadiusOut + mRadiusWidth * 2, mRadiusOut + mRadiusWidth * 2, Math.min(mRadiusIn, mRadiusOut), mPaint);
	}

	private void drawDynamic(Canvas canvas) {
		mPaint.setColor(Color.BLUE);
		mPaint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(mRectF, mDegreeStart, mDegreeRange, false, mPaint);
		if (mDegreeStart > 360) {
			mDegreeStart = 0;
		} else {
			mDegreeStart++;
		}
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		postInvalidate();

	}
}
