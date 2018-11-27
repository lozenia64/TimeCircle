package com.ljy93.timecircle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;

public class TabActivity_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity_1);
        setTitle("오늘의 일정");

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int date = cal.get(Calendar.DATE);
        int dayOfweek = cal.get(Calendar.DAY_OF_WEEK);
        String w = "";
        switch (dayOfweek){
            case 1:
                w = "일요일";
                break;
            case 2:
                w = "월요일";
                break;
            case 3:
                w = "화요일";
                break;
            case 4:
                w = "수요일";
                break;
            case 5:
                w = "목요일";
                break;
            case 6:
                w = "금요일";
                break;
            case 7:
                w = "토요일";
                break;
        }

        String today = year+""+month+""+date;

        DbHandler1 db = new DbHandler1(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ArrayList<HashMap<String, String>> newList = new ArrayList<>();
        for(int i=0; i<userList.size(); i++){
            if(userList.get(i).get("day").equals(today) || userList.get(i).get("day").equals(w) ) {
                newList.add(userList.get(i));
            }
        }
        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(TabActivity_1.this, newList, R.layout.list_row1,new String[]{"day","startTime","endTime","name"}, new int[]{R.id.listdate, R.id.liststime, R.id.listetime, R.id.listname});
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "목록갱신");
        menu.add(0, 2, 0, "일정삭제");
        menu.add(0, 3, 0, "오늘날씨");
        menu.add(0, 4, 0, "음악재생");
        menu.add(0, 5, 0, "음악종료");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("goto1", "goto1");
                startActivity(intent);
                finish();
                return true;
            case 2:
                Intent myIntent5 = new Intent(TabActivity_1.this,delete1.class);
                startActivity(myIntent5);
                finish();
                return true;
            case 3:
                Intent myIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.weather.naver.com/m/nation.nhn"));
                startActivity(myIntent2);
                return true;
            case 4:
                Intent myIntent3 = new Intent(getApplicationContext(), Myservice.class);
                startService(myIntent3);
                return true;
            case 5:
                Intent myIntent4 = new Intent(getApplicationContext(), Myservice.class);
                stopService(myIntent4);
                return true;
        }
        return false;
    }

}