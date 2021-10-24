package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.StringPrepParseException;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    Button btn;
    double latitude, longitude;
    FusedLocationProviderClient location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        location = LocationServices.getFusedLocationProviderClient(this);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                location.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Location loc = task.getResult();
                        intent.setData(Uri.parse("geo:" + loc.getLatitude() + ", " + loc.getLongitude()));
                        Intent chooser = Intent.createChooser(intent, "Launch Map");
                        startActivity(chooser);
                    }
                });
        }


    });


    //public void onClick(View v) {
//
    //    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
    //        return;
    //    }
    //    location.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
    //        @Override
    //        public void onComplete(@NonNull Task<Location> task) {
    //            Intent intent = new Intent(Intent.ACTION_VIEW);
    //            Location loc = task.getResult();
    //            intent.setData(Uri.parse("geo:" + loc.getLatitude() + ", " + loc.getLongitude()));
    //            Log.d("hatool", String.valueOf(latitude) + String.valueOf(longitude));
    //            Intent chooser = Intent.createChooser(intent, "Launch Map");
    //            startActivity(chooser);
    //        }
    //    });
    //}
}}