package com.example.torverlauf;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class GoalDispersionView extends View {
	
	private static int BOTTOM_BAR_HEIGHT;
	private static int BOTTOM_BAR_MARGIN_TOP;
	private static int BAR_WIDTH;
	private static int BARS_MARGIN;
	private static int VALUE_TEXT_SIZE;
	private static int VALUE_TEXT_MARGIN;
	private static float SECTION_WIDTH;
	
	private Paint testPaint;
	private Paint solidPaint;
	private Paint bluePaint;
	private Paint grayPaint;
	private TextPaint valuePaint;
	
	private float goalHeight;
	private int highestValue;
	
	private int goals1_15;
	private int goals16_30;
	private int goals31_45;
	private int goals45_plus;
	private int goals46_60;
	private int goals61_75;
	private int goals76_90;
	private int goals90_plus;
	
	private int opponent1_15;
	private int opponent16_30;
	private int opponent31_45;
	private int opponent45_plus;
	private int opponent46_60;
	private int opponent61_75;
	private int opponent76_90;
	private int opponent90_plus;
	
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
		canvas.drawRect(0, height - BOTTOM_BAR_HEIGHT, width, height, solidPaint);
		
		int barsBottom = height - (BOTTOM_BAR_HEIGHT + BOTTOM_BAR_MARGIN_TOP);
		for (int i = 1; i<8; i++)
			canvas.drawRect(i * SECTION_WIDTH, barsBottom, i*SECTION_WIDTH+2,height,solidPaint);
		
		float marginOutside = (SECTION_WIDTH - (2 * BAR_WIDTH + BARS_MARGIN)) / 2;
		int section = 0;
		
		float firstBarLeft = section * SECTION_WIDTH + marginOutside;
		canvas.drawRect(firstBarLeft, barsBottom - goals1_15 * goalHeight, firstBarLeft + BAR_WIDTH, barsBottom, bluePaint);
		canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent1_15 * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		canvas.drawText("" + goals1_15, firstBarLeft, barsBottom - goals1_15 * goalHeight - VALUE_TEXT_MARGIN, valuePaint);
		canvas.drawText("" + opponent1_15, firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent1_15 * goalHeight - VALUE_TEXT_MARGIN, valuePaint);
		section++;
		
		firstBarLeft = section * SECTION_WIDTH + marginOutside;
		canvas.drawRect(firstBarLeft, barsBottom - goals16_30 * goalHeight, firstBarLeft + BAR_WIDTH, barsBottom, bluePaint);
		canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent16_30 * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		section++;
		
		firstBarLeft = section * SECTION_WIDTH + marginOutside;
		canvas.drawRect(firstBarLeft, barsBottom - goals31_45 * goalHeight, firstBarLeft + BAR_WIDTH, barsBottom, bluePaint);
		canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent31_45 * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		section++;
		
		firstBarLeft = section * SECTION_WIDTH + marginOutside;
		canvas.drawRect(firstBarLeft, barsBottom - goals45_plus * goalHeight, firstBarLeft + BAR_WIDTH, barsBottom, bluePaint);
		canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent45_plus * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		section++;
		
		firstBarLeft = section * SECTION_WIDTH + marginOutside;
		canvas.drawRect(firstBarLeft, barsBottom - goals46_60 * goalHeight, firstBarLeft + BAR_WIDTH, barsBottom, bluePaint);
		canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent46_60 * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		section++;
		
		firstBarLeft = section * SECTION_WIDTH + marginOutside;
		canvas.drawRect(firstBarLeft, barsBottom - goals61_75 * goalHeight, firstBarLeft + BAR_WIDTH, barsBottom, bluePaint);
		canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent61_75 * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		section++;
		
		firstBarLeft = section * SECTION_WIDTH + marginOutside;
		canvas.drawRect(firstBarLeft, barsBottom - goals76_90 * goalHeight, firstBarLeft + BAR_WIDTH, barsBottom, bluePaint);
		canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent76_90 * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		section++;
		
		firstBarLeft = section * SECTION_WIDTH + marginOutside;
		canvas.drawRect(firstBarLeft, barsBottom - goals90_plus * goalHeight, firstBarLeft + BAR_WIDTH, barsBottom, bluePaint);
		canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponent90_plus * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
	}
	
	public void setValues() {
		
	}
	
	public void setData(Map<String, Object> subnode) {
		ArrayList<Map<String, Object>> resultlist = (ArrayList<Map<String, Object>>) subnode.get("resultlist");
		if (resultlist != null && !resultlist.isEmpty()) {
			Map<String, Object> result = resultlist.get(0);
			
			highestValue = 0;
			
			goals1_15 = (Integer) result.get("tore_1_15");
            if (goals1_15 > highestValue)
                    highestValue = goals1_15;
            goals16_30 = (Integer) result.get("tore_16_30");
            if (goals16_30 > highestValue)
                    highestValue = goals16_30;
            goals31_45 = (Integer) result.get("tore_31_45");
            if (goals31_45 > highestValue)
                    highestValue = goals31_45;
            goals45_plus = (Integer) result.get("tore_45_plus");
            if (goals45_plus > highestValue)
                    highestValue = goals45_plus;
            goals46_60 = (Integer) result.get("tore_46_60");
            if (goals46_60 > highestValue)
                    highestValue = goals46_60;
            goals61_75 = (Integer) result.get("tore_61_75");
            if (goals61_75 > highestValue)
                    highestValue = goals61_75;
            goals76_90 = (Integer) result.get("tore_76_90");
            if (goals76_90 > highestValue)
                    highestValue = goals76_90;
            goals90_plus = (Integer) result.get("tore_90_plus");
            if (goals90_plus > highestValue)
                    highestValue = goals90_plus;
            
            opponent1_15 = (Integer) result.get("gegentore_1_15");
            if (opponent1_15 > highestValue)
                    highestValue = opponent1_15;
            opponent16_30 = (Integer) result.get("gegentore_16_30");
            if (opponent16_30 > highestValue)
                    highestValue = opponent16_30;
            opponent31_45 = (Integer) result.get("gegentore_31_45");
            if (opponent31_45 > highestValue)
                    highestValue = opponent31_45;
            opponent45_plus = (Integer) result.get("gegentore_45_plus");
            if (opponent45_plus > highestValue)
                    highestValue = opponent45_plus;
            opponent46_60 = (Integer) result.get("gegentore_46_60");
            if (opponent46_60 > highestValue)
                    highestValue = opponent46_60;
            opponent61_75 = (Integer) result.get("gegentore_61_75");
            if (opponent61_75 > highestValue)
                    highestValue = opponent61_75;
            opponent76_90 = (Integer) result.get("gegentore_76_90");
            if (opponent76_90 > highestValue)
                    highestValue = opponent76_90;
            opponent90_plus = (Integer) result.get("gegentore_90_plus");
            if (opponent90_plus > highestValue)
                    highestValue = opponent90_plus;
			
            goalHeight = (float) (getHeight() - BOTTOM_BAR_HEIGHT - BOTTOM_BAR_MARGIN_TOP - VALUE_TEXT_MARGIN - VALUE_TEXT_SIZE) / highestValue;
		}
	}

	public void initPaint() {
		testPaint = new Paint();
		testPaint.setColor(Color.RED);
		
		solidPaint = new Paint();
		solidPaint.setColor(Color.parseColor("#33676767"));
		
		bluePaint = new Paint();
		bluePaint.setShader(new LinearGradient(0, 0, BAR_WIDTH, 0, Color.parseColor("#4f6d89"), Color.parseColor("#7baae2"), Shader.TileMode.MIRROR));
		
		grayPaint = new Paint();
		grayPaint.setShader(new LinearGradient(0, 0, BAR_WIDTH, 0, Color.parseColor("#696969"), Color.parseColor("#3d3d3d"), Shader.TileMode.MIRROR));
	
		valuePaint = new TextPaint();
		valuePaint.setColor(Color.WHITE);
		valuePaint.setTypeface(Typeface.DEFAULT_BOLD);
		valuePaint.setTextSize(VALUE_TEXT_SIZE);
		valuePaint.setFlags(Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
	}
	
	public void initValues() {
		Resources res = getResources();
		BOTTOM_BAR_HEIGHT = (int) res.getDimension(R.dimen.goal_dispersion_bottom_bar_height);
		BOTTOM_BAR_MARGIN_TOP = (int) res.getDimension(R.dimen.goal_dispersion_bottom_bar_margin_top);
		BAR_WIDTH = (int) res.getDimension(R.dimen.goal_dispersion_bar_width);
		BARS_MARGIN = (int) res.getDimension(R.dimen.goal_dispersion_bars_margin);
		VALUE_TEXT_SIZE = (int) res.getDimension(R.dimen.goal_dispersion_value_text_size);
		VALUE_TEXT_MARGIN = (int) res.getDimension(R.dimen.goal_dispersion_value_text_margin);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int parentWidth = ((View) getParent()).getWidth();
		setMeasuredDimension(height, parentWidth);
		SECTION_WIDTH = (float) height / 8;
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

	public void testCode() {
		goals1_15 = 3;
		opponent1_15 = 1;
		goals16_30 = 5;
		opponent16_30 = 0;
		goals31_45 = 3;
		opponent31_45 = 4;
		goals45_plus = 0;
		opponent45_plus = 0;
		goals46_60 = 5;
		opponent46_60 = 1;
		goals61_75 = 8;
		opponent61_75 = 0;
		goals76_90 = 5;
		opponent76_90 = 1;
		goals90_plus = 1;
		opponent90_plus = 0;
		
		goalHeight = (float) (getHeight() - BOTTOM_BAR_HEIGHT - BOTTOM_BAR_MARGIN_TOP - VALUE_TEXT_MARGIN - VALUE_TEXT_SIZE) / 8;
		invalidate();
	}
}
