package com.tesca.dabbaapp.Deliverman_control_views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.tesca.dabbaapp.LoginActivity;
import com.tesca.dabbaapp.R;

public class DeliverNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Pantalla de navegacion HOME, cargada por defecto
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFragment = new HomeFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_deliver_navigation, homeFragment, homeFragment.getTag()).commit();
        Toast.makeText(this,"Principal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.deliver_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            startActivity(new Intent(DeliverNavigationActivity.this, LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            // Acciones para opción HOME
            HomeFragment homeFragment = new HomeFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_deliver_navigation, homeFragment, homeFragment.getTag()).commit();
            Toast.makeText(this,"Principal", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_finish) {

            //Pantalla que muesta la informacion del pedido actual, para ser entregado y dado por completado
            CompleteFragment completeFragment = new CompleteFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_deliver_navigation, completeFragment, completeFragment.getTag()).commit();
            Toast.makeText(this,"Entrega completada", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.deliver_list) {

            //Pantalla que muestra la lista de entregas pendientes
            DeliversFragment deliversFragment = new DeliversFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_deliver_navigation, deliversFragment, deliversFragment.getTag()).commit();
            Toast.makeText(this, "Lista pendientes", Toast.LENGTH_SHORT).show();

        }/*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
