package com.example.suplaroofwindow;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String tempString;
    private SuplaWindowRoof cView;
    private TextView tView, tView2, tView3, tView4;
    private SeekBar sBar, sBar2, sBar3, sBar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cView = (SuplaWindowRoof) findViewById(R.id.cView);

        sBar = (SeekBar) findViewById(R.id.seekBar1);
        sBar2 = (SeekBar) findViewById(R.id.seekBar2);
        sBar3 = (SeekBar) findViewById(R.id.seekBar3);
        sBar4 = (SeekBar) findViewById(R.id.seekBar4);
        tView = (TextView) findViewById(R.id.textView1);
        tView2 = (TextView) findViewById(R.id.textView2);
        tView3 = (TextView) findViewById(R.id.textView3);
        tView4 = (TextView) findViewById(R.id.textView4);

        cView.setBlackColor(Color.BLACK);
        cView.setWhiteColor(Color.WHITE);
        cView.setGreenColor(Color.argb(255,5,170,55));

        tempString = sBar.getProgress() + "/" + sBar.getMax();
        tView.setText(tempString);
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tempString = sBar.getProgress() + "/" + sBar.getMax();
                tView.setText(tempString);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        tempString = sBar2.getProgress() + "/" + sBar2.getMax();
        tView2.setText(tempString);
        sBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cView.setPoint(progress);
                tempString = sBar2.getProgress() + "/" + sBar2.getMax();
                tView2.setText(tempString);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        tempString = sBar3.getProgress() + "/" + sBar3.getMax();
        tView3.setText(tempString);
        sBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cView.setPercentY(progress);
                tempString = sBar3.getProgress() + "/" + sBar3.getMax();
                tView3.setText(tempString);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        tempString = sBar4.getProgress() + "/" + sBar4.getMax();
        tView4.setText(tempString);
        sBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cView.setPercentX(progress);
                tempString = sBar4.getProgress() + "/" + sBar4.getMax();
                tView4.setText(tempString);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}
