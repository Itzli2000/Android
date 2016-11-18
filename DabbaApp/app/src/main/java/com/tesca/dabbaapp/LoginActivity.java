package com.tesca.dabbaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tesca.dabbaapp.Deliverman_control_views.DelivermanEmailActivity;
import com.tesca.dabbaapp.Deliverman_control_views.DelivermanGoogleActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void goEmailLog(View view) {

        Intent intent = new Intent(LoginActivity.this, DelivermanEmailActivity.class);
        startActivity(intent);
    }

    public void GoGoogleLog(View view) {

        Intent intent = new Intent(LoginActivity.this, DelivermanGoogleActivity.class);
        startActivity(intent);
    }
}
