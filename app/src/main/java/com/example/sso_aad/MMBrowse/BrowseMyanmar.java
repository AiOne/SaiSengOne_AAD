package com.example.sso_aad.MMBrowse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.sso_aad.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.lang.reflect.Array;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class BrowseMyanmar extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnMapReadyCallback{
    private static final int LOCATION_PERMISSION =1;
    Button satButton;
    boolean MapViewType;
    private Spinner spinnerTown;
    private String[] townList;
    private String town;
    private SupportMapFragment mapFragment;
    private double latitute;
    private double longitute;
    private Marker myMarker;
    private LocationData locationData;
    private double[] myCurrentLocation;
    private GoogleMap mgoogleMap;
    private float zoom_level = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_myanmar);
        townList = getResources().getStringArray(R.array.Town);
        spinnerTown = (Spinner) findViewById(R.id.townSpinner);
        locationData = new LocationData();
        myCurrentLocation = new double[] {16.8409,96.1735};
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.Town,
                android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTown.setAdapter(spinnerAdapter);
        spinnerTown.setOnItemSelectedListener(this);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        satButton = (Button) findViewById(R.id.mapViewType);
        MapViewType = false;
    }
    @Override
    protected void onStart(){
        super.onStart();

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(permission == PERMISSION_GRANTED){

        }else ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        }, LOCATION_PERMISSION);
    }
    private void getMyLocation(){
        FusedLocationProviderClient fusedLocationProviderClient;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    myCurrentLocation = new double[]{location.getLatitude(), location.getLongitude()};
                    Toast.makeText(getBaseContext(), "Location Accepted!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        town = townList[position];
        Toast.makeText(this, town + " Selected", Toast.LENGTH_SHORT).show();

        myMarker.setPosition(locationData.getLatLong(position));
        myMarker.setTitle(town);
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationData.getLatLong(position),zoom_level));


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        town = "No Town Selected";
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = locationData.getLatLong(0);
        googleMap.clear();
        MarkerOptions townMarker = new MarkerOptions().position(location);
        myMarker = googleMap.addMarker(townMarker);
//        myMarker.setTitle(town);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,zoom_level));
        mgoogleMap = googleMap;

    }


    public void pinMyCurrentLocation(View v){
        getMyLocation();
        myMarker.setPosition(new LatLng(myCurrentLocation[0], myCurrentLocation[1]));
        myMarker.setTitle("Your Current Location");
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myCurrentLocation[0], myCurrentLocation[1]),zoom_level));
    }

    public void SatelliteChange(View v){
        if(!MapViewType) {
            mgoogleMap.setMapType(mgoogleMap.MAP_TYPE_SATELLITE);
            MapViewType = true;
            satButton.setText("NORMAL");
        }
        else{
            satButton.setText("SATELLITE");
            MapViewType = false;
            mgoogleMap.setMapType(mgoogleMap.MAP_TYPE_NORMAL);
        }
    }

}


