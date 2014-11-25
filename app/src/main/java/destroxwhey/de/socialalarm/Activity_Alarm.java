package destroxwhey.de.socialalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
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
import java.util.ArrayList;
import java.util.Calendar;


public class Activity_Alarm extends Activity {
    private int alarmImage = R.drawable.alarm;
    private int intent = 0;
    //Widgets
    private ListView alarm_listView;
    private long id;
    private EditText et_name;
    private EditText et_hour;
    private EditText et_minute;
    private int requestcode;
    private TextView tv_M;
    private TextView tv_TU;
    private TextView tv_W;
    private TextView tv_TH;
    private TextView tv_F;
    private TextView tv_SA;
    private TextView tv_SU;
    private int[] dayState = {0,0,0,0,0,0,0};

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

        final ArrayList<TextView> mydays= new ArrayList<TextView>(7);
        mydays.add(0, (TextView) findViewById(R.id.alarm_tv_M));
        mydays.add(1,(TextView) findViewById(R.id.alarm_tv_TU));
        mydays.add(2,(TextView) findViewById(R.id.alarm_tv_W));
        mydays.add(3,(TextView) findViewById(R.id.alarm_tv_TH));
        mydays.add(4,(TextView) findViewById(R.id.alarm_tv_F));
        mydays.add(5,(TextView) findViewById(R.id.alarm_tv_SA));
        mydays.add(6,(TextView) findViewById(R.id.alarm_tv_SU));


        for(int i=0; i<mydays.size();i++){
            final int j=i;
            mydays.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dayState[j]==0){
                        dayState[j]=1;
                        mydays.get(j).setTextColor(Color.GREEN);
                    }else{
                        dayState[j]=0;
                        mydays.get(j).setTextColor(Color.BLACK);
                    }
                }
            });
        }

        Intent myIntent = getIntent();
        intent = myIntent.getIntExtra("intent", 0);
        if(intent == 1){
            int position = myIntent.getIntExtra("position", 0);
            et_name.setText(socialAlarm.myAlarms.get(position).getName());
            et_hour.setText(String.valueOf(socialAlarm.myAlarms.get(position).getHour()));
            et_minute.setText(String.valueOf(socialAlarm.myAlarms.get(position).getMinute()));
            requestcode = socialAlarm.myAlarms.get(position).getRequestcode();
            id = socialAlarm.myAlarms.get(position).getId();

            for(int i=0;i<dayState.length;i++){
                dayState[i]=socialAlarm.myAlarms.get(position).getDays()[i];
                if(dayState[i]==1){
                    mydays.get(i).setTextColor(Color.GREEN);
                }
            }
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
        //Set the requestCode as a new one -> set as next. We need max 7 req's and so we order 10, 20, 30 and so on
        int reqCode = socialAlarm.myAlarms.size()*10;
        if(intent == 1){
            //or set the requestCode as the same like the last alarm and override it
            reqCode = requestcode;
        }

        //getting all Information from Activity
        int hour = Integer.parseInt(et_hour.getText().toString());
        int minute = Integer.parseInt(et_minute.getText().toString());

        //Put all information into intent
        Intent myIntent = new Intent(this, NotifyService.class);
        String name = et_name.getText().toString();
        myIntent.putExtra("name", name);

        String wroteTime;
        if(hour <10){wroteTime = "0"+String.valueOf(hour);}
        else
        {wroteTime = String.valueOf(hour);}
        wroteTime += ":";
        if(minute <10){wroteTime += "0"+String.valueOf(minute);}
        else
        {wroteTime +=String.valueOf(minute);}
        myIntent.putExtra("time", wroteTime);

        //get the SystemServices
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        //fire all Alarms
        for(int i = 0;i<dayState.length;i++){
            if(dayState[i]==1){
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, reqCode+i, myIntent, PendingIntent.FLAG_ONE_SHOT);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.SECOND, 00);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                if(i==6){
                    calendar.set(Calendar.DAY_OF_WEEK, 1);
                }else{
                    calendar.set(Calendar.DAY_OF_WEEK, i+2);
                }
                //Don't fire Alarm if its already over
                if(calendar.getTimeInMillis()>Calendar.getInstance().getTimeInMillis()){
                    alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);
                }
            }
        }

        if(intent == 1){
            socialAlarm.dbAdapter.updateRow(id, name, hour, minute, reqCode, dayState[0],dayState[1],dayState[2],dayState[3],dayState[4],dayState[5],dayState[6]);
        }else{
            socialAlarm.dbAdapter.insertRow(name, hour, minute, reqCode, dayState[0],dayState[1],dayState[2],dayState[3],dayState[4],dayState[5],dayState[6]);
        }

        Toast.makeText(getBaseContext(), "Alarm Set", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void oc_setAlarm(View view){
       setAlarm();
    }
}