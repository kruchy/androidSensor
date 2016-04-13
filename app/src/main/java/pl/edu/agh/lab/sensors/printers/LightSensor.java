package pl.edu.agh.lab.sensors.printers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;

public class LightSensor extends BaseSensor {
    @Override
    public int getSensorType() {
        return Sensor.TYPE_LIGHT;
    }

    @Override
    public void writeEventToOutput(SensorEvent event, TextView textView) {
        float currentLight = event.values[0];
        if(currentLight<1){textView.setText("Current light: " + "no Light");}
        else if(currentLight<5){textView.setText("Current light is Dim:" + String.valueOf(currentLight));}
        else if(currentLight<10){textView.setText("Current light is Normal:" + String.valueOf(currentLight));}
        else if(currentLight<100){textView.setText("Current light is Bright(Room):" + String.valueOf(currentLight));}
        else textView.setText("Current light is Bright(Sun):" + String.valueOf(currentLight));
    }
}
