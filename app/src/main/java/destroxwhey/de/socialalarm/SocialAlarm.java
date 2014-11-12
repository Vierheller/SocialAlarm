package destroxwhey.de.socialalarm;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

/**
 * Created by Jan-Niklas on 03.11.2014.
 */
public class SocialAlarm extends Application {
    public static DBAdapter dbAdapter;
    public static SharedPreferences sp;

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

        dbAdapter.insertRow("Schule", 10, 30, 1,1,1,1,1,0,0);
        dbAdapter.insertRow("Hausaufgaben", 20,30,1,1,1,1,1,0,0);
        dbAdapter.insertRow("Whatsoever", 9,10, 1,0,1,0,1,0,1);
    }
}
