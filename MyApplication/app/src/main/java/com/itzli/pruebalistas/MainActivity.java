package com.itzli.pruebalistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView; //Declaramos listview y su nombre
    String[] elementos={"Ariel", "Itzli","Molina", "Sánchez","Pedro","Juan","Daniel", "Giovanni"}; //Declaramos variable y le asignamos elementos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrutasVerduras frutasVerduras_datos[] =  new FrutasVerduras[]{
                new FrutasVerduras(R.drawable.ic_launcher,"Manzana"),
                new FrutasVerduras(R.drawable.ic_launcher,"Kiwi"),
                new FrutasVerduras(R.drawable.ic_launcher,"Pera"),
                new FrutasVerduras(R.drawable.ic_launcher,"Naranja"),
                new FrutasVerduras(R.drawable.ic_launcher,"Mango"),
                new FrutasVerduras(R.drawable.ic_launcher,"Maracuya"),
                new FrutasVerduras(R.drawable.ic_launcher,"Carambolo"),
                new FrutasVerduras(R.drawable.ic_launcher,"Pitaya"),
                new FrutasVerduras(R.drawable.ic_launcher,"Cacao"),
                new FrutasVerduras(R.drawable.ic_launcher,"Platano"),
        };

        FrutasVerdurasAdapter adapter = new FrutasVerdurasAdapter(this,R.layout.listview_item_row,frutasVerduras_datos);

        listView=(ListView) findViewById(R.id.listview); //Metodo para llamar listView

        View header = (View) getLayoutInflater().inflate(R.layout.list_header_row,null);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView v =(TextView)view.findViewById(R.id.tv);
                Toast.makeText(getApplicationContext(),v.getText(),Toast.LENGTH_SHORT).show();
            }
        });


       /* ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,elementos);//Adaptador

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //Sabemos que elemento de la lista fue tocado
            @Override                                                             //y se muestra en pantalla
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), String.valueOf(position),Toast.LENGTH_SHORT).show() ;
            }
        });*/
    }
}
