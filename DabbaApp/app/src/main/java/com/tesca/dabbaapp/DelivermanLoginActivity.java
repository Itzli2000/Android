package com.tesca.dabbaapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tesca.dabbaapp.Deliverman_control_views.DeliverNavigationActivity;

import java.security.PrivateKey;

public class DelivermanLoginActivity extends AppCompatActivity {

    public static EditText mUser;
    public static EditText mPassword;
    private Button mLoginBtn;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverman_login);

        //Inicializar base de datos
        mAuth = FirebaseAuth.getInstance();

        mUser = (EditText) findViewById(R.id.deliver_user);
        mPassword = (EditText) findViewById(R.id.deliver_password);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){

                    startActivity(new Intent(DelivermanLoginActivity.this, DeliverNavigationActivity.class));

                }

            }
        };

        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });





        showToolbar(getResources().getString(R.string.toolbar_deliver_login),true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn (){
        String user = mUser.getText().toString();

        String pass = mPassword.getText().toString();

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {

            Toast.makeText(DelivermanLoginActivity.this,"Revisar campos",Toast.LENGTH_LONG).show();

        }else{
            mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {

                        Toast.makeText(DelivermanLoginActivity.this, "Error de inicio", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }


    }


    public void showToolbar (String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    /*public void goMailID(View view) {

        //Intent para llamar funcion que muestra el ID de de repartidor

        Intent intent = new Intent(this, DeliverNavigationActivity.class);
        startActivity(intent);
    }*/
}
