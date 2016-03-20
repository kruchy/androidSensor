package pl.edu.agh.lab.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Krzysiek on 2016-03-19.
 */
public class Gyroscope extends BaseSensor {
    private float[] gyroscope;


    @Override
    public int getSensorType() {
        return Sensor.TYPE_GYROSCOPE;
    }

    @Override
    public void writeEventToOutput(SensorEvent event,TextView textView) {
        String str = String.format("Gyroscope:\nX: %3.0f\nY: %3.0f\nZ: %3.0f",
                Math.toDegrees(event.values[0]), Math.toDegrees(event.values[1]), Math.toDegrees(event.values[2]));
        textView.setText(str);
    }
}
