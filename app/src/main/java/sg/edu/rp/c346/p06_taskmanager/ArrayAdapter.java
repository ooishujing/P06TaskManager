package sg.edu.rp.c346.p06_taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public abstract class ArrayAdapter extends ArrayAdapter<Task> {

    TextView tvID, tvName, tvDesc;
    Context context;
    ArrayList<Task> tasks;

    public ArrayAdapter(Context context, int resource, ArrayList<Task> objects){
        super(context, resource, objects);
        tasks = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        tvID = (TextView)rowView.findViewById(R.id.tvID);
        tvName = (TextView)rowView.findViewById(R.id.tvName);
        tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);

        Task task = this.tasks.get(position);

        tvID.setText(task.get_id() + "");
        tvName.setText(task.getTitle());
        tvDesc.setText(task.getDescription() + "");

        return rowView;

    }

}
