package com.example.sensors.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sensors.R;


//this accelerometer sensor check 2
public class AccelerometerSensor extends AppCompatActivity implements SensorEventListener {

    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer_sensor);

        text=findViewById(R.id.textView);

        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {
            Sensor accleroSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accleroSensor != null) {
                sensorManager.registerListener(this,accleroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else{
            Toast.makeText(this, "sensor Manager is null", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            text.setText("x: "+event.values[0]+" Y: "+event.values[1]+" Z: "+event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
