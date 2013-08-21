package com.example.birdapp2;

import java.util.Date;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class BirdObservationActivity extends BirdActivity {

	
	
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
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bird_observation, menu);
		return true;
	}

	
	// lost focus listener
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
