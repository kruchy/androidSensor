package pl.edu.agh.lab.sensors.printers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.TextView;

public class Accelerometer extends BaseSensor {
    private double[] gravity = new double[3];
    private double[] linear_acceleration = new double[3];



    @Override
    public int getSensorType() {
        return Sensor.TYPE_ACCELEROMETER;
    }

    @Override
    public void writeEventToOutput(SensorEvent event,TextView textView) {
        final float alpha = 200f/(200f+ (SensorManager.SENSOR_DELAY_NORMAL ));
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];


        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        String str = String.format("Accelerometer :\nX: %.2f\nY: %.2f\nZ: %.2f",
                linear_acceleration[0], linear_acceleration[1], linear_acceleration[2]);
        textView.setText(str);
    }

}
