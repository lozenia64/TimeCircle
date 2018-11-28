package com.ljy93.timecircle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ljy93.timecircle.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TabActivity_3 extends AppCompatActivity {
    Button sbtn3;
    EditText dateY, dateM, dateD, time1, time2, name3;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity_3);
        setTitle("추후 일정");

        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd-hh");
        String str = dayTime.format(new Date(time));
        String arr[] = str.split("-");

        sbtn3 = (Button)findViewById(R.id.saveBtn3);
        dateY = (EditText)findViewById(R.id.selectDate_3_1);
        dateM = (EditText)findViewById(R.id.selectDate_3_2);
        dateD = (EditText)findViewById(R.id.selectDate_3_3);
        time1 = (EditText)findViewById(R.id.selectTime_3_1);
        time2 = (EditText)findViewById(R.id.selectTime_3_2);
        name3 = (EditText)findViewById(R.id.selectName_3);

        dateY.setHint(arr[0]);
        dateM.setHint(arr[1]);
        dateD.setHint(arr[2]);
        time1.setHint(arr[3]);
        int t = Integer.parseInt(arr[3])+1;
        time2.setHint(t+"");
        name3.setHint("일정 이름을 입력해주세요.");

        sbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dm = dateM.getText().toString();
                String dd = dateD.getText().toString();

                if (dm.length() == 1) dm = "0"+dm;
                if (dd.length() == 1) dd = "0"+dd;

                String date = dateY.getText().toString() + dm + dd;
                String startTime = time1.getText().toString();
                String endTime = time2.getText().toString();
                String name = name3.getText().toString();

                try{
                    int intY = Integer.parseInt(dateY.getText().toString());
                    int intM = Integer.parseInt(dateM.getText().toString());
                    int intD = Integer.parseInt(dateD.getText().toString());
                    int Stime = Integer.parseInt(time1.getText().toString());
                    int Etime = Integer.parseInt(time2.getText().toString());

                    if (date == "" || startTime == "" || endTime == ""  || name == "") Toast.makeText(getApplicationContext(), "입력하지 않은 값이 존재합니다!", Toast.LENGTH_SHORT).show();
                    else if (intY < 1000) Toast.makeText(getApplicationContext(), "연도는 네자리 수의 값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    else if (intM > 12 || intM < 1) Toast.makeText(getApplicationContext(), "월은 1~12사이의 값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    else if (intD > 31 || intD < 1) Toast.makeText(getApplicationContext(), "일은 1~31사이의 값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    else if (Stime > 23 || Stime < 0 || Etime < 0 || Etime > 23) Toast.makeText(getApplicationContext(), "시간은 0~23사이의 값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    else if (Stime >= Etime) Toast.makeText(getApplicationContext(), "시작시간이 종료시간보다 작아야해요!", Toast.LENGTH_SHORT).show();
                    else {
                        DbHandler1 dbHandler1 = new DbHandler1(TabActivity_3.this);
                        DbHandler3 dbHandler = new DbHandler3(TabActivity_3.this);

                        if (Stime < 10 && Etime < 10) {
                            dbHandler.insertUserDetails(date,"0"+startTime,"0"+endTime,name);
                            dbHandler1.insertUserDetails(date,"0"+startTime,"0"+endTime,name);
                        }
                        else if (Stime < 10) {
                            dbHandler.insertUserDetails(date,"0"+startTime,endTime,name);
                            dbHandler1.insertUserDetails(date,"0"+startTime,endTime,name);
                        }
                        else if (Etime < 10) {
                            dbHandler.insertUserDetails(date,startTime,"0"+endTime,name);
                            dbHandler1.insertUserDetails(date,startTime,"0"+endTime,name);
                        }
                        else {
                            dbHandler.insertUserDetails(date,startTime,endTime,name);
                            dbHandler1.insertUserDetails(date,startTime,endTime,name);
                        }

                        Toast.makeText(getApplicationContext(), "저장", Toast.LENGTH_SHORT).show();
                        intent = new Intent(TabActivity_3.this,DetailsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "날짜, 시간에는 숫자만 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "일정삭제");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent1 = new Intent(TabActivity_3.this,DetailsActivity.class);
                startActivity(intent1);
                finish();
                return true;
        }
        return false;
    }

}