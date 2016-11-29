package com.tesca.dabbaapp;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    FloatingActionMenu menu;
    FloatingActionButton fab1, fab2, fab3;

    private FirebaseAuth mAuth;

    private GoogleMap mMap;
    private String TAG = "Maps_Activity";
    private LatLng destination = new LatLng(19.525170, -99.226120);
    private TextView textView, status_tv, costo_tv, id_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mAuth = FirebaseAuth.getInstance();

        menu = (FloatingActionMenu) findViewById(R.id.menu);
        fab1 = (FloatingActionButton) findViewById(R.id.menu_item1);
        fab2 = (FloatingActionButton) findViewById(R.id.menu_item2);
        fab3 = (FloatingActionButton) findViewById(R.id.menu_item3);

        fab1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.signOut();
                //Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                startActivity(new Intent(MapsActivity.this, LoginActivity.class));
            }
        });

        fab2.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent(MapsActivity.this, EntregasActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Entregas",Toast.LENGTH_LONG).show();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Mapa",Toast.LENGTH_LONG).show();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView = (TextView)findViewById(R.id.countdown);
        id_tv = (TextView)findViewById(R.id.id);

        countDown();
    }

    private void countDown() {

        //Controles para cronometro
        long horaentrega = 1800000;
        long horaActual = 300000;
        long hora = horaentrega - horaActual;


        textView = (TextView) findViewById(R.id.countdown);
        textView.setBackgroundColor(Color.GREEN);
        new CountDownTimer(horaActual, 1000) {

            public void onTick(long millisUntilFinished) {
                int v = (int) (millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                textView.setText(String.format("%02d",v)+":"+String.format("%02d",va));

                if(v == 3 && va == 59){
                    textView.setBackgroundColor(Color.YELLOW);
                    dialog();
                }

                if (v == 3 && va == 30){
                    notif();
                }
                if(v == 1 && va == 0){
                    textView.setBackgroundColor(Color.RED);
                }
            }

            public void onFinish() {
                textView.setTextSize(30);
                textView.setText("Fin del tiempo");
            }
        }.start();

    }

    private void notif() {

        NotificationCompat.Builder  notif = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.moto2)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.moto2))
                .setContentTitle("Nueva entrega")
                .setContentText("Revisa la seccion de entregas");
        notif.setAutoCancel(true);
        notif.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        NotificationManagerCompat notifman = NotificationManagerCompat.from (this);
        notifman.notify(1, notif.build());

    }

    private void dialog() {

        final AlertFragment dialog = new AlertFragment();
        dialog.show(getSupportFragmentManager(),"dialog");
        final MediaPlayer mp = MediaPlayer.create(MapsActivity.this, R.raw.alert);
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss(); // Cierra el alert dialog
                t.cancel(); // Detiene el timer para evitar crash report
            }
        }, 5000); // Después de 5 segundos se inicia la actividad


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        float zoomlevel = 20;


        // Add a marker
        LatLng TESCA = new LatLng(19.526664, -99.235235);
        mMap.addMarker(new MarkerOptions().position(TESCA).title("Marker in TESCA"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TESCA,zoomlevel));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(TESCA)
                .zoom(zoomlevel)
                .bearing(0)
                .tilt(70)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}

