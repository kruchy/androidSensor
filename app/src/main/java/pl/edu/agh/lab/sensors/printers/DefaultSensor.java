package pl.edu.agh.lab.sensors.printers;

import android.hardware.SensorEvent;
import android.widget.TextView;

/**
 * Created by Krzysiek on 2016-03-20.
 */
public class DefaultSensor extends BaseSensor {
    StringBuilder builder;
    public DefaultSensor() {
    builder = new StringBuilder();
    }

    @Override
    public int getSensorType() {
        return 0;
    }

    @Override
    public void onSensorChanged(SensorEvent event,TextView textView){
        writeEventToOutput(event,textView);
    }

    @Override
    public void writeEventToOutput(SensorEvent event,TextView textView) {
        for(float i : event.values)
        {
            builder.append(i + " ");
        }
        textView.setText(event.sensor.getName() + " " + builder.toString());
        builder.delete(0,builder.length());
    }
}
