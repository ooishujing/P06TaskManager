package sg.edu.rp.c346.p06_taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAddActivity;
    ListView lv;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddActivity = findViewById(R.id.btnAddActivity);
        lv = findViewById(R.id.lv);
        DBHelper db = new DBHelper(MainActivity.this);

        ArrayList<Task> tasks = db.getAllTasks("");
        db.close();
        aa = new ArrayAdapter(getApplicationContext(), R.layout.row, tasks);
        lv.setAdapter((ListAdapter) aa);
        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                startActivityForResult(i, 9);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper db = new DBHelper(MainActivity.this);

            ArrayList<Task> tasks = db.getAllTasks("");
            db.close();
            aa = new ArrayAdapter(getApplicationContext(), R.layout.row,tasks);
            lv.setAdapter((ListAdapter) aa);
        }
    }
}
