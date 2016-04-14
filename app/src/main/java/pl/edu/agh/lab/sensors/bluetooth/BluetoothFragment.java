package pl.edu.agh.lab.sensors.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pl.edu.agh.lab.sensors.R;

public class BluetoothFragment extends Fragment {
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVERABLE_BT = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bluetooth_activity, container, false);
        final TextView out=(TextView) view.findViewById(R.id.out);
        final Button button1 = (Button) view.findViewById(R.id.button1);
        final Button button2 = (Button) view.findViewById(R.id.button2);
        final Button button3 = (Button) view.findViewById(R.id.button3);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            out.append("device not supported");
        }
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mBluetoothAdapter != null) {
                    if (!mBluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mBluetoothAdapter != null) {
                    if (!mBluetoothAdapter.isDiscovering()) {
                        Context context = getActivity().getApplicationContext();
                        CharSequence text = "MAKING YOUR DEVICE DISCOVERABLE";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        startActivityForResult(enableBtIntent, REQUEST_DISCOVERABLE_BT);

                    }
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mBluetoothAdapter != null) {
                    mBluetoothAdapter.disable();
                }
                Context context = getActivity().getApplicationContext();
                CharSequence text = "TURNING_OFF BLUETOOTH";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                toast.show();
            }
        });


        if (mBluetoothAdapter == null) {
            out.append("device not supported");
        }
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mBluetoothAdapter != null) {
                    if (!mBluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mBluetoothAdapter != null) {
                    if (!mBluetoothAdapter.isDiscovering()) {
                        Context context = getActivity().getApplicationContext();
                        CharSequence text = "MAKING YOUR DEVICE DISCOVERABLE";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        startActivityForResult(enableBtIntent, REQUEST_DISCOVERABLE_BT);

                    }
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mBluetoothAdapter != null) {
                    mBluetoothAdapter.disable();
                }
                Context context = getActivity().getApplicationContext();
                CharSequence text = "TURNING_OFF BLUETOOTH";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return view;

    }

}
