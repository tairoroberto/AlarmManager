package br.com.trmasolucoes.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "Script";

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG, "onReceive: We are into the receiver");

        //Create a intent that into  ringtone service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        // start the rigtone service
        context.startService(intent);
    }
}
