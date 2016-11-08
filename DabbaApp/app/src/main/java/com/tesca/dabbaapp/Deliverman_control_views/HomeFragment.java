package com.tesca.dabbaapp.Deliverman_control_views;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;
import com.tesca.dabbaapp.R;

import java.sql.Time;
import java.util.Calendar;

import static com.tesca.dabbaapp.R.id.map1;
import static com.tesca.dabbaapp.R.id.thing_proto;

/**
 * A simple {@link Fragment} subclass.
 */
public class    HomeFragment extends Fragment {

    private MapView mMapView;
    private GoogleMap mMap;
    private Bundle mBundle;


    public HomeFragment() {
        // Required empty public constructor
    }

    private SupportMapFragment mSupportMapFragment;


    Fragment fragment =  null;
    TextView textView;
    View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        root = inflater.inflate(R.layout.fragment_home,container, false);


        //Nueva codificacion para mapa

        MapsInitializer.initialize(getActivity());

        mMapView = (MapView) root.findViewById(R.id.map1);
        mMapView.onCreate(mBundle);
        setUpMapIfNeeded(root);

        countDown();
        //initializeMap();

        return root;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = savedInstanceState;
    }

    private void setUpMapIfNeeded(View inflatedView) {
        if (mMap == null) {
            ((MapView) inflatedView.findViewById(R.id.map1)).getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    if (googleMap == null) {

                        googleMap.getUiSettings().setAllGesturesEnabled(true);

                        LatLng mexico = new LatLng(19.432608,-99.133209);

                        CameraPosition cameraPosition = new CameraPosition.Builder().target(mexico).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);

                    }
                }
            });
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
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
        mMapView.onDestroy();
        super.onDestroy();
    }

    //Antigüa codificadion de mapa
    private void initializeMap() {


        //Controles para mapa

        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map1, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null)
        {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {

                        googleMap.getUiSettings().setAllGesturesEnabled(true);

                        LatLng mexico = new LatLng(19.432608,-99.133209);

                        CameraPosition cameraPosition = new CameraPosition.Builder().target(mexico).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);

                    }

                }
            });
        }


    }

    private void countDown() {

        //Controles para cronometro
        long horaentrega = 1800000;
        long horaActual = 7200000;
        long hora = horaentrega - horaActual;


        textView = (TextView)root.findViewById(R.id.chronometer2);
        new CountDownTimer(horaentrega, 1000) {

            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                textView.setText(v+":"+String.format("%02d",va));
            }

            public void onFinish() {
                textView.setText("Fin del tiempo");
            }
        }.start();


    }

}

