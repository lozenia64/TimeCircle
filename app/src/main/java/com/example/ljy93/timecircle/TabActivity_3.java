package com.example.ljy93.timecircle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljy93.timecircle.decorators.SaturdayDecorator;
import com.example.ljy93.timecircle.decorators.EventDecorator;
import com.example.ljy93.timecircle.decorators.OneDayDecorator;
import com.example.ljy93.timecircle.decorators.SaturdayDecorator;
import com.example.ljy93.timecircle.decorators.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;

public class TabActivity_3 extends Activity {
    String time;
    View dialogView;
    EditText edtName, edtTime1, edtTime2, edtTime3, edtTime4;
    RadioGroup radioColor;
    String touchedColor;

    private final  OneDayDecorator  oneDayDecorator = new OneDayDecorator();
    MaterialCalendarView materialCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity_3);

        DbHandler3 db = new DbHandler3(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        //DB에 저장되어있던 데이터를 result에 저장.
        List<String> result = new ArrayList<>();
        for(int i = 0; i<userList.size(); i++) {
            Collection<String> s = userList.get(i).values();
            Iterator<String> it = s.iterator();
            String subs = it.next();
            result.add(subs);
        }
        //ApiSimulator에서 점을 찍어줄 때 마지막 날짜가 무시됨.. 따라서 선택되지 않을 날짜 1개를 마지막에 추가
        result.add("20170101");
        //ApiSimulator에서 점찍기
        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull final MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                final int Year = date.getYear();
                final int Month = date.getMonth() + 1;
                final int Day = date.getDay();

                String shot_Day = Year + "." + Month + "." + Day;

                materialCalendarView.clearSelection();

                dialogView = (View) View.inflate(TabActivity_3.this, R.layout.activity3_timeselect, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(TabActivity_3.this);
                dlg.setTitle(Year+ "년 " + Month + "월 " + Day +"일 일정 추가");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        edtTime1 = (EditText) dialogView.findViewById(R.id.selectTime_3_1);
                        edtTime2 = (EditText) dialogView.findViewById(R.id.selectTime_3_2);
                        edtTime3 = (EditText) dialogView.findViewById(R.id.selectTime_3_3);
                        edtTime4 = (EditText) dialogView.findViewById(R.id.selectTime_3_4);
                        edtName = (EditText) dialogView.findViewById(R.id.selectName_3);
                        radioColor = (RadioGroup) dialogView.findViewById(R.id.radioGroupColor_3);

                        int T1 = Integer.parseInt(edtTime1.getText().toString());
                        int T2 = Integer.parseInt(edtTime2.getText().toString());
                        int T3 = Integer.parseInt(edtTime3.getText().toString());
                        int T4 = Integer.parseInt(edtTime4.getText().toString());

                        //입력오류일 경우 예외처리
                        if (T1 < 0 || T3 < 0 || T1 > 23 || T3 > 23) Toast.makeText(getApplicationContext(), "시간은 0~23사이로 입력해주세요!",Toast.LENGTH_SHORT).show();
                        else if (T2 < 0 || T4 < 0 || T2 > 60 || T4 > 60) Toast.makeText(getApplicationContext(), "분은 0~59사이로 입력해주세요!",Toast.LENGTH_SHORT).show();
                        else{
                            switch (radioColor.getCheckedRadioButtonId()) {
                                case R.id.radio_color_3_1:
                                    touchedColor = "빨간색";
                                    break;
                                case R.id.radio_color_3_2:
                                    touchedColor = "주황색";
                                    break;
                                case R.id.radio_color_3_3:
                                    touchedColor = "노란색";
                                    break;
                                case R.id.radio_color_3_4:
                                    touchedColor = "초록색";
                                    break;
                                case R.id.radio_color_3_5:
                                    touchedColor = "파란색";
                                    break;
                                case R.id.radio_color_3_6:
                                    touchedColor = "남색";
                                    break;
                                case R.id.radio_color_3_7:
                                    touchedColor = "보라색";
                                    break;

                            }

                            String date = Integer.toString(Year)+""+Integer.toString(Month)+""+Integer.toString(Day);
                            String stime = edtTime1.getText().toString()+""+edtTime2.getText().toString();
                            String etime = edtTime3.getText().toString()+""+edtTime4.getText().toString();
                            String name = edtName.getText().toString();
                            String color = touchedColor;
                            DbHandler3 dbHandler = new DbHandler3(TabActivity_3.this);
                            dbHandler.insertUserDetails(date,stime,etime,name,color);

                            Toast.makeText(getApplicationContext(), "일정을 저장했어요!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소했어요.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });
    }


    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
        List<String> Time_Result;
        ApiSimulator(List<String> Time_Result){
            this.Time_Result = Time_Result;
        }
        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            for(int i = 0 ; i < Time_Result.size(); i ++){
                CalendarDay day = CalendarDay.from(calendar);
                int year = Integer.parseInt(Time_Result.get(i).substring(0,4));
                int month = Integer.parseInt(Time_Result.get(i).substring(4,6));
                int dayy = Integer.parseInt(Time_Result.get(i).substring(6,8));
                dates.add(day);
                calendar.set(year,month-1,dayy);
            }
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);
            if (isFinishing()) return;
            materialCalendarView.addDecorator(new EventDecorator(Color.RED, calendarDays, TabActivity_3.this));
        }
    }
}
