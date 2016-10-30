package com.tesca.dabbaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DeliveryMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_main);
    }

    public void goDeliveryId(View view) {

        //Intent para llamar funcion que muestra el ID de de repartidor

        Intent intent = new Intent(this, DeliveryIDActivity.class);
        startActivity(intent);
    }
}
