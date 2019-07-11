package haiming.co.jp.sample_02.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import haiming.co.jp.sample_02.DialogFragment.DatePickerDialogFragment;
import haiming.co.jp.sample_02.DialogFragment.TimePickerDialogFragment;
import haiming.co.jp.sample_02.Interface.DateDecisionCallback;
import haiming.co.jp.sample_02.Interface.TimeDecisionCallback;
import haiming.co.jp.sample_02.R;

public class AlarmActivity extends AppCompatActivity {

    // 設定した時刻を表示するTextView
    private TextView settimer1_txt;
    private TextView settimer2_txt;
    private TextView settimer3_txt;

    // 設定するアラームのタイトルを入力するEditText
    private EditText settimer_title1_edit;
    private EditText settimer_title2_edit;
    private EditText settimer_title3_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void setTimerPickerShow(View v){

        final String[] year = {""};
        final String[] month = {""};
        final String[] day = {""};
        final String[] hour = {""};
        final String[] minute = {""};

        final TimeDecisionCallback timeDecisionCallback = new TimeDecisionCallback() {
            @Override
            public void DecidionTime(String hour_, String minute_) {
                // Alarmの登録
                hour[0] = hour_;
                minute[0] = minute_;
            }
        };

        DateDecisionCallback decisionCallback = new DateDecisionCallback() {
            @Override
            public void onDecisionDate(String year_, String month_, String day_) {
                year[0] = year_;
                month[0] = month_;
                day[0] = day_;

                TimePickerDialogFragment.TimeCallback_Set(timeDecisionCallback);
                TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
                timePickerDialogFragment.show(getSupportFragmentManager(),"timerpickerfragment");
            }
        };

        DatePickerDialogFragment.DateCallback_Set(decisionCallback);
        DatePickerDialogFragment datePicker = new DatePickerDialogFragment();
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }
}
