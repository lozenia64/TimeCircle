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
/*import com.example.ljy93.timecircle.decorators.EventDecorator; */
import com.example.ljy93.timecircle.decorators.OneDayDecorator;
import com.example.ljy93.timecircle.decorators.SaturdayDecorator;
import com.example.ljy93.timecircle.decorators.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

public class TabActivity_3 extends Activity {
    String time;
    View dialogView;
    EditText edtName, edtTime1, edtTime2, edtTime3, edtTime4;
    RadioGroup radioWeek, radioColor;
    String touchedWeek, touchedColor;

    private final  OneDayDecorator  oneDayDecorator = new OneDayDecorator();
    MaterialCalendarView materialCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity_3);
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

        String[] result = {"2017,03,18","2017,04,18","2017,05,18","2017,06,18"};

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                final int Year = date.getYear();
                final int Month = date.getMonth() + 1;
                final int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String shot_Day = Year + "." + Month + "." + Day;

                Log.i("shot_Day test", shot_Day + "");
                materialCalendarView.clearSelection();

                //Toast.makeText(getApplicationContext(), shot_Day , Toast.LENGTH_SHORT).show();

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

                        Toast.makeText(getApplicationContext(), Integer.toString(Year) + "년 " + Integer.toString(Month) + "월 "
                                + Integer.toString(Day) + "일" + edtTime1.getText().toString() + "시"
                                + edtTime2.getText().toString() + "분부터 " + edtTime3.getText().toString() + "시"
                                + edtTime4.getText().toString() + "분까지 " + touchedColor + "선택", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "일정 이름은 " + edtName.getText().toString() + "입니다.", Toast.LENGTH_SHORT).show();
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
        String[] Time_Result;
        ApiSimulator(String[] Time_Result){
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
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0 ; i < Time_Result.length ; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
            }
            return dates;
        }
    }
}