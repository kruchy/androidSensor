package pl.edu.agh.lab.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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

public class SensorFragment extends Fragment implements SensorEventListener{

    private SensorManager sensorManager;
    private Gyroscope gyroscope;
    private HashMap<Integer, TextView> sensorMap = new HashMap<>();


    @Override
    public void onStart() {
        for (Sensor sensor : sensorManager.getSensorList(Sensor.TYPE_ALL)) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        super.onStart();
    }
    @Override
    public void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        gyroscope.onSensorChanged(event, sensorMap.get(event.sensor.getType()));

    }

    @Override
    public void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null)
            return super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment, container, false);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.Sensors);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        TextView textView2;
        int id = 0;
        for (Sensor sensor : sensorManager.getSensorList(Sensor.TYPE_ALL)) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            textView2 = new TextView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 10);
            textView2.setLayoutParams(params);
            layout.addView(textView2);
            sensorMap.put(sensor.getType(), textView2);
            id++;
        }

        gyroscope = new Gyroscope();
        Accelerometer accelerometer = new Accelerometer();
        LightSensor lightSensor = new LightSensor();
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        DefaultSensor defaultSensor = new DefaultSensor();
        gyroscope.setNext(accelerometer);
        gyroscope.setNext(lightSensor);
        gyroscope.setNext(temperatureSensor);
        gyroscope.setNext(defaultSensor);
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Made " + id + " sensors", Toast.LENGTH_SHORT);
        toast.show();
        return view;
    }
}
