package com.example.ljy93.timecircle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity_2 extends Activity {
    String touchedBtn, subBtn;
    // 버튼의 개수 7 * 24 = 168
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
    int i; // 증가값 용도
    View dialogView, dialogTime;
    EditText edtName, edtColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity_2);
        for (i = 0; i < numBtnIDs.length; i++) numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        for (i = 0; i < numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    touchedBtn = numButtons[index].toString();
                    subBtn = touchedBtn.substring(touchedBtn.length() - 6, touchedBtn.length() - 1);
                    //Toast.makeText(getApplicationContext(), subBtn + "을 선택했어요.", Toast.LENGTH_SHORT).show();

                    dialogView = (View) View.inflate(TabActivity_2.this, R.layout.activity2_timeselect, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(TabActivity_2.this);
                    dlg.setTitle("반복 일정 입력");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            edtName = (EditText) dialogView.findViewById(R.id.selectName);
                            edtColor = (EditText) dialogView.findViewById(R.id.selectColor);
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
    }
}