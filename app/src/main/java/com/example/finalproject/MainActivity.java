package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.geo.GeoHome;

public class MainActivity extends AppCompatActivity {

    public void goToGeo(View v){
        startActivity(new Intent(v.getContext(), GeoHome.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button geoBtn = findViewById(R.id.geoDataBtn);
        geoBtn.setOnClickListener(v -> goToGeo(v));
    }
}
