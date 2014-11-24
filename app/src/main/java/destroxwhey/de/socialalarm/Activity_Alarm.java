package destroxwhey.de.socialalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.security.acl.NotOwnerException;
import java.util.Calendar;


public class Activity_Alarm extends Activity {
    private int alarmImage = R.drawable.alarm;
    //Widgets
    private ListView alarm_listView;
    private EditText et_name;
    private EditText et_hour;
    private EditText et_minute;
    private TextView tv_M;
    private TextView tv_TU;
    private TextView tv_W;
    private TextView tv_TH;
    private TextView tv_F;
    private TextView tv_SA;
    private TextView tv_SU;

    private SocialAlarm socialAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__alarm);
        socialAlarm = (SocialAlarm)getApplicationContext();

        alarm_listView = (ListView) findViewById(R.id.alarm_listView);
        et_name = (EditText) findViewById(R.id.alarm_et_name);
        et_hour = (EditText) findViewById(R.id.alarm_et_hour);
        et_minute = (EditText) findViewById(R.id.alarm_et_minute);
        tv_M = (TextView) findViewById(R.id.alarm_tv_M);
        tv_TU = (TextView) findViewById(R.id.alarm_tv_TU);
        tv_W = (TextView) findViewById(R.id.alarm_tv_W);
        tv_TH = (TextView) findViewById(R.id.alarm_tv_TH);
        tv_F = (TextView) findViewById(R.id.alarm_tv_F);
        tv_SA = (TextView) findViewById(R.id.alarm_tv_SA);
        tv_SU = (TextView) findViewById(R.id.alarm_tv_SU);

        Intent myIntent = getIntent();
        if(myIntent.getIntExtra("intent", 0) == 1){
            int position = myIntent.getIntExtra("position", 0);
            et_name.setText(socialAlarm.myAlarms.get(position).getName());
            et_hour.setText(String.valueOf(socialAlarm.myAlarms.get(position).getHour()));
            et_minute.setText(String.valueOf(socialAlarm.myAlarms.get(position).getMinute()));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__alarm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAlarm(){
        //TODO: Change requestCode and update by index of Alarms
        int requestCode = socialAlarm.myAlarms.size();
        Intent myIntent = new Intent(this, NotifyService.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, myIntent, PendingIntent.FLAG_ONE_SHOT);

        Calendar calendar = Calendar.getInstance();
        int hour = Integer.parseInt(et_hour.getText().toString());
        int minute = Integer.parseInt(et_minute.getText().toString());
        String name = et_name.getText().toString();

        Log.d("Alarm", String.valueOf(hour)+" "+String.valueOf(minute));
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        Log.d("Alarm" , String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)+" "+ calendar.get(Calendar.MINUTE)+" "+ calendar.get(Calendar.DAY_OF_MONTH)));

        //Without seperate Days
        alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);

        socialAlarm.dbAdapter.insertRow(name, hour, minute, requestCode, 1,1,1,1,1,1,1);

        Toast.makeText(getBaseContext(), "Alarm Set", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void oc_setAlarm(View view){
       setAlarm();
    }
}
