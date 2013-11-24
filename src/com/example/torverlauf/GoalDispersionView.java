package com.example.torverlauf;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class GoalDispersionView extends View {
	
	private static int BOTTOM_BAR_HEIGHT;
	private static int BOTTOM_BAR_MARGIN_TOP;
	private static int DIVIDER_WIDTH;
	private static float DIVIDER_MARGIN;
	
	private Paint testPaint;
	private Paint solidPaint;
	
	private int height = 888;

	public GoalDispersionView(Context context) {
		super(context);
		initView(context);
	}

	public GoalDispersionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public GoalDispersionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public void initView(Context context) {
		initValues();
		initPaint();
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		int height = getHeight();
		int width = getWidth();
		solidPaint.setColor(Color.parseColor("#676767"));
		canvas.drawRect(0, height - BOTTOM_BAR_HEIGHT, width, height, solidPaint);
		solidPaint.setColor(Color.parseColor("#aaaaaa"));
		for (int i = 0; i<8; i++) {
			canvas.drawRect(i * DIVIDER_MARGIN, 0, i * DIVIDER_MARGIN + DIVIDER_WIDTH, height, solidPaint);
		}
		canvas.drawRect(width - DIVIDER_WIDTH, 0, width, height, solidPaint);
	}

	public void initPaint() {
		testPaint = new Paint();
		testPaint.setColor(Color.RED);
		
		solidPaint = new Paint();
	}
	
	public void initValues() {
		Resources res = getResources();
		BOTTOM_BAR_HEIGHT = (int) res.getDimension(R.dimen.goal_dispersion_bottom_bar_height);
		BOTTOM_BAR_MARGIN_TOP = (int) res.getDimension(R.dimen.goal_dispersion_bottom_bar_margin_top);
		DIVIDER_WIDTH = (int) res.getDimension(R.dimen.goal_dispersion_divider_width);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int parentWidth = ((View) getParent()).getWidth();
		setMeasuredDimension(height, parentWidth);
		DIVIDER_MARGIN = (float) (height - DIVIDER_WIDTH) / 8;
	}
	
	@Override
	public void setRotation(final float rotation) {
		super.setRotation(rotation);
		if (rotation == 90 || rotation == -90 || rotation == 270 || rotation == -270) {
			getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
				@SuppressWarnings("deprecation")
				public void onGlobalLayout() {
					getViewTreeObserver().removeGlobalOnLayoutListener(this);
					float offset = (getHeight() - getWidth()) / 2;
					if (rotation == -90 || rotation == 270) {
						setX(getX() + offset);
						setY(getY() - offset);
					} else if (rotation == 90 || rotation == -270) {
						setX(getX() + offset);
						setY(getY() - offset);
					}
				}
			});
		}
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

}
