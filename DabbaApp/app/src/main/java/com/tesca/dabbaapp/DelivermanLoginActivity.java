package com.tesca.dabbaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DelivermanLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverman_login);

        showToolbar(getResources().getString(R.string.toolbar_deliver_login),true);


    }

    public void showToolbar (String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void goGoogleid(View view) {



    }

    public void goFacebookid(View view) {


    }

    public void goTwitterid(View view) {


    }

    public void goMailID(View view) {


        //Intent para llamar funcion que muestra el ID de de repartidor

        Intent intent = new Intent(this, DeliverIDActivity.class);
        startActivity(intent);
    }
}
