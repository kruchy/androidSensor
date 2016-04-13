package pl.edu.agh.lab.sensors.printers;

import android.hardware.SensorEvent;
import android.widget.TextView;

/**
 * Created by Krzysiek on 2016-03-19.
 */
public abstract class BaseSensor {
    public abstract int getSensorType();


    private BaseSensor next;


    public abstract void writeEventToOutput(SensorEvent event, TextView textView);



    public void onSensorChanged(SensorEvent event,TextView textView) {
        if (event.sensor.getType() == getSensorType())
            writeEventToOutput(event,textView);
        else
            getNext().onSensorChanged(event,textView);
    }


    public BaseSensor getNext() {
        return next;
    }

    public void setNext(BaseSensor next) {
        if(this.next == null)
            this.next = next;
        else
            this.next.setNext(next);
    }
}
