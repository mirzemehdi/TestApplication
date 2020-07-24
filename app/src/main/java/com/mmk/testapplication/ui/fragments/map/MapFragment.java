package com.mmk.testapplication.ui.fragments.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mmk.testapplication.R;
import com.mmk.testapplication.ui.fragments.qrcode.QrCodeScannerFragment;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;


@AndroidEntryPoint
public class MapFragment extends Fragment implements OnMapReadyCallback, OnCompleteListener<Location> {
    private static final int ERROR_DIALOG_REQUEST = 1001;
    private GoogleMap mMap;
    private static final float cameraZoomDegree = 12f;
    private static final float minMapZoomPreference = 8f;
    private static final float maxMapZoomPreference = 24f;

    private MapViewModel mapViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapViewModel=new ViewModelProvider(this).get(MapViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (checkServicesOkay())
            requestLocationPermission();

    }



    private boolean checkServicesOkay(){
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());
        if (available== ConnectionResult.SUCCESS) return true;
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            GoogleApiAvailability.getInstance().getErrorDialog(getActivity(),available,ERROR_DIALOG_REQUEST);
        }
        else {
            Toast.makeText(getActivity(), getString(R.string.error_google_play_services), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void requestLocationPermission() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted())
                            initMap();
                        else
                            Toast.makeText(getActivity(), getString(R.string.denied_permission_location), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                    }

                }).check();
    }

    private void initMap() {
          SupportMapFragment mapFragment=
                  (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment) ;
          if (mapFragment!=null){
              mapFragment.getMapAsync(this);
          }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.setMinZoomPreference(minMapZoomPreference);
        mMap.setMaxZoomPreference(maxMapZoomPreference);
        getMyLocation();
        addStationLocations();

    }

    private void addStationLocations() {
        mapViewModel.getStationLocations().observe(getViewLifecycleOwner(), latLngList -> {
                for(LatLng latLng:latLngList){
                    MarkerOptions markerOptions=new MarkerOptions();
                    markerOptions.position(latLng);
                    mMap.addMarker(markerOptions);
                }
        });
    }

    private void getMyLocation() {
        if (getContext()!=null){
           FusedLocationProviderClient fusedLocationProviderClient
                   = LocationServices.getFusedLocationProviderClient(getContext());
            try {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(this);
            } catch (SecurityException e) {
                Timber.e(e);
            }
        }
    }

    @Override
    public void onComplete(@NonNull Task<Location> task) {
        if (task.isSuccessful() && task.getResult()!=null){
            LatLng myLocation=new LatLng(task.getResult().getLatitude(),task.getResult().getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,cameraZoomDegree));
        }
        else
            Timber.d("Could not get your location");

    }
}