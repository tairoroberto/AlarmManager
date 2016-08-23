package br.com.trmasolucoes.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // Declare our variablesr
    private AlarmManager alarmManager;
    private TimePicker timePicker;
    private Button btnAlarmOn,btnAlarmOff;
    private TextView updateText;
    private Context context;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        //Initialize our AlarmManager
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //Initialize our timepicker
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        //Initialize our TextView
        updateText = (TextView) findViewById(R.id.update_text);

        //Create a instance of Calendar
        final Calendar calendar = Calendar.getInstance();

        //Initialize Buttons
        btnAlarmOn = (Button)findViewById(R.id.btn_alarm_on);
        btnAlarmOff = (Button)findViewById(R.id.btn_alarm_off);

        // Create a intent to our Alarma Receiver
        final Intent intentAlarmReceiver = new Intent(context, AlarmReceiver.class);


        btnAlarmOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText.setText(R.string.txt_alarm_on);

                //Set calewndar with the hour and minute that we are picked in
                // timepicker
                calendar.set(Calendar.HOUR, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (minute < 10){
                    minute_string = "0" + String.valueOf(minute);
                }

                updateText.setText("Alarm set to :" + hour_string +":"+ minute_string);

                //Create a pending intent that  delays the intent until the specify calendar time
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intentAlarmReceiver, PendingIntent.FLAG_UPDATE_CURRENT);

                // Set alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }
        });

        btnAlarmOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText.setText(R.string.txt_alarm_off);

                alarmManager.cancel(pendingIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
