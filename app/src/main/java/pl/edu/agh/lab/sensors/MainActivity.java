package pl.edu.agh.lab.sensors;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import pl.edu.agh.lab.sensors.adapter.ViewPagerAdapter;
import pl.edu.agh.lab.sensors.bluetooth.BluetoothFragment;
import pl.edu.agh.lab.sensors.contacts.ContactsFragment;
import pl.edu.agh.lab.sensors.telephony.TelephonyActivity;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView location;
    private TextView locationStatus;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icons);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new BluetoothFragment(), "Bluetooth");
        adapter.addFrag(new SensorFragment(), "Sensors");
        adapter.addFrag(new TelephonyActivity(), "Telephony");
        adapter.addFrag(new ContactsFragment(), "Contacts");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_call);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab_contacts);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_favourite);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_launcher);
//        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

//        if (findViewById(R.id.fragment_container) != null) {
//
//            if (savedInstanceState != null) {
//                return;
//            }
//
//            // Create a new Fragment to be placed in the activity layout
//            BluetoothFragment firstFragment = new BluetoothFragment();
//
//            // In case this activity was started with special instructions from an
//            // Intent, pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(getIntent().getExtras());
//
//            // Add the fragment to the 'fragment_container' FrameLayout
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, firstFragment).commit();
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }








    @Override
    public void onLocationChanged(Location location) {
        this.location.setText(location.getLatitude() + " " + location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        locationStatus.setText(provider + " status: " + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        locationStatus.setText(provider + " enabled!");
    }

    @Override
    public void onProviderDisabled(String provider) {

        locationStatus.setText(provider + " disabled!");
    }


}