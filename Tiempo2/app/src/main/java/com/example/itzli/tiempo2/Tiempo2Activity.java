package com.example.itzli.tiempo2;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Tiempo2Activity extends AppCompatActivity {

    TimePicker timePicker;
    TextView update_text;
    Context context;
    AlarmManager alarmManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            hour = timePicker.getHour();
            min = timePicker.getMinute();
        }

        update_text = (TextView) findViewById(R.id.update_text);
        Calendar calendar = Calendar.getInstance();

        final Button start_alarm = (Button) findViewById(R.id.set_alarm);
        Button cancel_alarm = (Button) findViewById(R.id.cancel);

        start_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start_alarm_text("Alarma iniciada");

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
