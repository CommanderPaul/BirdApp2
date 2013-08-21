package com.example.birdapp2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * Using this class to propagate the Activity class.
 * 
 * @author pwroe
 *
 */
public class BirdActivity extends Activity {

	// Static fields here
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bird);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bird, menu);
		return true;
	}

}
