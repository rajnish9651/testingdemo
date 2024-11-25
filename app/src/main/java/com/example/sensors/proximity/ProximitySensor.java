package com.example.sensors.proximity;

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

public class ProximitySensor extends AppCompatActivity implements SensorEventListener {

    TextView proximity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proximity_sensor);

        proximity=findViewById(R.id.textProximity);

        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            Sensor proximitySensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if (proximitySensor != null) {
                sensorManager.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else{
            Toast.makeText(this, "sensor manager is null", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximity.setText("value "+event.values[0]);
            if (event.values[0] > 0) {
                Toast.makeText(this, "Object is far", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Object is near", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
