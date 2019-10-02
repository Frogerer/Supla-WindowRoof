package com.example.suplaroofwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SuplaWindowRoof cView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cView = (SuplaWindowRoof) findViewById(R.id.cView);
    }
}
