package utilities;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class GPSReader implements LocationListener{

	private LocationManager locationManager;
	private String provider;
	private Context context;
	private Location location;
	
	// Constructor
	public GPSReader(Context context){
		
		this.context = context;
		
		checkToSeeIfGPSIsOn();
		
		
		
		// get the location manager
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		location = locationManager.getLastKnownLocation(provider);
		
		
		
		
		// initialize the location fields
//		if (location != null){
//			// can put a toast or some other feedback here
//			onLocationChanged(location);
//		} else {
//			latitudeField.setText("Location not available");
//			longitudeField.setText("Location not available");
//		}
		
		
		
	}
	
	
	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	@Override
	public void onLocationChanged(Location location) {
		
		float lat = (float) (location.getLatitude());
		float lng = (float) (location.getLongitude());
		
//		latitudeField.setText(String.valueOf(lat));
//		longitudeField.setText(String.valueOf(lng));
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(context,  "Enabled new provider " + provider,  Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Toast.makeText(context,  "Disabled provider " + provider,  Toast.LENGTH_SHORT).show();
		
	}
	
	
	// request update from gps
	public void updateGPS(){
		
		// request an update based on parameters
		locationManager.requestLocationUpdates(provider,  400,  1,  this);
		
	}
	
	/**
	 * Checks to see if gps is on.  Opens GPS enable activity if not enabled.
	 * Returns boolean as to whether it is enabled or not.
	 * @return
	 */
	private boolean checkToSeeIfGPSIsOn(){
		
		LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if (!enabled){
			//TODO there should be a dialog here asking the user if they even want to
			// turn on the gps
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			context.startActivity(intent);
		}
		
		return enabled;
	}

}
