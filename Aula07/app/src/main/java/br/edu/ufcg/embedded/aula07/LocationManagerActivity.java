package br.edu.ufcg.embedded.aula07;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

public class LocationManagerActivity extends AppCompatActivity
        implements LocationListener, MapFragment.OnLocationRequestListener {

    private static final String TAG = "location";
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean isNetworkProviderEnabled;
    private boolean isGpsProviderEnabled;
    private LocationManager mLocationManager;
    private Location mLocation;
    private FrameLayout mMapLayout;
    private TextView mLat, mLng;
    private Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_manager);

        mMapLayout = (FrameLayout) findViewById(R.id.layout_map);

        mLat = (TextView) findViewById(R.id.lat);
        mLng = (TextView) findViewById(R.id.lng);


        getSupportFragmentManager().beginTransaction().replace(R.id.layout_map,
                MapFragment.newInstance(0,0)).commit();

        mLocationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        isNetworkProviderEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isGpsProviderEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // verificar os providers disponiveis
        Log.d(TAG, String.format("NetworkProvider is %b", isNetworkProviderEnabled));
        Log.d(TAG, String.format("GpsProvider is %b", isGpsProviderEnabled));

        if (isGpsProviderEnabled && hasPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_DENIED) {

                    // inicia o listener, o ideal é que toda atualização do GPS seja feita
                    // dentro de um serviço, tendo em vista que o GPS demora um pouco para atualizar
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    updateLatLng(mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
                }
            }
        }


    }

    public boolean hasPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                !=
                PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.


                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED) {
                            mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            Log.d(TAG, String.format("After permission %s",mLocation.toString()));
                            updateLatLng(mLocation);
                        }
                    } else {
                        mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        Log.d(TAG, String.format("After permission %s",mLocation.toString()));
                        updateLatLng(mLocation);
                    }


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void updateLatLng(Location location) {

        if(location != null) {
            mCurrentLocation = location;
            if(mLat != null) {
                mLat.setText(String.format("Latitude: %.5f", location.getLatitude()));
            }

            if(mLng != null) {
                mLng.setText(String.format("Longitude: %.5f", location.getLongitude()));
            }
        }


    }

    @Override
    public void onLocationChanged (Location location){
        Log.d(TAG, String.format("Nova localizacao -> %s", location));
        updateLatLng(location);
    }

    @Override
    public void onStatusChanged (String provider,int status, Bundle extras){

    }

    @Override
    public void onProviderEnabled (String provider){
        Log.d(TAG, String.format("Provider enabled -> %s", provider));
    }

    @Override
    public void onProviderDisabled (String provider){
        Log.d(TAG, String.format("Provider disabled -> %s", provider));
    }

    @Override
    public Location getCurrentLocation() {
        return mCurrentLocation;
    }
}