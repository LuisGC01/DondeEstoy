package com.example.usuario_06.dondeestoy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView txtGps;
    LocationManager GPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtGps = (TextView) findViewById(R.id.txtGps);
        try {
            GPS = (LocationManager) getSystemService(LOCATION_SERVICE);
        }catch (Exception e){
            txtGps.setText("El error es "+e.getMessage());
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        GPS.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3, this);
    }



    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        txtGps.setBackgroundColor(Color.GREEN);
        txtGps.setText("Recibiendo informacion del gps");
    }

    @Override
    public void onProviderDisabled(String s) {
        txtGps.setBackgroundColor(Color.RED);
        txtGps.setText("Por favor enciende tu gps");
    }
    @Override
    public void onLocationChanged(Location location) {
        try {
            txtGps.setText("\naltitudud: " +
                    location.getAltitude() + " \nlongitud: " +
                    location.getLongitude() + " \nlatitud: " +
                    location.getLatitude() +
                     "\nvelocidad: " + location.getSpeed());
        }catch (Exception e){
            txtGps.setText("El error es "+e.getMessage());
        }
    }
}
