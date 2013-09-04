package utilities;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
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

		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);// set the locationManager to use the gps
		provider = locationManager.getBestProvider(criteria, false);

		// returns null if provider is disabled.  Appears to return null if using emulator
		location = locationManager.getLastKnownLocation(provider);
		
		// have to request location updates or it won't work - won't take updates from ddms
		locationManager.requestLocationUpdates(provider, 20000, 1, this);
		
	}
	
	public void updateGps(){
		
		locationManager.requestLocationUpdates(provider, 400, 1, this);
		
	}
	
	public Location getLocation() {
		// location can be null if using an emulator
		// or if gps is turned off or not available.
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	@Override
	public void onLocationChanged(Location location) {
		// this fires all the time

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

			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			context.startActivity(intent);
		}
		
		return enabled;
	}

}
