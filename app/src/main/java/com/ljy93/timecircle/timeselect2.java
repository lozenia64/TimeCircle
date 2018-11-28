package com.ljy93.timecircle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class timeselect2 extends AppCompatActivity {
    RadioGroup radioWeek, radioColor;
    Intent intent;
    EditText et1, et2, et3;
    Button btnB, btnS;
    String touchedWeek, touchedColor;
    Button[] numButtons = new Button[170];
    Integer[] numBtnIDs = { R.id.btnMon00, R.id.btnTue00, R.id.btnWed00, R.id.btnThu00, R.id.btnFri00, R.id.btnSat00, R.id.btnSon00,
            R.id.btnMon01, R.id.btnTue01, R.id.btnWed01, R.id.btnThu01, R.id.btnFri01, R.id.btnSat01, R.id.btnSon01,
            R.id.btnMon02, R.id.btnTue02, R.id.btnWed02, R.id.btnThu02, R.id.btnFri02, R.id.btnSat02, R.id.btnSon02,
            R.id.btnMon03, R.id.btnTue03, R.id.btnWed03, R.id.btnThu03, R.id.btnFri03, R.id.btnSat03, R.id.btnSon03,
            R.id.btnMon04, R.id.btnTue04, R.id.btnWed04, R.id.btnThu04, R.id.btnFri04, R.id.btnSat04, R.id.btnSon04,
            R.id.btnMon05, R.id.btnTue05, R.id.btnWed05, R.id.btnThu05, R.id.btnFri05, R.id.btnSat05, R.id.btnSon05,
            R.id.btnMon06, R.id.btnTue06, R.id.btnWed06, R.id.btnThu06, R.id.btnFri06, R.id.btnSat06, R.id.btnSon06,
            R.id.btnMon07, R.id.btnTue07, R.id.btnWed07, R.id.btnThu07, R.id.btnFri07, R.id.btnSat07, R.id.btnSon07,
            R.id.btnMon08, R.id.btnTue08, R.id.btnWed08, R.id.btnThu08, R.id.btnFri08, R.id.btnSat08, R.id.btnSon08,
            R.id.btnMon09, R.id.btnTue09, R.id.btnWed09, R.id.btnThu09, R.id.btnFri09, R.id.btnSat09, R.id.btnSon09,
            R.id.btnMon10, R.id.btnTue10, R.id.btnWed10, R.id.btnThu10, R.id.btnFri10, R.id.btnSat10, R.id.btnSon10,
            R.id.btnMon11, R.id.btnTue11, R.id.btnWed11, R.id.btnThu11, R.id.btnFri11, R.id.btnSat11, R.id.btnSon11,
            R.id.btnMon12, R.id.btnTue12, R.id.btnWed12, R.id.btnThu12, R.id.btnFri12, R.id.btnSat12, R.id.btnSon12,
            R.id.btnMon13, R.id.btnTue13, R.id.btnWed13, R.id.btnThu13, R.id.btnFri13, R.id.btnSat13, R.id.btnSon13,
            R.id.btnMon14, R.id.btnTue14, R.id.btnWed14, R.id.btnThu14, R.id.btnFri14, R.id.btnSat14, R.id.btnSon14,
            R.id.btnMon15, R.id.btnTue15, R.id.btnWed15, R.id.btnThu15, R.id.btnFri15, R.id.btnSat15, R.id.btnSon15,
            R.id.btnMon16, R.id.btnTue16, R.id.btnWed16, R.id.btnThu16, R.id.btnFri16, R.id.btnSat16, R.id.btnSon16,
            R.id.btnMon17, R.id.btnTue17, R.id.btnWed17, R.id.btnThu17, R.id.btnFri17, R.id.btnSat17, R.id.btnSon17,
            R.id.btnMon18, R.id.btnTue18, R.id.btnWed18, R.id.btnThu18, R.id.btnFri18, R.id.btnSat18, R.id.btnSon18,
            R.id.btnMon19, R.id.btnTue19, R.id.btnWed19, R.id.btnThu19, R.id.btnFri19, R.id.btnSat19, R.id.btnSon19,
            R.id.btnMon20, R.id.btnTue20, R.id.btnWed20, R.id.btnThu20, R.id.btnFri20, R.id.btnSat20, R.id.btnSon20,
            R.id.btnMon21, R.id.btnTue21, R.id.btnWed21, R.id.btnThu21, R.id.btnFri21, R.id.btnSat21, R.id.btnSon21,
            R.id.btnMon22, R.id.btnTue22, R.id.btnWed22, R.id.btnThu22, R.id.btnFri22, R.id.btnSat22, R.id.btnSon22,
            R.id.btnMon23, R.id.btnTue23, R.id.btnWed23, R.id.btnThu23, R.id.btnFri23, R.id.btnSat23, R.id.btnSon23
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_timeselect);
        setTitle("반복 일정 입력");
        for (int i = 0; i < numBtnIDs.length; i++) numButtons[i] = (Button) findViewById(numBtnIDs[i]);

        radioWeek = (RadioGroup) findViewById(R.id.radioGroupWeek_2);
        radioColor = (RadioGroup) findViewById(R.id.radioGroupColor_2);
        et1 = (EditText)findViewById(R.id.selectTime_2_1);
        et2 = (EditText) findViewById(R.id.selectTime_2_2);
        et3 = (EditText) findViewById(R.id.selectName_2);

        intent = getIntent();
        String strA = intent.getStringExtra("A");
        String strB = strA.substring(0,3);
        String strC = strA.substring(3,5);

        if(strB.equals("Mon")) radioWeek.check(R.id.radio_week_2_1);
        else if(strB.equals("Tue")) radioWeek.check(R.id.radio_week_2_2);
        else if(strB.equals("Wed")) radioWeek.check(R.id.radio_week_2_3);
        else if(strB.equals("Thu")) radioWeek.check(R.id.radio_week_2_4);
        else if(strB.equals("Fri")) radioWeek.check(R.id.radio_week_2_5);
        else if(strB.equals("Sat")) radioWeek.check(R.id.radio_week_2_6);
        else if(strB.equals("Son")) radioWeek.check(R.id.radio_week_2_7);
        radioColor.check(R.id.radio_color_2_1);

        int C1 = Integer.parseInt(strC);
        int C2 = Integer.parseInt(strC)+1;
        et1.setText(C1+"");
        et2.setText(C2+"");
        et3.setHint("일정 이름을 입력해주세요.");

        btnB = (Button)findViewById(R.id.btnBack2);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("goto2", "goto2");
                startActivity(intent);
                finish();
            }
        });

        btnS = (Button)findViewById(R.id.btnSave2);
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (radioWeek.getCheckedRadioButtonId()) {
                    case R.id.radio_week_2_1:
                        touchedWeek = "월요일";
                        break;
                    case R.id.radio_week_2_2:
                        touchedWeek = "화요일";
                        break;
                    case R.id.radio_week_2_3:
                        touchedWeek = "수요일";
                        break;
                    case R.id.radio_week_2_4:
                        touchedWeek = "목요일";
                        break;
                    case R.id.radio_week_2_5:
                        touchedWeek = "금요일";
                        break;
                    case R.id.radio_week_2_6:
                        touchedWeek = "토요일";
                        break;
                    case R.id.radio_week_2_7:
                        touchedWeek = "일요일";
                        break;
                }
                switch (radioColor.getCheckedRadioButtonId()) {
                    case R.id.radio_color_2_1:
                        touchedColor = "빨간색";
                        break;
                    case R.id.radio_color_2_2:
                        touchedColor = "주황색";
                        break;
                    case R.id.radio_color_2_3:
                        touchedColor = "노란색";
                        break;
                    case R.id.radio_color_2_4:
                        touchedColor = "초록색";
                        break;
                    case R.id.radio_color_2_5:
                        touchedColor = "파란색";
                        break;
                    case R.id.radio_color_2_6:
                        touchedColor = "남색";
                        break;
                    case R.id.radio_color_2_7:
                        touchedColor = "보라색";
                        break;
                }

                String day = touchedWeek;
                String stime = et1.getText().toString();
                String etime = et2.getText().toString();
                String name = et3.getText().toString();
                String color = touchedColor;

                try {
                    int Stime = Integer.parseInt(stime);
                    int Etime = Integer.parseInt(etime);
                    if (Stime > 24 || Stime < 0 || Etime < 0 || Etime > 24)
                        Toast.makeText(getApplicationContext(), "시간은 0~24사이의 값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    else if (Stime >= Etime)
                        Toast.makeText(getApplicationContext(), "시작시간이 종료시간보다 작아야해요!", Toast.LENGTH_SHORT).show();
                    else {
                        DbHandler1 dbHandler1 = new DbHandler1(timeselect2.this);
                        DbHandler2 dbHandler = new DbHandler2(timeselect2.this);
                        //DB에 추가
                        if (Stime < 10 && Etime < 10) {
                            dbHandler.insertUserDetails(day, "0"+stime, "0"+etime, name, color);
                            dbHandler1.insertUserDetails(day, "0"+stime, "0"+etime, name);
                        }
                        else if (Stime < 10) {
                            dbHandler.insertUserDetails(day, "0"+stime, etime, name, color);
                            dbHandler1.insertUserDetails(day, "0"+stime, etime, name);
                        }
                        else if (Etime < 10) {
                            dbHandler.insertUserDetails(day, stime, "0"+etime, name, color);
                            dbHandler1.insertUserDetails(day, stime, "0"+etime, name);
                        }
                        else {
                            dbHandler.insertUserDetails(day, stime, etime, name, color);
                            dbHandler1.insertUserDetails(day, stime, etime, name);
                        }


                        Toast.makeText(getApplicationContext(), "일정을 저장했어요!", Toast.LENGTH_SHORT).show();
                        //돌아가기
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("goto2", "goto2");
                        startActivity(intent);
                        finish();
                    }
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "시간에는 숫자만 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
