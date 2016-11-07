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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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
public class    HomeFragment extends Fragment  implements OnMapReadyCallback{


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
        long horaentrega = 17;
        long horaActual = Calendar.getInstance().HOUR_OF_DAY;
        long hora = (horaentrega - horaActual)*1000;


        textView = (TextView)root.findViewById(R.id.chronometer2);
        new CountDownTimer(hora, 1000) {

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

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}

