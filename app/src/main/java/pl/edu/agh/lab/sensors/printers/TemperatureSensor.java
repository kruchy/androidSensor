package pl.edu.agh.lab.sensors.printers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;

public class TemperatureSensor extends BaseSensor{
    @Override
    public int getSensorType() {
        return Sensor.TYPE_AMBIENT_TEMPERATURE;
    }

    @Override
    public void writeEventToOutput(SensorEvent event, TextView textView) {
        textView.setText("Current temperature = " + event.values[0]);
    }
}
