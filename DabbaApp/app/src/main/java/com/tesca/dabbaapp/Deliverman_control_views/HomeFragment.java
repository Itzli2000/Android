package com.tesca.dabbaapp.Deliverman_control_views;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tesca.dabbaapp.R;

import static com.tesca.dabbaapp.R.id.mapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


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

        root = inflater.inflate(R.layout.fragment_home,null, false);

        countDown();
       // initializeMap();

        return root;

    }

    /*private void initializeMap() {

        //Controles para mapa
        SupportMapFragment mSupportMapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(mapFragment);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(mapFragment, mSupportMapFragment).commit();
        }
        if (mSupportMapFragment != null)
        {
            GoogleMap googleMap = mSupportMapFragment.getMapAsync(OnMapReadyCallback mapFragment);
            if (googleMap != null)
                googleMap.addMarker(new MarkerOptions().position(new LatLng(10.1253,10.5868)));
        }

    }*/

    private void countDown() {

        //Controles para cronometro
        textView = (TextView)root.findViewById(R.id.chronometer2);

        new CountDownTimer(30000, 1000){
            public void onTick (long millisUntilFinished){
                textView.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish(){
                textView.setText("Fin del tiempo");
            }
        }.start();

    }

}