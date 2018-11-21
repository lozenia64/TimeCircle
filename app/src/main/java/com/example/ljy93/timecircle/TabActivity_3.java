package com.example.ljy93.timecircle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ljy93.timecircle.R;

public class TabActivity_3 extends Activity {
    Button sbtn3;
    EditText dateY, dateM, dateD, time1, time2, name3;
    RadioGroup radioColor2;
    String touchedColor3;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity_3);

        sbtn3 = (Button)findViewById(R.id.saveBtn3);
        dateY = (EditText)findViewById(R.id.selectDate_3_1);
        dateM = (EditText)findViewById(R.id.selectDate_3_2);
        dateD = (EditText)findViewById(R.id.selectDate_3_3);
        time1 = (EditText)findViewById(R.id.selectTime_3_1);
        time2 = (EditText)findViewById(R.id.selectTime_3_2);
        name3 = (EditText)findViewById(R.id.selectName_3);

        sbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateY.getText().toString() + dateM.getText().toString() + dateD.getText().toString();
                String startTime = time1.getText().toString();
                String endTime = time2.getText().toString();
                String name = name3.getText().toString();
                String color = touchedColor3;

                if (date == "" || startTime == "" || endTime == ""  || name == "" || color == "") {
                    Toast.makeText(getApplicationContext(), "입력하지 않은 값이 존재합니다!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DbHandler3 dbHandler = new DbHandler3(TabActivity_3.this);
                    dbHandler.insertUserDetails(date,startTime,endTime,name,color);
                    Toast.makeText(getApplicationContext(), "저장", Toast.LENGTH_SHORT).show();
                }
                intent = new Intent(TabActivity_3.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}