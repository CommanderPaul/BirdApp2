package com.example.birdapp2;

import java.util.Date;
import utilities.GPSReader;
import android.location.Location;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class BirdObservationActivity extends BirdActivity {

	// Fields
	
	private Double latValue;
	private TextView latField;
	
	private Double lonValue;
	private TextView lonField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bird_observation);
		
		// get current time
		Date d = new Date();
		CharSequence s = DateFormat.format("MM-dd-yyyy", d.getTime());
		
		TextView clockView = (TextView) findViewById(R.id.observation_time);
		clockView.setText(s);
		
		
		// input validation for bird name
		EditText birdName = (EditText)findViewById(R.id.editTextBirdName);
		setListenerToEditText(birdName);
		// input validation for bird activity
		EditText birdActivity = (EditText)findViewById(R.id.editTextBirdActivity);
		setListenerToEditText(birdActivity);
		
		// set initial gps coordiates
		latField = (TextView) findViewById(R.id.TextViewLatitude);// this may need to go in onCreate()
		lonField = (TextView) findViewById(R.id.TextViewLongitude);
		populateLatAndLong();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bird_observation, menu);
		return true;
	}

	
	@Override
	protected void onResume(){
		super.onResume();
		populateLatAndLong();
	}
	
	
	/**
	 * Uses GPSReader class to get lat and lon from gps
	 * and auto-populate the appropriate text fields
	 */
	private void populateLatAndLong(){
		
		GPSReader gpsReader = new GPSReader(this);
		
		Location location = gpsReader.getLocation();
		
		if (location != null) {
			gpsReader.updateGps();
			// this shouldn't run unless there is a good location.
			latField.setText(String.valueOf(location.getLatitude()));
			lonField.setText(String.valueOf(location.getLongitude()));
		}
		
	}
	
	/**
	 * Set a focus change listener to an EditText
	 * @param editText
	 */
	private void setListenerToEditText(final EditText editText){
		//TODO can set up a hashmap ore something to further reduce code duplication
		editText.setOnFocusChangeListener(new OnFocusChangeListener(){
			
			public void onFocusChange(View v, boolean hasFocus){
				
				final String textString = editText.getText().toString();
				
				if(!hasFocus){
					if(textString.length() < 1){

						if( editText.getResources().getResourceEntryName(editText.getId()).equals("editTextBirdName")  ){
							editText.setError("Bird Name is Required!");							
						}
						if( editText.getResources().getResourceEntryName(editText.getId()).equals("editTextBirdActivity")){
							editText.setError("Bird Activity is Required!");
						}
			
					}
					
					// use matches to detect non characters
					if (!textString.matches(REGULAR_EXPRESSION_ONLY_LETTERS)){
						editText.setError("Non Letter Detected!");
					}
				}
			}
			
			
		});
		
	}
	
	
	
	
	
}
