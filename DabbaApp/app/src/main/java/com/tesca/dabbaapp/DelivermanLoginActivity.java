package com.tesca.dabbaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tesca.dabbaapp.Deliverman_control_views.DeliverNavigationActivity;

public class DelivermanLoginActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverman_login);

        //Inicializar base de datos
        mDatabase = FirebaseDatabase.getInstance();

        //Escribir en de base datos
        DatabaseReference a =  mDatabase.getReference().child("Delivery").child("Test1");
        mDatabase.getReference().child("Delivery").child("Test1").setValue("abcdef");

        //Leer desde base de datos
        DatabaseReference b = mDatabase.getReference().push();//Genera una clave unica
        b.setValue("Test");//Escribe informacion en la clave generada

        a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String test = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        showToolbar(getResources().getString(R.string.toolbar_deliver_login),true);


    }

    public void showToolbar (String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    public void goMailID(View view) {


        //Intent para llamar funcion que muestra el ID de de repartidor

        Intent intent = new Intent(this, DeliverNavigationActivity.class);
        startActivity(intent);
    }
}
