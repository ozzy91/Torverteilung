package com.example.torverlauf;

import java.util.ArrayList;
import java.util.Map;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;

public class GoalDispersionView extends View {
	
	private static final int ANIMATION_DURATION = 1400;
	
	private static int BOTTOM_BAR_HEIGHT;
	private static int BOTTOM_BAR_MARGIN_TOP;
	private static int MINUTE_TEXT_SIZE;
	private static int MINUTE_TEXT_MARGIN_BOTTOM;
	private static int BAR_WIDTH;
	private static int BARS_MARGIN;
	private static int VALUE_TEXT_SIZE;
	private static int VALUE_TEXT_MARGIN;
	private static int SECTION_WIDTH;
	
	private Paint testPaint;
	private Paint solidPaint;
	private Paint grayPaint;
	private TextPaint valuePaint;
	private TextPaint minutePaint;
	private Drawable blueBar;
	
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
	
	private float goals1_15Animation;
	private float goals16_30Animation;
	private float goals31_45Animation;
	private float goals45_plusAnimation;
	private float goals46_60Animation;
	private float goals61_75Animation;
	private float goals76_90Animation;
	private float goals90_plusAnimation;
	private float opponent1_15Animation;
	private float opponent16_30Animation;
	private float opponent31_45Animation;
	private float opponent45_plusAnimation;
	private float opponent46_60Animation;
	private float opponent61_75Animation;
	private float opponent76_90Animation;
	private float opponent90_plusAnimation;
	
	private int height = 888;
	private int marginOutside;
	private int barsBottom;
	private int currentSection = 0;
	
	private boolean showBar;
	private boolean animationStarted;
	
	private String[] minuteStrings = {
			"1-15", "16-30", "31-45", "45+", "46-60", "61-75", "76-90", "90+"
	};

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
		barsBottom = height - (BOTTOM_BAR_HEIGHT + BOTTOM_BAR_MARGIN_TOP);
		marginOutside = (int) (SECTION_WIDTH - (2 * BAR_WIDTH + BARS_MARGIN)) / 2;
		
		canvas.drawRect(0, height - BOTTOM_BAR_HEIGHT, width, height, solidPaint);
		
		for (int i = 0; i<8; i++) {
			canvas.drawRect(i * SECTION_WIDTH, barsBottom, i*SECTION_WIDTH+2,height,solidPaint);
			canvas.drawText(minuteStrings[i], i * SECTION_WIDTH + (SECTION_WIDTH - minutePaint.measureText(minuteStrings[i])) / 2, height - MINUTE_TEXT_MARGIN_BOTTOM, minutePaint);
		}

