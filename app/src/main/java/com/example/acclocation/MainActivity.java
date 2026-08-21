package com.example.acclocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnHelp;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnHelp = findViewById(R.id.btnHelp);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnHelp.setOnClickListener(v -> {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        100
                );
                return;
            }

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        if (location == null) {
                            Toast.makeText(this,
                                    "Location not available", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
                            List<Address> addresses =
                                    geocoder.getFromLocation(
                                            location.getLatitude(),
                                            location.getLongitude(),
                                            1
                                    );

                            String loc = addresses.get(0).getAddressLine(0);

                            String msg = "HELP!\nMy location:\n" + loc;

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.setPackage("com.whatsapp");
                            intent.putExtra(Intent.EXTRA_TEXT, msg);

                            startActivity(intent);

                        } catch (Exception e) {
                            Toast.makeText(this,
                                    "Error getting address", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
