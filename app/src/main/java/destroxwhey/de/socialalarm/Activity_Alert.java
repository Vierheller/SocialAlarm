package destroxwhey.de.socialalarm;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class Activity_Alert extends Activity {
    private TextView tv_name;
    private TextView tv_time;
    private TextView btn_cancel;
    private TextView btn_sleep;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_activity__alert);

        tv_name = (TextView) findViewById(R.id.alert_tv_name);
        tv_time = (TextView) findViewById(R.id.alert_tv_time);
        btn_cancel = (TextView) findViewById(R.id.alert_btn_cancel);
        btn_sleep = (TextView) findViewById(R.id.alert_btn_sleep);

        Intent myIntent = getIntent();

        tv_name.setText(myIntent.getStringExtra("name"));
        tv_time.setText(myIntent.getStringExtra("time"));

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }


    public void oc_btn_cancel(View view){
        mediaPlayer.stop();
        finish();
    }

    public void oc_btn_sleep(View view){

    }
}
