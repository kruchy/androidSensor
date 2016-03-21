package pl.edu.agh.lab.sensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;

import pl.edu.agh.lab.sensors.printers.Accelerometer;
import pl.edu.agh.lab.sensors.printers.DefaultSensor;
import pl.edu.agh.lab.sensors.printers.Gyroscope;
import pl.edu.agh.lab.sensors.printers.LightSensor;
import pl.edu.agh.lab.sensors.printers.TemperatureSensor;
import pl.edu.agh.lab.sensors.telephony.TelephonyActivity;

public class MainActivity extends Activity implements SensorEventListener, LocationListener {
	private LinearLayout layout;
	private SensorManager sensorManager;
	private Accelerometer accelerometer;
	private Gyroscope gyroscope;
	private DefaultSensor defaultSensor;
	private TextView location;
	private TextView locationStatus;
	private HashMap<Integer, TextView> sensorMap = new HashMap<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light_sensor);
		layout = (LinearLayout) findViewById(R.id.Sensors);
		layout.getBaseline();
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		location = (TextView) findViewById(R.id.location);
		locationStatus = (TextView) findViewById(R.id.locationStatus);

		TextView textView2;
		int id = 0;
		for (Sensor sensor : sensorManager.getSensorList(Sensor.TYPE_ALL)) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
			textView2 = new TextView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 10, 0, 10);
			textView2.setLayoutParams(params);
			layout.addView(textView2);
			sensorMap.put(sensor.getType(), textView2);
			id++;
		}
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		gyroscope = new Gyroscope();
		accelerometer = new Accelerometer();
		LightSensor lightSensor = new LightSensor();
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		defaultSensor = new DefaultSensor();
		gyroscope.setNext(accelerometer);
		gyroscope.setNext(lightSensor);
		gyroscope.setNext(temperatureSensor);
		gyroscope.setNext(defaultSensor);
		Toast toast = Toast.makeText(getApplicationContext(),"Made " + id + " sensors",Toast.LENGTH_SHORT);
		toast.show();
	}
	@Override
	protected void onStart() {
		super.onStart();
		for (Sensor sensor : sensorManager.getSensorList(Sensor.TYPE_ALL)) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
	}
    @Override
	protected void onResume() {
		super.onResume();
	}
    @Override
	protected void onPause() {
		// unregister listener
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	protected void onStop() {
		sensorManager.unregisterListener(this);
		super.onStop();
	}
	@Override
	public void onSensorChanged(SensorEvent event) {

		gyroscope.onSensorChanged(event,sensorMap.get(event.sensor.getType()));

	}

	@Override
	public void onLocationChanged(Location location) {
		this.location.setText(location.getLatitude() + " " + location.getLatitude() );
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		locationStatus.setText(provider +  " status: " +  status);
	}

	@Override
	public void onProviderEnabled(String provider) {
		locationStatus.setText(provider +  " enabled!");
	}

	@Override
	public void onProviderDisabled(String provider) {

		locationStatus.setText(provider +  " disabled!");
	}


	public void switchToTelephony(View view) {
		Intent intent = new Intent(MainActivity.this,TelephonyActivity.class);
		startActivity(intent);
	}
}