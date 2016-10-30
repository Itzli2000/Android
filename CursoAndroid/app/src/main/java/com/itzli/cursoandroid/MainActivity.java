package com.itzli.cursoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviarDatos (View view){
        EditText edtNombre = (EditText) findViewById(R.id.nombre);
        String nombre = edtNombre.getText().toString();

        Toast.makeText(getBaseContext(), "Felicidades tu nombre es:  " + nombre, Toast.LENGTH_LONG).show();
    }
}