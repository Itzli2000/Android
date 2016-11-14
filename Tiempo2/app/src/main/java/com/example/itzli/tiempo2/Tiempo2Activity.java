package com.example.itzli.tiempo2;

import android.app.TimePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Tiempo2Activity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private TimePicker timePicker;
    private Button btn;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo2);

        int hour = 0;
        int min = 0;

        //Inicializar TimePicker con la hora actual
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            hour = timePicker.getHour();
            min = timePicker.getMinute();
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        btn = (Button) findViewById(R.id.alarmBtn);


    }
}
