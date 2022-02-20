package com.zerostudio.prueba_emqu_josesilva.Util;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.zerostudio.prueba_emqu_josesilva.R;
import com.zerostudio.prueba_emqu_josesilva.View.Login_view;
import com.zerostudio.prueba_emqu_josesilva.View.Principal_view;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Servicio extends IntentService {

    /**
     * @param name
     * @deprecated
     */
    public Servicio(String name) {
        super("hola");
    }
    public Servicio() {
        super("hola");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // return super.onStartCommand(intent, flags, startId);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Created_channel_noti();
            }
        };
        timer.schedule(task, 0, (60000)*60);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }


    public void Created_channel_noti(){


            Intent intent = new Intent(this, Login_view.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importancia = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(Util.idchannel, Util.channelname, importancia);
                channel.setDescription(Util.channeldescrip);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);

            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Util.idchannel)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentTitle("Importante")
                    .setContentText(Util.channeldescrip)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(Util.noti_id, builder.build());

        }

}