		if (showBar) {
			if (!animationStarted) {
				ObjectAnimator barAnimator = ObjectAnimator.ofFloat(this, "goals1_15Animation", goals1_15);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "opponent1_15Animation", opponent1_15);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "goals16_30Animation", goals16_30);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "opponent16_30Animation", opponent16_30);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "goals31_45Animation", goals31_45);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "opponent31_45Animation", opponent31_45);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "goals45_plusAnimation", goals45_plus);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "opponent45_plusAnimation", opponent45_plus);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "goals46_60Animation", goals46_60);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "opponent46_60Animation", opponent46_60);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "goals61_75Animation", goals61_75);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "opponent61_75Animation", opponent61_75);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "goals76_90Animation", goals76_90);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "opponent76_90Animation", opponent76_90);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "goals90_plusAnimation", goals90_plus);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				barAnimator = ObjectAnimator.ofFloat(this, "opponent90_plusAnimation", opponent90_plus);
				barAnimator.setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator());
				barAnimator.start();
				
				animationStarted = true;
			}
			
			currentSection = 0;
			
			drawSection(canvas, goals1_15, opponent1_15, goals1_15Animation, opponent1_15Animation);
			drawSection(canvas, goals16_30, opponent16_30, goals16_30Animation, opponent16_30Animation);
			drawSection(canvas, goals31_45, opponent31_45, goals31_45Animation, opponent31_45Animation);
			drawSection(canvas, goals45_plus, opponent45_plus, goals45_plusAnimation, opponent45_plusAnimation);
			drawSection(canvas, goals46_60, opponent46_60, goals46_60Animation, opponent46_60Animation);
			drawSection(canvas, goals61_75, opponent61_75, goals61_75Animation, opponent61_75Animation);
			drawSection(canvas, goals76_90, opponent76_90, goals76_90Animation, opponent76_90Animation);
			drawSection(canvas, goals90_plus, opponent90_plus, goals90_plusAnimation, opponent90_plusAnimation);
		}
	}
	
	public void drawSection(Canvas canvas, int goalsValue, int opponentGoalsValue, float goalsAnimation, float opponentGoalsAnimation) {
		int firstBarLeft = currentSection * SECTION_WIDTH + marginOutside;
		if (goalsValue == 0)
			blueBar.setBounds(firstBarLeft, (int) (barsBottom - 0.1 * goalHeight), firstBarLeft + BAR_WIDTH, barsBottom);
		else
			blueBar.setBounds(firstBarLeft, (int) (barsBottom - goalsAnimation * goalHeight), firstBarLeft + BAR_WIDTH, barsBottom);
		blueBar.draw(canvas);
		if (opponentGoalsValue == 0)
			canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - 0.1f * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		else
			canvas.drawRect(firstBarLeft + BAR_WIDTH + BARS_MARGIN, barsBottom - opponentGoalsAnimation * goalHeight, firstBarLeft + 2 * BAR_WIDTH + BARS_MARGIN, barsBottom, grayPaint);
		float textLeft = firstBarLeft + (BAR_WIDTH - valuePaint.measureText("" + goalsValue)) / 2f;
		if (goalsValue == 0)
			canvas.drawText("" + goalsValue, textLeft, barsBottom - 0.1f * goalHeight - VALUE_TEXT_MARGIN, valuePaint);
		else
			canvas.drawText("" + goalsValue, textLeft, barsBottom - goalsAnimation * goalHeight - VALUE_TEXT_MARGIN, valuePaint);
		textLeft = firstBarLeft + BAR_WIDTH + BARS_MARGIN + (BAR_WIDTH - valuePaint.measureText("" + opponentGoalsValue)) / 2f;
		if (opponentGoalsValue == 0)
			canvas.drawText("" + opponentGoalsValue, textLeft, barsBottom - 0.1f * goalHeight - VALUE_TEXT_MARGIN, valuePaint);
		else
			canvas.drawText("" + opponentGoalsValue, textLeft, barsBottom - opponentGoalsAnimation * goalHeight - VALUE_TEXT_MARGIN, valuePaint);
		currentSection++;
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
		
		grayPaint = new Paint();
		grayPaint.setColor(Color.parseColor("#676767"));
	
		valuePaint = new TextPaint();
		valuePaint.setColor(Color.WHITE);
		valuePaint.setTypeface(Typeface.DEFAULT_BOLD);
		valuePaint.setTextSize(VALUE_TEXT_SIZE);
		valuePaint.setFlags(Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
		
		minutePaint = new TextPaint();
		minutePaint.setColor(Color.WHITE);
		minutePaint.setTextSize(MINUTE_TEXT_SIZE);
		minutePaint.setFlags(Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
		
		blueBar = getResources().getDrawable(R.drawable.gradient_goal_statistic_blue);
	}
	
	public void initValues() {
		Resources res = getResources();
		BOTTOM_BAR_HEIGHT = (int) res.getDimension(R.dimen.goal_dispersion_bottom_bar_height);
		BOTTOM_BAR_MARGIN_TOP = (int) res.getDimension(R.dimen.goal_dispersion_bottom_bar_margin_top);
		MINUTE_TEXT_SIZE = (int) res.getDimension(R.dimen.goal_dispersion_minute_text_size);
		MINUTE_TEXT_MARGIN_BOTTOM = (int) res.getDimension(R.dimen.goal_dispersion_minute_text_margin_bottom);
		BAR_WIDTH = (int) res.getDimension(R.dimen.goal_dispersion_bar_width);
		BARS_MARGIN = (int) res.getDimension(R.dimen.goal_dispersion_bars_margin);
		VALUE_TEXT_SIZE = (int) res.getDimension(R.dimen.goal_dispersion_value_text_size);
		VALUE_TEXT_MARGIN = (int) res.getDimension(R.dimen.goal_dispersion_value_text_margin);
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int parentWidth = ((View) getParent()).getWidth();
		setMeasuredDimension(height, parentWidth);
		SECTION_WIDTH = height / 8;
	}
	
	public void showBar() {
		showBar = true;
		invalidate();
	}
	
	public void setAnimationStarted(boolean animationStarted) {
		this.animationStarted = animationStarted;
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
		
//		showBar();
		getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				getViewTreeObserver().removeGlobalOnLayoutListener(this);
				showBar();
			}
		});
		requestLayout();
	}

	public float getGoals1_15Animation() {
		return goals1_15Animation;
	}

	public void setGoals1_15Animation(float goals1_15Animation) {
		this.goals1_15Animation = goals1_15Animation;
		invalidate();
	}

	public float getGoals16_30Animation() {
		return goals16_30Animation;
	}

	public void setGoals16_30Animation(float goals16_30Animation) {
		this.goals16_30Animation = goals16_30Animation;
	}

	public float getGoals31_45Animation() {
		return goals31_45Animation;
	}

	public void setGoals31_45Animation(float goals31_45Animation) {
		this.goals31_45Animation = goals31_45Animation;
	}

	public float getGoals45_plusAnimation() {
		return goals45_plusAnimation;
	}

	public void setGoals45_plusAnimation(float goals45_plusAnimation) {
		this.goals45_plusAnimation = goals45_plusAnimation;
	}

	public float getGoals46_60Animation() {
		return goals46_60Animation;
	}

	public void setGoals46_60Animation(float goals46_60Animation) {
		this.goals46_60Animation = goals46_60Animation;
	}

	public float getGoals61_75Animation() {
		return goals61_75Animation;
	}

	public void setGoals61_75Animation(float goals61_75Animation) {
		this.goals61_75Animation = goals61_75Animation;
	}

	public float getGoals76_90Animation() {
		return goals76_90Animation;
	}

	public void setGoals76_90Animation(float goals76_90Animation) {
		this.goals76_90Animation = goals76_90Animation;
	}

	public float getGoals90_plusAnimation() {
		return goals90_plusAnimation;
	}

	public void setGoals90_plusAnimation(float goals90_plusAnimation) {
		this.goals90_plusAnimation = goals90_plusAnimation;
	}

	public float getOpponent1_15Animation() {
		return opponent1_15Animation;
	}

	public void setOpponent1_15Animation(float opponent1_15Animation) {
		this.opponent1_15Animation = opponent1_15Animation;
	}

	public float getOpponent16_30Animation() {
		return opponent16_30Animation;
	}

	public void setOpponent16_30Animation(float opponent16_30Animation) {
		this.opponent16_30Animation = opponent16_30Animation;
	}

	public float getOpponent31_45Animation() {
		return opponent31_45Animation;
	}

	public void setOpponent31_45Animation(float opponent31_45Animation) {
		this.opponent31_45Animation = opponent31_45Animation;
	}

	public float getOpponent45_plusAnimation() {
		return opponent45_plusAnimation;
	}

	public void setOpponent45_plusAnimation(float opponent45_plusAnimation) {
		this.opponent45_plusAnimation = opponent45_plusAnimation;
	}

	public float getOpponent46_60Animation() {
		return opponent46_60Animation;
	}

	public void setOpponent46_60Animation(float opponent46_60Animation) {
		this.opponent46_60Animation = opponent46_60Animation;
	}

	public float getOpponent61_75Animation() {
		return opponent61_75Animation;
	}

	public void setOpponent61_75Animation(float opponent61_75Animation) {
		this.opponent61_75Animation = opponent61_75Animation;
	}

	public float getOpponent76_90Animation() {
		return opponent76_90Animation;
	}

	public void setOpponent76_90Animation(float opponent76_90Animation) {
		this.opponent76_90Animation = opponent76_90Animation;
	}

	public float getOpponent90_plusAnimation() {
		return opponent90_plusAnimation;
	}

	public void setOpponent90_plusAnimation(float opponent90_plusAnimation) {
		this.opponent90_plusAnimation = opponent90_plusAnimation;
		invalidate();
	}
}
