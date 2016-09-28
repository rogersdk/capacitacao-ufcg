package br.edu.ufcg.embedded.aula07;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by rogerio on 27/09/16.
 */
public class MapFragment extends Fragment
        implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private static final String TAG = "location";
    private OnLocationRequestListener mLocationCallback;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.fragment_map, viewGroup, false);


        // Gets the MapView from the XML layout and creates it
        mMapView = (MapView) view.findViewById(R.id.mapview);
        mMapView.onCreate(bundle);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mMapView.getMapAsync(this);

        return view;
    }

    public static MapFragment newInstance(double lat, double lng) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();

        args.putDouble("lat", lat);
        args.putDouble("lng", lng);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mGoogleMap.setOnMyLocationButtonClickListener(this);

        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(true);
        }
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
//        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-37.2, 152.2)));
        addMarker(mGoogleMap, new LatLng(-37.2, 152.2));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-37.2, 152.2), 10));
    }

    private void addMarker(GoogleMap map, LatLng latLng) {
        map.clear();
        map.addMarker(new MarkerOptions().position(latLng));
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Location newLocation = mLocationCallback.getCurrentLocation();

        LatLng latLng = new LatLng(newLocation.getLatitude(), newLocation.getLongitude());
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        addMarker(mGoogleMap, latLng);

        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mLocationCallback = (OnLocationRequestListener) context;
    }

    public interface OnLocationRequestListener {
        Location getCurrentLocation();
    }
}
