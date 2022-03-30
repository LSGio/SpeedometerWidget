package com.cardiomood.andoid.demo;

import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.cardiomood.android.controls.gauge.SpeedometerGauge;


public class MainActivity extends ActionBarActivity {

    private SpeedometerGauge mSpeedoMeter;
    private BatteryManager mBatteryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpeedoMeter = (SpeedometerGauge) findViewById(R.id.speedometer);
        mBatteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);

        mSpeedoMeter.setMaxSpeed(100);
        mSpeedoMeter.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });
        mSpeedoMeter.setMajorTickStep(20);
        mSpeedoMeter.setMinorTicks(8);
        mSpeedoMeter.addColoredRange(0, 15, Color.RED);
        mSpeedoMeter.addColoredRange(15, 80, Color.YELLOW);
        mSpeedoMeter.addColoredRange(80, mSpeedoMeter.getMaxSpeed(), Color.GREEN);
        mSpeedoMeter.setSpeed(mBatteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY), 1000, 0);
    }

}
