package com.ljy93.timecircle;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
    TabHost TabHostWindow;
    Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second Tab");
        TabSpec TabMenu3 = TabHostWindow.newTabSpec("Third Tab");

        TabMenu1.setIndicator("TODAY");
        TabMenu1.setContent(new Intent(this,TabActivity_1.class));
        TabMenu2.setIndicator("WEEK");
        TabMenu2.setContent(new Intent(this,TabActivity_2.class));
        TabMenu3.setIndicator("FUTURE");
        TabMenu3.setContent(new Intent(this,TabActivity_3.class));

        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);
        TabHostWindow.addTab(TabMenu3);

        Intent intent = getIntent();
        String str1 = intent.getStringExtra("goto1");
        String str2 = intent.getStringExtra("goto2");
        String str3 = intent.getStringExtra("goto3");
        if(str1 != null) TabHostWindow.setCurrentTab(0);
        if(str2 != null) TabHostWindow.setCurrentTab(1);
        if(str3 != null) TabHostWindow.setCurrentTab(2);
    }
}