package com.xter.slimnews.presenstation.component.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xter.slimnews.R;
import com.xter.support.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XTER on 2018/11/9.
 * 画个圈
 */

public class RectSplash extends View {

	private int mSplashLength;
	private int mSplashWidth;

	private int mWidthIn;
	private int mWidthOut;

	private int mHeightIn;
	private int mHeightOut;

	private int mWidthIncrement;
	private int mHeightIncrement;

	private Paint mPaint;

	private RectF mRectIn;
	private RectF mRectOut;
	private Path mPath;
	private int mDirection;

	private float mX;
	private float mY;
	private float[] mPointX;
	private float[] mPointY;


	private float mStep;

	private PointF[] points;

	private List<Float> mXList;
	private List<Float> mYList;

	private List<Integer> seedList;

	private int counter;

	public RectSplash(Context context) {
		super(context);
	}

	public RectSplash(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public RectSplash(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs);
	}

	private void initView(Context context, @Nullable AttributeSet attrs) {
		if (attrs != null) {
			final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RectSplash);
			final int count = a.getIndexCount();
			for (int i = 0; i < count; i++) {
				int attr = a.getIndex(i);
				switch (attr) {
					case R.styleable.RectSplash_width_in:
						mWidthIn = a.getDimensionPixelOffset(attr, 100);
						break;
					case R.styleable.RectSplash_width_out:
						mWidthOut = a.getDimensionPixelOffset(attr, 120);
						break;
					case R.styleable.RectSplash_height_in:
						mHeightIn = a.getDimensionPixelOffset(attr, 100);
						break;
					case R.styleable.RectSplash_height_out:
						mHeightOut = a.getDimensionPixelOffset(attr, 120);
						break;
					case R.styleable.RectSplash_splash_length:
						mSplashLength = a.getInteger(attr, 30);
						break;
					case R.styleable.RectSplash_splash_width:
						mSplashWidth = a.getInteger(attr, 20);
						break;
					case R.styleable.RectSplash_direction:
						mDirection = a.getInteger(attr, 0);
						break;
					case R.styleable.RectSplash_splash_step:
						mStep = a.getFloat(attr, 20.0f);
						break;
				}
			}
		}

		L.d("wIn=" + mWidthIn + ",hIn=" + mHeightIn + ",wOut=" + mWidthOut + ",hOut=" + mHeightOut + ",dire=" + mDirection + ",len=" + mSplashLength);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(mSplashWidth);

		mRectOut = new RectF(mSplashWidth, mSplashWidth, mWidthOut + mSplashWidth, mHeightOut + mSplashWidth);
		points = new PointF[4];
		if (mDirection == 0) {
			points[0] = new PointF(mRectOut.left, mRectOut.top);
			points[1] = new PointF(mRectOut.right, mRectOut.top);
			points[2] = new PointF(mRectOut.right, mRectOut.bottom);
			points[3] = new PointF(mRectOut.left, mRectOut.bottom);
		} else {
			points[0] = new PointF(mRectOut.left, mRectOut.top);
			points[1] = new PointF(mRectOut.left, mRectOut.bottom);
			points[2] = new PointF(mRectOut.right, mRectOut.bottom);
			points[3] = new PointF(mRectOut.right, mRectOut.top);
		}

		int space = (mWidthOut - mWidthIn) / 2;
		mRectIn = new RectF(mRectOut.left + space, mRectOut.top + space, mRectOut.right - space, mRectOut.bottom - space);

		mPath = new Path();
//		mPath.addRect(mRectOut, Path.Direction.CCW);

		mX = mRectOut.left;
		mY = mRectOut.top;

		mXList = new ArrayList<>();
		mYList = new ArrayList<>();

