package com.example.HealthApplication.application.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.HealthApplication.R;

import java.util.zip.Inflater;

/**
 *
 */

public abstract class HealthBaseActivity extends Activity{
    private TextView mHeaderText;
    private Button mCancelButton;
    private Button mConfirmButton;
    private LinearLayout mActivityLayout;

    private void init(){
        mActivityLayout = (LinearLayout) findViewById(R.id.ll_main_content);
        mHeaderText = (TextView) findViewById(R.id.tv_activity_header);
        mCancelButton = (Button) findViewById(R.id.bt_cancel);
        mConfirmButton = (Button) findViewById(R.id.bt_confirm);
    }

    public void setContent(){

    }

    public void setHeaderText(String description){
        mHeaderText.setText(description);
    }


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void onCreateActivity(Bundle savedInstanceState) {
        // Do nothing
    }

    protected void onPostCreateActivity(Bundle savedInstanceState) {
        // Do nothing
    }

    protected void onStartActivity() {
        // Do nothing
    }

    protected void onResumeActivity() {
        // Do nothing
    }

    protected void onPauseActivity() {
        // Do nothing
    }

    protected void onStopActivity() {
        // Do nothing
    }

}
