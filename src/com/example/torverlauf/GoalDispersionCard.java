package com.example.torverlauf;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GoalDispersionCard extends LinearLayout {
	
	private TextView txtTeamName;
	private GoalDispersionView goalDispersionView;
	
	public GoalDispersionCard(Context context) {
		super(context);
		initView(context);
	}

	public GoalDispersionCard(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public GoalDispersionCard(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}
	
	public void initView(final Context context) {
		View.inflate(context, R.layout.card_goal_trend, this);
		txtTeamName = (TextView) findViewById(R.id.goal_dispersion_teamname);
		goalDispersionView = (GoalDispersionView) findViewById(R.id.goal_dispersion_view);
		
		txtTeamName.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				txtTeamName.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				float teamNameBottom = txtTeamName.getY() + txtTeamName.getHeight();
				int height = getHeight() - (int) teamNameBottom - 0;
				goalDispersionView.setHeight(height);
				goalDispersionView.setRotation(-90);
			}
		});
	}

}
