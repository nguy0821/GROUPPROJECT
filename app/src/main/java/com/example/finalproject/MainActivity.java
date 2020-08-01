package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.geo.GeoHome;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;

    public void goToGeo(View v) {
        startActivity(new Intent(v.getContext(), GeoHome.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button geoBtn = findViewById(R.id.geoDataBtn);
        geoBtn.setOnClickListener(v -> goToGeo(v));

        Toolbar toolbar = findViewById(R.id.home_toolbar);
//        setActionBar(toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.home_drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.d_act_name, R.string.app_name);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id){
                case R.id.dw_menu_geo:
                    startActivity(new Intent(this, GeoHome.class));
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_toolbar_menu, menu);

        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i("LOOGGGGG", "onOptionsItemSelected: hello");
        int id = item.getItemId();
        switch (id){
            case R.id.home_tb_geo:
                startActivity(new Intent(this, GeoHome.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
