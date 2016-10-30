package com.tesca.dabbaapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DeliveryIDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_id);

        showToolbar(getResources().getString(R.string.toolbar_deliver_id),true);

        deliver_list();
    }

    public void showToolbar (String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void deliver_list() {

        //Datos a mostrar
        String [] deliverlist = {"Entrega 1", "Entrega 2","Entrega3","Entrega 4",
                                  "Entrega 5", "Entrega 6", "Entrega 7", "Entrega 8"};

        //Adaptar la forma de mostrar datos
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deliverlist);
        ListView listview = (ListView)  findViewById(R.id.list_deliver);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String entrega = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(DeliveryIDActivity.this, entrega, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
