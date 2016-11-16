package com.example.itzli.tiempo2;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Tiempo2Activity extends AppCompatActivity {

    private TimePicker timePicker;
    private TextView update_text;
    private Context context;
    private AlarmManager alarmManager;
    PendingIntent pendingIntent;

    Intent alarm_intent = new Intent(this, Alarm_Reciver.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo2);
        this.context =  this;
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        int hour = 0;
        int min = 0;

        //Inicializar TimePicker con la hora actual
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hour = timePicker.getHour();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                min = timePicker.getMinute();
            }
        }

        update_text = (TextView) findViewById(R.id.update_text);
        final Calendar calendar = Calendar.getInstance();

        Button start_alarm = (Button) findViewById(R.id.set_alarm);
        final Button cancel_alarm = (Button) findViewById(R.id.cancel);




        start_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                }


                int hour = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                }
                int minute = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    minute = timePicker.getMinute();
                }


                String hourst = String.valueOf(hour);
                String minutest = String.valueOf(minute);

                if (hour > 12){
                    hourst= String.valueOf(hour - 12);
                }
                if(minute < 10){
                    minutest = "0"+ String.valueOf(minute);
                }

                start_alarm_text("Alarma iniciada " + hourst + ":" + minutest);

                pendingIntent = PendingIntent.getBroadcast(Tiempo2Activity.this, 0,
                        alarm_intent, PendingIntent.FLAG_UPDATE_CURRENT);


                alarmManager.set (AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        pendingIntent);
            }
        });


        cancel_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_alarm_text("Alarma cancelada");
            }
        });
    }

    private void start_alarm_text(String output) {
        update_text.setText(output);
    }

}
