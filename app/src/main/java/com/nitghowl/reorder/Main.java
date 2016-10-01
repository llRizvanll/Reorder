package com.nitghowl.reorder;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class Main extends Activity implements OnTouchListener, OnDragListener {

	LinearLayout viewone,viewtwo,viewthree,viewfour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_n);

		viewone=(LinearLayout)findViewById(R.id.red_ball);
		viewtwo=(LinearLayout)findViewById(R.id.green_ball);
		viewthree=(LinearLayout)findViewById(R.id.blue_ball);
		viewfour=(LinearLayout)findViewById(R.id.yele_ball);

		//Adding video sample 
		VideoView videoView = (VideoView) (findViewById(R.id.videoview));
		String path = "android.resource://" + getPackageName() + "/" + R.raw.samplevideo;
				videoView.setVideoURI(Uri.parse(path));
		videoView.setMediaController(new android.widget.MediaController(Main.this));
		videoView.requestFocus();
		videoView.start();

		//Adding dynamic layouts
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
		ViewGroup linearExtra = new LinearLayout(getBaseContext());
		int id = 100+1;
		linearExtra.setId(id);
		linearExtra.setLayoutParams(params);
		//linearExtra.setOrientation(LinearLayout.HORIZONTAL );
		linearExtra.setOnDragListener(this);
		linearExtra.setOnTouchListener(this);
		linearExtra.setBackgroundColor(getResources().getColor(R.color.five));
		((LinearLayout) findViewById(R.id.root_container)).addView(linearExtra);


		//Adding dynamic layouts
		ViewGroup linearExtra2 = new LinearLayout(getBaseContext());
		linearExtra2.setLayoutParams(params);
		int id2 = 100+2;
		linearExtra2.setId(id2);
		//linearExtra2.setOrientation(LinearLayout.HORIZONTAL);
		linearExtra2.setOnDragListener(this);
		linearExtra2.setOnTouchListener(this);

		linearExtra2.setBackgroundColor(getResources().getColor(R.color.six));
		((LinearLayout) findViewById(R.id.root_container)).addView(linearExtra2);

		findViewById(R.id.red_ball).setOnTouchListener(this);
		findViewById(R.id.green_ball).setOnTouchListener(this);
		findViewById(R.id.blue_ball).setOnTouchListener(this);
		findViewById(R.id.yele_ball).setOnTouchListener(this);
		findViewById(R.id.top_container).setOnDragListener(this);
		findViewById(R.id.bottom_container).setOnDragListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(null, shadowBuilder, v, 0);
			v.setVisibility(View.INVISIBLE);
			return true;
		} else {
			v.setVisibility(View.VISIBLE);
			return false;
		}
	}

	@Override
	public boolean onDrag(View v, DragEvent e) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		params.weight = 1.0f;
		View v1 = (View) e.getLocalState();
		v1.setLayoutParams(params);

		if (e.getAction()== DragEvent.ACTION_DROP) {
			View view = (View) e.getLocalState();
			ViewGroup from = (ViewGroup) view.getParent();
			from.removeView(view);
			LinearLayout to = (LinearLayout) v;
			to.addView(view);
			view.setVisibility(View.VISIBLE);
		}
		return true;
	}

}
