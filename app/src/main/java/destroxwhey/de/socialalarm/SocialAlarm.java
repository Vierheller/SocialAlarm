package destroxwhey.de.socialalarm;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jan-Niklas on 03.11.2014.
 */
public class SocialAlarm extends Application {
    public static DBAdapter dbAdapter;
    public static SharedPreferences sp;
    public ArrayList<AlarmHolder> myAlarms= new ArrayList<AlarmHolder>();

    @Override
    public void onTerminate() {
        super.onTerminate();
        dbAdapter.close();
    }

    @Override
    public void onCreate(){
        super.onCreate();

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        Log.d("Lololol", "DB ist geöffnet");
    }
}
