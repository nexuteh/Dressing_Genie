package com.dressingGenie;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Result extends TabActivity {
    FloatingActionButton fab;
    TabHost TabHostWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        TabSpec TabMenu1 = TabHostWindow.newTabSpec("Analysis");
        TabSpec TabMenu2 = TabHostWindow.newTabSpec("Suggestion");

        TabMenu1.setIndicator("Analysis");
        TabMenu1.setContent(new Intent(this,Analysis_m.class));

        TabMenu2.setIndicator("Suggestion");
        TabMenu2.setContent(new Intent(this,Suggestion.class));

        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);

        fab=(FloatingActionButton)findViewById(R.id.home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Result.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
