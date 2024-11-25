package com.example.sensors.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sensors.R;

public class NotificationDemo extends AppCompatActivity {

    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (notificationManager != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent intent=new Intent(NotificationDemo.this, SecondActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),100,intent,PendingIntent.FLAG_MUTABLE);
                Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.man);


              notification=new Notification.Builder(NotificationDemo.this,"abc")
                        .setSmallIcon(R.drawable.man)
                        .setContentText("hi, this is Raj!!")
                        .setContentTitle("new Massage")
                        .setChannelId("abc")
                        .setContentIntent(pendingIntent)
                      .setLargeIcon(picture)
                      .setAutoCancel(true)
                        .build();
           

            notificationManager.createNotificationChannel(new NotificationChannel("abc","alarm",NotificationManager.IMPORTANCE_HIGH));
            }
       
        }
        notificationManager.notify(1,notification);



    }
}
