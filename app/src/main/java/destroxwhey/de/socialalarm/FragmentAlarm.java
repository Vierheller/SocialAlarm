package destroxwhey.de.socialalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jan-Niklas on 11.10.2014.
 */
public class FragmentAlarm extends Fragment{
    private ListView alarmList;
    private String[] titles;
    private ArrayList<AlarmHolder> myAlarms;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        SocialAlarm socialAlarm = (SocialAlarm) inflater.getContext().getApplicationContext();
        myAlarms = socialAlarm.myAlarms;
        myAlarms.clear();

        View row = inflater.inflate(R.layout.fragment_overview_alarms, container, false);
        alarmList = (ListView) row.findViewById(R.id.alarms_listView);

        alarmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Position1", String.valueOf(position));
                Intent intent = new Intent(getActivity(), Activity_Alarm.class);
                intent.putExtra("intent", 1);//tells Activity, that it should load information.
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        Cursor cursor = socialAlarm.dbAdapter.getAllRows();
        if(cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                int hour = cursor.getInt(2);
                int minute = cursor.getInt(3);
                int requestcode = cursor.getInt(4);
                int mo = cursor.getInt(5);
                int tu = cursor.getInt(6);
                int we = cursor.getInt(7);
                int th = cursor.getInt(8);
                int fr = cursor.getInt(9);
                int sa = cursor.getInt(10);
                int su = cursor.getInt(11);
                myAlarms.add(new AlarmHolder(title, hour, minute, requestcode, mo, tu, we, th, fr, sa, su));
            }while(cursor.moveToNext());
        }
        cursor.close();

        AlarmListAdapter adapter = new AlarmListAdapter(inflater.getContext(), R.layout.single_row, R.id.single_tv_titel, getTitles(), myAlarms);
        alarmList.setAdapter(adapter);

        return row;
    }

    private String[] getTitles(){
        String[] titles = new String[myAlarms.size()];
        for(int i=0; i<myAlarms.size();i++){
            titles[i]=myAlarms.get(i).getName();
        }
        return titles;
    }


}
