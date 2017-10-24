package com.czeng.tidyday.add_goal_memo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.czeng.tidyday.R;

import java.util.ArrayList;
import java.util.List;

public class add_goal_ extends AppCompatActivity {

    LinearLayout ll_repeatsection, ll_qbpsection, ll_otnotificationsection;
    Spinner sp;

    LinearLayout DailyOption, WeeklyOption, MonthlyOption, Annually;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ll_repeatsection = (LinearLayout) findViewById(R.id.repeat_section);
        ll_qbpsection = (LinearLayout) findViewById(R.id.quit_bad_priority_section);
        ll_otnotificationsection = (LinearLayout) findViewById(R.id.onetime_notification_section);

        DailyOption = (LinearLayout) findViewById(R.id.daily);
        WeeklyOption = (LinearLayout) findViewById(R.id.weekly);
        MonthlyOption = (LinearLayout) findViewById(R.id.monthly);
        Annually = (LinearLayout) findViewById(R.id.annually);


        sp = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Daily");
        list.add("Weekly");
        list.add("Monthly");
        list.add("Annually");
        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        adpater.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adpater);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    default:
                        DailyOption.setVisibility(View.GONE);
                        WeeklyOption.setVisibility(View.GONE);
                        MonthlyOption.setVisibility(View.GONE);
                        Annually.setVisibility(View.GONE);
                    case 0:
                        DailyOption.setVisibility(View.VISIBLE);
                        WeeklyOption.setVisibility(View.GONE);
                        MonthlyOption.setVisibility(View.GONE);
                        Annually.setVisibility(View.GONE);
                        break;
                    case 1:
                        DailyOption.setVisibility(View.GONE);
                        WeeklyOption.setVisibility(View.VISIBLE);
                        MonthlyOption.setVisibility(View.GONE);
                        Annually.setVisibility(View.GONE);
                        break;
                    case 2:
                        DailyOption.setVisibility(View.GONE);
                        WeeklyOption.setVisibility(View.GONE);
                        MonthlyOption.setVisibility(View.VISIBLE);
                        Annually.setVisibility(View.GONE);
                        break;
                    case 3:
                        DailyOption.setVisibility(View.GONE);
                        WeeklyOption.setVisibility(View.GONE);
                        MonthlyOption.setVisibility(View.GONE);
                        Annually.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void GoalTypeSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    ll_repeatsection.setVisibility(View.VISIBLE);
                    ll_qbpsection.setVisibility(View.GONE);
                    ll_otnotificationsection.setVisibility(View.GONE);
                break;
            case R.id.radioButton2:
                if (checked)
                    ll_repeatsection.setVisibility(View.GONE);
                    ll_qbpsection.setVisibility(View.VISIBLE);
                    ll_otnotificationsection.setVisibility(View.GONE);
                break;
            case R.id.radioButton3:
                if (checked)
                    ll_repeatsection.setVisibility(View.GONE);
                    ll_qbpsection.setVisibility(View.GONE);
                    ll_otnotificationsection.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void Close(View view) {
        finish();
    }

    public void SaveAndCloseGoal(View view) {
        finish();
    }
}
