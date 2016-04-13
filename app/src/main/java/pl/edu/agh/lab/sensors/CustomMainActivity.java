package pl.edu.agh.lab.sensors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pl.edu.agh.lab.sensors.telephony.TelephonyActivity;

public class CustomMainActivity extends AppCompatActivity{

    public void switchToTelephony(View view) {
        TelephonyActivity newFragment = new TelephonyActivity();
        replaceTransaction(newFragment);
    }

    private void replaceTransaction(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void switchToBluetooth(View view) {
        BluetoothFragment newFragment = new BluetoothFragment();
        replaceTransaction(newFragment);

    }

    public void switchToSensors(View view)
    {
        SensorFragment newFragment = new SensorFragment();
        replaceTransaction(newFragment);
    }

}
