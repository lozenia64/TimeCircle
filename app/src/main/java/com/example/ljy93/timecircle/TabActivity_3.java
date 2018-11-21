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

                try{
                    int intM = Integer.parseInt(dateM.getText().toString());
                    int intD = Integer.parseInt(dateD.getText().toString());
                    int Stime = Integer.parseInt(time1.getText().toString());
                    int Etime = Integer.parseInt(time2.getText().toString());

                    if (date == "" || startTime == "" || endTime == ""  || name == "") Toast.makeText(getApplicationContext(), "입력하지 않은 값이 존재합니다!", Toast.LENGTH_SHORT).show();
                    else if (intM > 12 || intM < 1) Toast.makeText(getApplicationContext(), "월은 1~12사이의 값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    else if (intD > 31 || intD < 1) Toast.makeText(getApplicationContext(), "일은 1~31사이의 값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    else if (Stime > 23 || Stime < 0 || Etime < 0 || Etime > 23) Toast.makeText(getApplicationContext(), "시간은 0~23사이의 값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    else {
                        DbHandler3 dbHandler = new DbHandler3(TabActivity_3.this);
                        dbHandler.insertUserDetails(date,startTime,endTime,name);
                        Toast.makeText(getApplicationContext(), "저장", Toast.LENGTH_SHORT).show();
                        intent = new Intent(TabActivity_3.this,DetailsActivity.class);
                        startActivity(intent);
                    }
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "날짜, 시간에는 숫자만 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}