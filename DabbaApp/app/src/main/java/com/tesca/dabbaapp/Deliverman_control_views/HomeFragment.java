package com.tesca.dabbaapp.Deliverman_control_views;


import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.Cast;
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
import com.tesca.dabbaapp.DelivermanLoginActivity;
import com.tesca.dabbaapp.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static com.tesca.dabbaapp.R.id.map;
import static com.tesca.dabbaapp.R.id.thing_proto;

/**
 * A simple {@link Fragment} subclass.
 */
public class    HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    Fragment fragment =  null;
    TextView textView;
    View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home,container, false);
        countDown();
        return root;

    }

    private void countDown() {

        //Controles para cronometro
        long horaentrega = 1800000;
        long horaActual = 300000;
        long hora = horaentrega - horaActual;


        textView = (TextView)root.findViewById(R.id.chronometer2);
        new CountDownTimer(horaActual, 1000) {

            public void onTick(long millisUntilFinished) {
                int v = (int) (millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                textView.setText(String.format("%02d",v)+":"+String.format("%02d",va));

                if(v == 3 && va == 59){
                    dialog();
                }
            }

            public void onFinish() {
                textView.setText("Fin del tiempo");
            }
        }.start();

    }

    private void dialog() {

        final AlertFragment dialog = new AlertFragment();
        dialog.show(getFragmentManager(),"dialogo");
        final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.alert04);
        mp.setLooping(true);
        mp.start();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss(); // Cierra el alert dialog
                mp.stop();
                t.cancel(); // Detiene el timer para evitar crash report
            }
        }, 5000); // Después de 5 segundos se inicia la actividad


    }


}

