package com.czeng.tidyday.add_goal_memo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.czeng.tidyday.R;
import com.czeng.tidyday.GoalRecycler.GoalAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;


public class add_goal_ extends AppCompatActivity {

    GoalAdapter adapter;

    LinearLayout ll_repeatsection, ll_qbpsection, ll_otnotificationsection;
    TextView tv_monthly_date, tv_monthly_time;
    TextView tv_onetime_date, tv_onetime_time;
    TextView tv_weekly_time;
    RadioButton tv_monthly_sameday, tv_monthly_xth_xday;
    TextView tv_annually_date;
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
    SimpleDateFormat stf = new SimpleDateFormat("KK:mm aa");
    SimpleDateFormat sstf = new SimpleDateFormat("KK:"+"00"+" aa");
    SimpleDateFormat dayofweekinmonthf = new SimpleDateFormat("F");;
    SimpleDateFormat dayf = new SimpleDateFormat("EEEE");
    SimpleDateFormat ann_datef = new SimpleDateFormat("MMM dd, yyyy");

    String m_o_a = "mon";
    String m_o_w = "mon";

    Spinner sp;

    LinearLayout DailyOption, WeeklyOption, MonthlyOption, Annually;

    Calendar cal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateDateTextView(m_o_a);
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);

            updateTimeTextView(m_o_w);
        }
    };


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

        tv_monthly_date = (TextView) findViewById(R.id.monthly_date);
        tv_monthly_time = (TextView) findViewById(R.id.monthly_time);
        tv_onetime_date = (TextView) findViewById(R.id.onetime_date);
        tv_onetime_time = (TextView) findViewById(R.id.onetime_time);
        tv_weekly_time = (TextView) findViewById(R.id.weekly_time);
        tv_monthly_sameday = (RadioButton) findViewById(R.id.rb_monthly_sameday);
        tv_monthly_xth_xday = (RadioButton) findViewById(R.id.rb_monthly_xth_xday);
        tv_annually_date = (TextView) findViewById(R.id.annually_date);

        DailyOption = (LinearLayout) findViewById(R.id.daily);
        WeeklyOption = (LinearLayout) findViewById(R.id.weekly);
        MonthlyOption = (LinearLayout) findViewById(R.id.monthly);
        Annually = (LinearLayout) findViewById(R.id.annually);

        long date = System.currentTimeMillis();
        String dateString = sdf.format(date);
        String stimeString = sstf.format(date);
        tv_monthly_date.setText(dateString);
        tv_onetime_date.setText(dateString);
        tv_monthly_time.setText(stimeString);
        tv_onetime_time.setText(stimeString);
        tv_weekly_time.setText(stimeString);
        String mxx = "On every "+getweekrank(dayofweekinmonthf.format(date))+" "+dayf.format(date);
        tv_monthly_xth_xday.setText(mxx);
        tv_annually_date.setText(ann_datef.format(date));


        tv_monthly_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                m_o_a = "mon";
            }
        });

        tv_onetime_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                m_o_a = "one";
            }
        });

        tv_monthly_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
                m_o_w = "mon";
            }
        });

        tv_onetime_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
                m_o_w = "one";

            }
        });

        tv_weekly_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
                m_o_w = "wee";

            }
        });

        tv_annually_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                m_o_a = "ann";

            }
        });



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

    public String getweekrank(String F){
        switch (F){
            case "1":
                return "first";
            case "2":
                return "second";
            case "3":
                return "third";
            case "4":
                return "fourth";
            case "5":
                return "fifth";
            case "6":
                return "sixth";
            default:
                return null;
        }

    }

    private void updateDateTextView(String m_o_a) {
        switch (m_o_a){
            case "mon":
                tv_monthly_date.setText(sdf.format(cal.getTime().getTime()));
                updatexx();
                break;
            case "one":
                tv_onetime_date.setText(sdf.format(cal.getTime().getTime()));
                break;
            case "ann":
                tv_annually_date.setText(ann_datef.format(cal.getTime().getTime()));
            default:
                tv_monthly_date.setText(sdf.format(cal.getTime().getTime()));
                tv_onetime_date.setText(sdf.format(cal.getTime().getTime()));
                tv_annually_date.setText(ann_datef.format(cal.getTime().getTime()));
        }
    }

    private void updateTimeTextView(String m_o_w) {
        switch (m_o_w){
            case "mon":
                tv_monthly_time.setText(stf.format(cal.getTime().getTime()));
                break;
            case "one":
                tv_onetime_time.setText(stf.format(cal.getTime().getTime()));
                break;
            case "wee":
                tv_weekly_time.setText(stf.format(cal.getTime().getTime()));
                break;
            default:
                tv_monthly_time.setText(stf.format(cal.getTime().getTime()));
                tv_onetime_time.setText(stf.format(cal.getTime().getTime()));
                tv_weekly_time.setText(stf.format(cal.getTime().getTime()));
        }
    }

    private void updatexx(){
        tv_monthly_xth_xday.setText("On every "+getweekrank(dayofweekinmonthf.format(cal.getTime().getTime()))+" "+dayf.format(cal.getTime().getTime()));
    }

    private void showDatePickerDialog() {
        new DatePickerDialog(this, d, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePickerDialog(){
        new TimePickerDialog(this, t, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();
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
        adapter.addGoalCard("Test", "This is a test");
        finish();
    }
}
