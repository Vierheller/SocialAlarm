package destroxwhey.de.socialalarm;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jan-Niklas on 12.11.2014.
 */
public class AlarmListAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] titles;
    private String[] times;
    private int[][] days;

    public AlarmListAdapter(Context context, int resource, int textViewResourceId, String[] titles, int[][] days, String[] times) {
        super(context, resource, textViewResourceId, titles);
        this.context = context;
        this.days = days;
        this.titles = titles;
        this.times = times;

        Log.d("ListViewAdapter", "Adapter instanciated!");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row, parent, false);
        Log.d("ListViewAdapter", "get View");

        ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
        TextView myTitle = (TextView) row.findViewById(R.id.single_tv_titel);
        TextView myTime = (TextView) row.findViewById(R.id.single_tv_time);

        ArrayList<TextView> mydays= new ArrayList<TextView>(7);
        mydays.add(0, (TextView) row.findViewById(R.id.single_tv_m));
        mydays.add(1,(TextView) row.findViewById(R.id.single_tv_t));
        mydays.add(2,(TextView) row.findViewById(R.id.single_tv_w));
        mydays.add(3,(TextView) row.findViewById(R.id.single_tv_th));
        mydays.add(4,(TextView) row.findViewById(R.id.single_tv_f));
        mydays.add(5,(TextView) row.findViewById(R.id.single_tv_sa));
        mydays.add(6,(TextView) row.findViewById(R.id.single_tv_su));

        for(int i=0;i<7;i++){
            if(days[position][i]==0){
                mydays.get(i).setTextColor(Color.BLACK);
            }else{
                mydays.get(i).setTextColor(Color.GREEN);
            }
        }
        myImage.setImageResource(R.drawable.alarm);
        myTitle.setText(titles[position]);
        myTime.setText(times[position]);



        return row;
    }
}
