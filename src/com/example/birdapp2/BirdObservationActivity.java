package com.example.birdapp2;

import java.util.Date;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.widget.TextView;

public class BirdObservationActivity extends BirdActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bird_observation);
		
		// get current time
		Date d = new Date();
		CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());
		
		TextView clockView = (TextView) findViewById(R.id.observation_time);
		clockView.setText(s);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bird_observation, menu);
		return true;
	}

}