		seedList = new ArrayList<>();
		buildXYInRect(mDirection == 0, mStep, mRectOut);
	}

	private void buildXYInRect(boolean isCW, float step, RectF rectF) {
		if (isCW) {
			float offset1 = (rectF.right - rectF.left) % step;
			int size1 = (int) ((rectF.right - rectF.left) / step);
			for (int i = 0; i < size1; i++) {
				mXList.add(rectF.left + step * i);
				mYList.add(rectF.top);
			}

			float offset = offset1 == 0 ? 0 : (step - offset1);
			float offset2 = (rectF.bottom - rectF.top - offset) % step;
			int size2 = (int) ((rectF.bottom - rectF.top - offset) / step);
			for (int i = 0; i < size2; i++) {
				mXList.add(rectF.right);
				mYList.add(rectF.top + offset + step * i);
			}

			offset = offset2 == 0 ? 0 : (step - offset2);
			float offset3 = (rectF.right - rectF.left - offset) % step;
			int size3 = (int) ((rectF.right - rectF.left - offset) / step);
			for (int i = 0; i < size3; i++) {
				mXList.add(rectF.right - step * i - offset);
				mYList.add(rectF.bottom);
			}

			offset = offset3 == 0 ? 0 : (step - offset3);
			float offset4 = (rectF.bottom - rectF.top - offset) % step;
			int size4 = (int) ((rectF.right - rectF.top - offset) / step);
			for (int i = 0; i < size4; i++) {
				mXList.add(rectF.left);
				mYList.add(rectF.bottom - step * i - offset);
			}
		} else {
			float offset1 = (rectF.bottom - rectF.top) % step;
			int size1 = (int) ((rectF.bottom - rectF.top) / step);
			for (int i = 0; i < size1; i++) {
				mXList.add(rectF.left);
				mYList.add(rectF.top + step * i);
			}

			float offset = offset1 == 0 ? 0 : (step - offset1);
			float offset2 = (rectF.right - rectF.left - offset) % step;
			int size2 = (int) ((rectF.right - rectF.left - offset) / step);
			for (int i = 0; i < size2; i++) {
				mXList.add(rectF.left + offset + step * i);
				mYList.add(rectF.bottom);
			}

			offset = offset2 == 0 ? 0 : (step - offset2);
			float offset3 = (rectF.bottom - rectF.top - offset) % step;
			int size3 = (int) ((rectF.bottom - rectF.top - offset) / step);
			for (int i = 0; i < size3; i++) {
				mXList.add(rectF.right);
				mYList.add(rectF.bottom - step * i - offset);
			}

			offset = offset3 == 0 ? 0 : (step - offset3);
			float offset4 = (rectF.right - rectF.top - offset) % step;
			int size4 = (int) ((rectF.right - rectF.top - offset) / step);
			for (int i = 0; i < size4; i++) {
				mXList.add(rectF.right - step * i - offset);
				mYList.add(rectF.top);
			}
		}

		L.d(mXList.size() + "~" + mXList.toString());
		L.d(mYList.size() + "~" + mYList.toString());

		int total = mXList.size();
		int seed = total / 4;
		for (int i = 0; i < total; i++) {
			if (i % seed == 0) {
				seedList.add(i);
			}
		}
		L.d(seedList.size() + "~" + seedList.toString());
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
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
		canvas.drawRect(mRectIn, mPaint);
	}

	private void drawDynamic(Canvas canvas) {
		mPaint.setColor(Color.BLUE);
		mPaint.setStyle(Paint.Style.STROKE);
		mPath.reset();
		if (counter + 1 >= mXList.size()) {
			counter = 0;
		}
		mPath.moveTo(mXList.get(counter), mYList.get(counter));
		if (mSplashLength > mStep) {
			int temp = counter;
			int endCounter = (int) (counter + 1 + mSplashLength / mStep);
			if (endCounter >= mXList.size()) {
				endCounter = endCounter - mXList.size();
			}
			int seed = judge(temp, endCounter);
			if (seed != -1) {
				mPath.lineTo(mXList.get(seed), mYList.get(seed));
				L.d(seed + "~" + mXList.get(seed) + "," + mYList.get(seed));
			}
			mPath.lineTo(mXList.get(endCounter), mYList.get(endCounter));
			counter++;
		} else {
			counter++;
			mPath.lineTo(mXList.get(counter), mYList.get(counter));
		}
		canvas.drawPath(mPath, mPaint);
		postInvalidate();
	}

	private int judge(int start, int end) {
		for (int seed : seedList) {
			if (start < seed && end > seed) {
				return seed;
			}
			if (start > end && end > seed) {
				return seed;
			}
		}
		return -1;
	}

	@Deprecated
	private void drawPath(float startX, float startY) {
		PointF point = null;
		boolean isHorizontal = false;
		for (int i = 0; i < points.length; i++) {
			if (startX == points[i].x && startX == points[i + 1 > points.length - 1 ? 0 : i + 1].x) {
				point = points[i + 1 > points.length - 1 ? 0 : i + 1];
				isHorizontal = false;
			}
			if (startY == points[i].y && startY == points[i + 1 > points.length - 1 ? 0 : i + 1].y) {
				point = points[i + 1 > points.length - 1 ? 0 : i + 1];
				isHorizontal = true;
			}
		}
		if (point != null) {
			int tempL = 0;
			if (isHorizontal) {
				tempL = Math.abs((int) (point.x - startX));
			} else {
				tempL = Math.abs((int) (point.y - startY));
			}

			if (mSplashLength > tempL) {
				mPath.moveTo(startX, startY);
				if (isHorizontal) {
					mPath.lineTo(startX + mSplashLength, startY);
					mX = mX + 3;
					if (mX >= mRectOut.right) {
						mX = mRectOut.right;
					}
				} else {
					mPath.lineTo(startX, startY + mSplashLength);
					mY = mY + 3;
					if (mY >= mRectOut.bottom) {
						mY = mRectOut.bottom;
					}
				}
			} else {

			}

		}
	}

}
