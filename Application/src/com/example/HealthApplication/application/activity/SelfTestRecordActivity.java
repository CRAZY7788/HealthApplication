package com.example.HealthApplication.application.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.HealthApplication.R;
import com.example.HealthApplication.application.interfaces.presenterInterface.ISelfTestRecordPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.ISelfTestRecordView;
import com.example.HealthApplication.application.presenter.SelfTestRecordPresenter;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBaseActivity;

import java.util.Calendar;

/**
 *
 */

public class SelfTestRecordActivity extends MvpBaseActivity<ISelfTestRecordView,ISelfTestRecordPresenter> implements ISelfTestRecordView {
    LinearLayout mAddFoamingRecordCard;
    LinearLayout mAddProfessionalRecordCard;
    LinearLayout mResultViewCard;
    Button mAddFoamingButton;
    Button mAddProfessionalButton;
    Button mResultButton;
    Button mUpdateButton;
    Button mCloseButton;

    NumberPicker mClockPicker;
    NumberPicker mFoamingRatePicker;

    TextView mDatePicker;

    SelfTestRecordPresenter mPresenter;
    private int year;
    private int month;
    private int day;

    int mDialogId = 999;


    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.ll_self_record_activity_layout);
        mPresenter = new SelfTestRecordPresenter();
        initResource();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public ISelfTestRecordPresenter getPresenter() {
        return mPresenter;
    }

    private void initResource(){
        mAddFoamingRecordCard = (LinearLayout) findViewById(R.id.add_foaming_record_view);
        mAddProfessionalRecordCard = (LinearLayout) findViewById(R.id.add_professional_record_view);
        mResultViewCard = (LinearLayout) findViewById(R.id.show_result_card);
        mAddFoamingButton = (Button) findViewById(R.id.btn_add_self_foaming_record_view);
        mAddProfessionalButton = (Button) findViewById(R.id.btn_add_professinal_record_view);
        mResultButton = (Button) findViewById(R.id.btn_show_record_view);
        mUpdateButton = (Button) findViewById(R.id.btn_update) ;
        mCloseButton = (Button) findViewById(R.id.btn_close);
        mAddFoamingButton.setEnabled(false);
        mAddProfessionalButton.setEnabled(true);
        mResultButton.setEnabled(true);
//        getPresenter().initAddFoamingView();
        mClockPicker = (NumberPicker)findViewById(R.id.np_clock_picker);
        mFoamingRatePicker = (NumberPicker) findViewById(R.id.np_foaming_rate_picker);
        mDatePicker = (TextView) findViewById(R.id.tv_date_view);

        mAddFoamingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddFoamingRecordCard.setVisibility(View.VISIBLE);
                mAddProfessionalRecordCard.setVisibility(View.GONE);
                mResultViewCard.setVisibility(View.GONE);

                mAddFoamingButton.setEnabled(false);
                mAddProfessionalButton.setEnabled(true);
                mResultButton.setEnabled(true);

                getPresenter().initAddFoamingView();
            }
        });

        mAddProfessionalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddFoamingRecordCard.setVisibility(View.GONE);
                mAddProfessionalRecordCard.setVisibility(View.VISIBLE);
                mResultViewCard.setVisibility(View.GONE);

                mAddFoamingButton.setEnabled(true);
                mAddProfessionalButton.setEnabled(false);
                mResultButton.setEnabled(true);

                getPresenter().initAddProfessionalView();
            }
        });

        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddFoamingRecordCard.setVisibility(View.GONE);
                mAddProfessionalRecordCard.setVisibility(View.GONE);
                mResultViewCard.setVisibility(View.VISIBLE);

                mAddFoamingButton.setEnabled(true);
                mAddProfessionalButton.setEnabled(true);
                mResultButton.setEnabled(false);

                getPresenter().initShowView();
            }
        });

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().saveData(mDatePicker.getText().toString(),2);
            }
        });

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });

        setCurrentDateOnView();


    }

    // display current date
    public void setCurrentDateOnView() {


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into datepicker
        mDatePicker.setText(year+ "-" + month + "-" +day);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 999:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into datepicker also
            mDatePicker.setText(year+ "-" + month + "-" +day);

        }
    };


    @Override
    public void initViewForAddFoamingCard() {

    }

    @Override
    public void initViewForAddProfessionalCard() {

    }

    @Override
    public void initViewForResultCard() {

    }

    @Override
    public void updateDataBase() {

    }
}
