package com.tesca.dabbaapp.Deliverman_control_views;

import android.app.Notification;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tesca.dabbaapp.R;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class    HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

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
        textView.setBackgroundColor(getResources().getColor(R.color.DarckGreen));
        new CountDownTimer(horaActual, 1000) {

            public void onTick(long millisUntilFinished) {
                int v = (int) (millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                textView.setText(String.format("%02d",v)+":"+String.format("%02d",va));

                if(v == 3 && va == 59){
                    dialog();
                }
                
                if (v == 3 && va == 30){
                    notif();
                }
            }

            public void onFinish() {
                textView.setTextSize(40);
                textView.setBackgroundColor(0xfff00000);
                textView.setText("Fin del tiempo");
            }
        }.start();

    }

    private void notif() {

        NotificationCompat.Builder  notif = (NotificationCompat.Builder) new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.moto2)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.moto2))
                .setContentTitle("Nueva entrega")
                .setContentText("Revisa la seccion de entregas");
        notif.setAutoCancel(true);
        notif.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        NotificationManagerCompat notifman = NotificationManagerCompat.from (getActivity());
        notifman.notify(1, notif.build());

    }

    private void dialog() {

        final AlertFragment dialog = new AlertFragment();
        dialog.show(getFragmentManager(),"dialog");
        final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.alert);
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


}

