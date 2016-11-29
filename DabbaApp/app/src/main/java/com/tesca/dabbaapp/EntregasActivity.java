package com.tesca.dabbaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;

public class EntregasActivity extends AppCompatActivity {

    FloatingActionMenu menu;
    FloatingActionButton fab1, fab2, fab3;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);

        mFirebaseAuth = FirebaseAuth.getInstance();

        menu = (FloatingActionMenu) findViewById(R.id.menu);
        fab1 = (FloatingActionButton) findViewById(R.id.menu_item1);
        fab2 = (FloatingActionButton) findViewById(R.id.menu_item2);
        fab3 = (FloatingActionButton) findViewById(R.id.menu_item3);

        fab1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                //Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                startActivity(new Intent(EntregasActivity.this, LoginActivity.class));
            }
        });

        fab2.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent(EntregasActivity.this, EntregasActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Entregas",Toast.LENGTH_LONG).show();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent(EntregasActivity.this, MapsActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Mapa",Toast.LENGTH_LONG).show();
            }
        });
    }
}
