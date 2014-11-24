package destroxwhey.de.socialalarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Jan-Niklas on 28.10.2014.
 */
public class NotifyService extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Notify", "Started Notify");
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager nMN = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context.getApplicationContext(), OverviewActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent1, 0);

        Notification mNotify = new Notification.Builder(context)
                .setContentTitle("Alarm!")
                .setContentText("Wake UP" /*intent.getStringExtra("AlarmTime")*/)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .setSound(sound)
                .build();
        nMN.notify(1, mNotify);
    }
}
