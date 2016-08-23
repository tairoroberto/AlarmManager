package br.com.trmasolucoes.alarmmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RingtonePlayingService extends Service {
    private static final String TAG = "Script";
    private MediaPlayer mediaPlayer;

    public RingtonePlayingService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("RingtonePlayingService", "Received start id " + startId + ": " + intent);

        //Create a instace of the mediaplayer
        mediaPlayer = MediaPlayer.create(this, R.raw.i_cold_have_lied);
        mediaPlayer.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this, "Notification stoped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
