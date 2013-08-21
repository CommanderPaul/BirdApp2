package com.example.birdapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends BirdActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		populateListView();
		
	}

	private void populateListView(){
		ListView menuList = (ListView) findViewById(R.id.ListView_Menu);
		
		// get list of menu items
		String[] items = {
				getResources().getString(R.string.menu_settings)
				,getResources().getString(R.string.menu_observation)
				,getResources().getString(R.string.menu_records)
				,getResources().getString(R.string.menu_bird_map)
				,getResources().getString(R.string.menu_about)
				,"to be implemented"
				,"to be implemented"
				,"to be implemented"
				,"to be implemented"
				,"to be implemented"
				,"to be implemented"
				,""
		};
		
		
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.menu_item, items);// needs view that is menu item
		
		menuList.setAdapter(adapt);
		
		// set click listener for ArrayAdapter
		menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View itemClicked,
					int position, long id) {
				TextView textView = (TextView) itemClicked;
				String strText = textView.getText().toString();
				if (strText.equalsIgnoreCase(getResources().getString(
						R.string.menu_settings))) {
					// Launch the Splash Activity
					startActivity(new Intent(MenuActivity.this,
							BirdSplashActivity.class));
				} else if (strText.equalsIgnoreCase(getResources().getString(
						R.string.menu_observation))) {
					// Launch the Splash Activity
					startActivity(new Intent(MenuActivity.this,
							BirdObservationActivity.class));
				} else if (strText.equalsIgnoreCase(getResources().getString(
						R.string.menu_records))) {
					// Launch the Splash Activity
					startActivity(new Intent(MenuActivity.this,
							BirdSplashActivity.class));
				} else if (strText.equalsIgnoreCase(getResources().getString(
						R.string.menu_bird_map))) {
					// Launch the Splash Activity
					startActivity(new Intent(MenuActivity.this,
							BirdSplashActivity.class));
				}
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	
	
	
	
	
	
}
