package com.example.drdr_.dabbaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.android.gms.R.id.url;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private String TAG = "TAG";
    private static String url = "http://192.168.0.15:8080/api/v1/customer-orders/?format=json";
    ArrayList<HashMap<String, String>> contactList;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        lv = (ListView)findViewById(R.id.lista);

        new GetContacts().execute();

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    int count = jsonObj.getInt("count");
                    JSONArray results = jsonObj.getJSONArray("results");

                    // looping through All Contacts
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject c = results.getJSONObject(i);


                        String created_at = c.getString("created_at");



                        // Phone node is JSON Object
                        String id = c.getString("id");
                        String latitude = c.getString("latitude");
                        String longitude = c.getString("longitude");
                        String price = c.getString("price");
                        String customer = c.getString("customer");
                        String status = c.getString("status");
                        String created_time = c.getString("created_at");

                        String delivery = c.getString("delivery_date");

                        String[] delivery_date_time = delivery.split("T");
                        String delivery_date = delivery_date_time[0];
                        String delivery_time = delivery_date_time[1];

                        String showTime = delivery_time.substring(0,5);

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("latitude", latitude);
                        contact.put("longitude", longitude);
                        contact.put("price", price);
                        contact.put("customer",customer);
                        contact.put("status",status);
                        contact.put("created_time",created_time);
                        contact.put("delivery_date",delivery_date);
                        contact.put("delivery_time",delivery_time);
                        contact.put("showTime",showTime);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[]{"customer","price","status","showtime"}, new int[]{R.id.customer,R.id.price, R.id.status,R.id.hour_tv});

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    HashMap<String, String> c = contactList.get(i);

                    String id = c.get("id");
                    String latitude = c.get("latitude");
                    String longitude = c.get("longitude");
                    String price = c.get("price");
                    String customer = c.get("customer");
                    String status = c.get("status");
                    String created_time = c.get("created_at");

                    Intent intent = new Intent(MainActivity.this,MapsActivity.class);

                    intent.putExtra("id",id);
                    intent.putExtra("customer",customer);
                    intent.putExtra("latitude",latitude);
                    intent.putExtra("longitude",longitude);
                    intent.putExtra("status",status);
                    intent.putExtra("created_time",created_time);
                    intent.putExtra("price",price);

                    startActivity(intent);


                }
            });
        }

    }
}
