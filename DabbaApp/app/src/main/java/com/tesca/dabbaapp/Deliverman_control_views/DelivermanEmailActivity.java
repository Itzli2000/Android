package com.tesca.dabbaapp.Deliverman_control_views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tesca.dabbaapp.R;

public class DelivermanEmailActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";

    public static EditText mUser;
    public static EditText mPassword;
    private Button mLoginBtn;


    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverman_email);

        //Inicializar base de datos
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        DatabaseReference a =  mDatabase.getReference().child("Users").child("Deliver");

        mUser = (EditText) findViewById(R.id.deliver_user);
        mPassword = (EditText) findViewById(R.id.deliver_password);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){

                    // User is signed in
                    startActivity(new Intent(DelivermanEmailActivity.this, DeliverNavigationActivity.class));

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

            Toast.makeText(DelivermanEmailActivity.this,"Revisar campos",Toast.LENGTH_LONG).show();

        }else{
            mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {

                        Toast.makeText(DelivermanEmailActivity.this, "Error de inicio", Toast.LENGTH_LONG).show();

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

}
