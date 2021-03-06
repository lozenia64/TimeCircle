package com.ljy93.timecircle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class delete2 extends AppCompatActivity {
    View dialogView;
    EditText editT;
    Integer dNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_delete);
        setTitle("추가한 일정보기");

        DbHandler2 db = new DbHandler2(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.user_list2);
        ListAdapter adapter = new SimpleAdapter(delete2.this, userList, R.layout.list_row,new String[]{"day","startTime","endTime","name", "id"}, new int[]{R.id.listdate, R.id.liststime, R.id.listetime, R.id.listname, R.id.listid});
        lv.setAdapter(adapter);

        Button deleteB = (Button)findViewById(R.id.btnDelete2);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DbHandler1 dbHandler1 = new DbHandler1(delete2.this);
                final DbHandler2 dbHandler = new DbHandler2(delete2.this);

                dialogView = (View) View.inflate(delete2.this, R.layout.dialog3d, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(delete2.this);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        editT = (EditText)dialogView.findViewById(R.id.dlgEdt1);
                        dNum = Integer.parseInt(editT.getText().toString());
                        final ArrayList<HashMap<String, String>> userList = dbHandler.GetUserByUserId(dNum);
                        dbHandler.DeleteUser(dNum);
                        dbHandler1.DeleteUser1(userList.get(0).get("day"), userList.get(0).get("startTime"), userList.get(0).get("endTime"));
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
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

        Button back = (Button)findViewById(R.id.btnBack2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("goto2", "goto2");
                startActivity(intent);
                finish();
            }
        });
    }
}