package com.example.sensors;

import android.content.ContentProvider;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sensors.accelerometer.AccelerometerSensor;
import com.example.sensors.content.ContentProviderDemo;
import com.example.sensors.notification.NotificationDemo;
import com.example.sensors.proximity.ProximitySensor;

public class MainActivity extends AppCompatActivity {

    Button accelerometerBtn,proximity,btnNotification,btnContentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometerBtn=findViewById(R.id.accelerometerBtn);
        proximity=findViewById(R.id.proximity);
        btnNotification=findViewById(R.id.btnNotification);
        btnContentProvider=findViewById(R.id.btnContentProvider);


        accelerometerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AccelerometerSensor.class);
                startActivity(intent);
            }
        });
        proximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ProximitySensor.class);
                startActivity(intent);
            }
        });


        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, NotificationDemo.class);
                startActivity(intent);
            }
        });

        btnContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, ContentProviderDemo.class);
                startActivity(intent);
            }
        });

    }
}