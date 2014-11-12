package destroxwhey.de.socialalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Jan-Niklas on 11.10.2014.
 */
public class FragmentAlarm extends Fragment{
    private ListView alarmList;
    private String[] titles;
    private String[] times;
    private int[][] days;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        SocialAlarm socialAlarm = (SocialAlarm) inflater.getContext().getApplicationContext();

        View row = inflater.inflate(R.layout.fragment_overview_alarms, container, false);
        alarmList = (ListView) row.findViewById(R.id.alarms_listView);



        Cursor cursor = socialAlarm.dbAdapter.getAllRows();

        int count = cursor.getColumnCount();
        titles = new String[50];
        times = new String[50];
        days = new int[50][7];

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                titles[id] = cursor.getString(1);
                times[id] = String.valueOf(cursor.getInt(2))+":"+String.valueOf(cursor.getInt(3));
                days[id][0]= cursor.getInt(4);
                days[id][1]= cursor.getInt(5);
                days[id][2]= cursor.getInt(6);
                days[id][3]= cursor.getInt(7);
                days[id][4]= cursor.getInt(8);
                days[id][5]= cursor.getInt(9);
                days[id][6]= cursor.getInt(10);
            }while(cursor.moveToNext());
        }
        cursor.close();


        AlarmListAdapter adapter = new AlarmListAdapter(inflater.getContext(), R.layout.single_row, R.id.single_tv_titel, titles, days, times);
        alarmList.setAdapter(adapter);

        return row;
    }


}
