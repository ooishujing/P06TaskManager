package sg.edu.rp.c346.p06_taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    Button btnAdd,btnCancel;
    EditText etName,etDesc;

    int reqCode = 12345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(SecondActivity.this);
                String strName = etName.getText().toString();
                String strDesc = etDesc.getText().toString();
                Task newTask = new Task(strName, strDesc);


                db.insertTask(strName, strDesc);
                db.close();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, i);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                Intent intent = new Intent(SecondActivity.this,
                        ScheduledNotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        SecondActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

                Bundle args = new Bundle();
                args.putSerializable("task",newTask);
                intent.putExtra("data",args);

                finish();

            }


        });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                        setResult(RESULT_OK, i);
                        finish();
                    }
                });

            }
    }
