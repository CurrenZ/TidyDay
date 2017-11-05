package com.czeng.tidyday.add_goal_memo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.czeng.tidyday.GoalDataBase.GoalContract;
import com.czeng.tidyday.GoalDataBase.GoalDataSource;
import com.czeng.tidyday.GoalDataBase.GoalDatabaseHelper;
import com.czeng.tidyday.MainActivity;
import com.czeng.tidyday.R;
import com.czeng.tidyday.GoalRecycler.GoalAdapter;
import com.czeng.tidyday.GoalDataObject.GoalCard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;


public class add_goal_ extends AppCompatActivity {

    GoalDataSource dataSource = new GoalDataSource(this);

    // Strings that will be stored in db
    String GoalTitle = "", GoalType = "GG", GoalRepeat = "", GoalDToggle = "", GoalWToggle = "", GoalMMode = "", GoalCalenderCache = "";
    int GoalID = 0;

    LinearLayout ll_repeatsection, ll_qbpsection, ll_otnotificationsection;
    TextView tv_monthly_date, tv_monthly_time;
    TextView tv_onetime_date, tv_onetime_time;
    TextView tv_weekly_time;
    RadioButton tv_monthly_xth_xday;
    TextView tv_annually_date;
    EditText et_title;

    ToggleButton tb_mon, tb_tue, tb_wed, tb_thu, tb_fri, tb_sat, tb_sun;
    ToggleButton tb_mo, tb_no, tb_ni;

    SimpleDateFormat sqldate = new SimpleDateFormat("MMMM dddd yyyy KK 00 aa");
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

            GoalCalenderCache = sqldate.format(cal.getTime().getTime());

            updateDateTextView(m_o_a);
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);

            GoalCalenderCache = sqldate.format(cal.getTime().getTime());

            updateTimeTextView(m_o_w);
        }
    };

    GoalAdapter goalAdapter;
    ArrayList<GoalCard> goalCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //goalCards = new GoalCardsCollection.getGoalCards();
        goalAdapter = new GoalAdapter(this, goalCards);

        // get max id and pass new id
        GoalID = getMaxID() + 1;

//        toastMessage(String.valueOf(GoalID));

        ll_repeatsection = (LinearLayout) findViewById(R.id.repeat_section);
        ll_qbpsection = (LinearLayout) findViewById(R.id.quit_bad_priority_section);
        ll_otnotificationsection = (LinearLayout) findViewById(R.id.onetime_notification_section);

        tv_monthly_date = (TextView) findViewById(R.id.monthly_date);
        tv_monthly_time = (TextView) findViewById(R.id.monthly_time);
        tv_onetime_date = (TextView) findViewById(R.id.onetime_date);
        tv_onetime_time = (TextView) findViewById(R.id.onetime_time);
        tv_weekly_time = (TextView) findViewById(R.id.weekly_time);
        tv_monthly_xth_xday = (RadioButton) findViewById(R.id.rb_monthly_xth_xday);
        tv_annually_date = (TextView) findViewById(R.id.annually_date);
        et_title = (EditText) findViewById(R.id.goal_title_et);

        DailyOption = (LinearLayout) findViewById(R.id.daily);
        WeeklyOption = (LinearLayout) findViewById(R.id.weekly);
        MonthlyOption = (LinearLayout) findViewById(R.id.monthly);
        Annually = (LinearLayout) findViewById(R.id.annually);

        tb_mo = (ToggleButton) findViewById(R.id.tb_morning);
        tb_no = (ToggleButton) findViewById(R.id.tb_noon);
        tb_ni = (ToggleButton) findViewById(R.id.tb_night);
        tb_mon = (ToggleButton) findViewById(R.id.tb_Mon);
        tb_tue = (ToggleButton) findViewById(R.id.tb_Tue);
        tb_wed = (ToggleButton) findViewById(R.id.tb_Wed);
        tb_thu = (ToggleButton) findViewById(R.id.tb_Thu);
        tb_fri = (ToggleButton) findViewById(R.id.tb_Fri);
        tb_sat = (ToggleButton) findViewById(R.id.tb_Sat);
        tb_sun = (ToggleButton) findViewById(R.id.tb_Sun);

        long date = System.currentTimeMillis();
        GoalCalenderCache = sqldate.format(date);
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

        tv_annually_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                m_o_a = "ann";

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
                        GoalRepeat = "D";
                        DailyOption.setVisibility(View.VISIBLE);
                        WeeklyOption.setVisibility(View.GONE);
                        MonthlyOption.setVisibility(View.GONE);
                        Annually.setVisibility(View.GONE);
                        break;
                    case 1:
                        GoalRepeat = "W";
                        DailyOption.setVisibility(View.GONE);
                        WeeklyOption.setVisibility(View.VISIBLE);
                        MonthlyOption.setVisibility(View.GONE);
                        Annually.setVisibility(View.GONE);
                        break;
                    case 2:
                        GoalRepeat = "M";
                        DailyOption.setVisibility(View.GONE);
                        WeeklyOption.setVisibility(View.GONE);
                        MonthlyOption.setVisibility(View.VISIBLE);
                        Annually.setVisibility(View.GONE);
                        break;
                    case 3:
                        GoalRepeat = "A";
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

    public void GoalTypeSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    GoalType = "GG";
                    ll_repeatsection.setVisibility(View.VISIBLE);
                    ll_qbpsection.setVisibility(View.GONE);
                    ll_otnotificationsection.setVisibility(View.GONE);
                break;
            case R.id.radioButton2:
                if (checked)
                    GoalType = "QB";
                    ll_repeatsection.setVisibility(View.GONE);
                    ll_qbpsection.setVisibility(View.VISIBLE);
                    ll_otnotificationsection.setVisibility(View.GONE);
                break;
            case R.id.radioButton3:
                if (checked)
                    GoalType = "OR";
                    ll_repeatsection.setVisibility(View.GONE);
                    ll_qbpsection.setVisibility(View.GONE);
                    ll_otnotificationsection.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void GoalPrioritySelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.quit_bad_priority_urgent:
                if (checked)
                    GoalRepeat = "U";
                break;
            case R.id.quit_bad_priority_important:
                if (checked)
                    GoalType = "I";
                break;
            case R.id.quit_bad_priority_normal:
                if (checked)
                    GoalType = "N";
                break;
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

    public void MorningToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalDToggle += "mo";
        }
        else{
            GoalDToggle = GoalDToggle.replaceAll("mo", "");
        }
    }

    public void NoonToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalDToggle += "no";
        }
        else{
            GoalDToggle = GoalDToggle.replaceAll("no", "");
        }
    }

    public void NightToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalDToggle += "ni";
        }
        else{
            GoalDToggle = GoalDToggle.replaceAll("ni", "");
        }
    }

    public void SunToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalWToggle += "sun";
        }
        else{
            GoalWToggle = GoalWToggle.replaceAll("sun", "");
        }
    }

    public void MonToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalWToggle += "mon";
        }
        else{
            GoalWToggle = GoalWToggle.replaceAll("mon", "");
        }
    }

    public void TueToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalWToggle += "tue";
        }
        else{
            GoalWToggle = GoalWToggle.replaceAll("tue", "");
        }
    }

    public void WedToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalWToggle += "wed";
        }
        else{
            GoalWToggle = GoalWToggle.replaceAll("wed", "");
        }
    }

    public void ThuToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalWToggle += "thu";
        }
        else{
            GoalWToggle = GoalWToggle.replaceAll("thu", "");
        }
    }

    public void FriToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalWToggle += "fri";
        }
        else{
            GoalWToggle = GoalWToggle.replaceAll("fir", "");
        }
    }

    public void SatToggled(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            GoalWToggle += "sat";
        }
        else{
            GoalWToggle = GoalWToggle.replaceAll("sat", "");
        }
    }

    public void MonthModeSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_monthly_sameday:
                if (checked)
                    GoalMMode = "sd";
                break;
            case R.id.rb_monthly_xth_xday:
                if (checked)
                    GoalMMode = "xx";
                break;
        }
    }

    private int getMaxID(){
        int maxid = 0;
        SQLiteDatabase database;
        SQLiteOpenHelper dbhelper = new GoalDatabaseHelper(this);
        database = dbhelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor1 = database.rawQuery("SELECT MAX(_ID) AS max_id FROM " + GoalContract.GoalEntry.TABLE_NAME, null);
        if (cursor1.moveToFirst())
        {
            do {
                maxid = cursor1.getInt(0);
            } while(cursor1.moveToNext());
        }
        return maxid;
    }

    public void AddGoalData(int id, String title, String subtitle, String type){
        dataSource.open();
        dataSource.insertGoal(id, title, subtitle, type);
        dataSource.close();
        goalAdapter.notifyItemInserted(0);
    }

    public void AddGoalData(int id, String title, String subtitle, String type, String repeat, String dtoggle, String wtoggle, String mmode, String cal){
        dataSource.open();
        dataSource.insertGoal(id, title, subtitle, type, repeat, dtoggle, wtoggle, mmode, cal);
        dataSource.close();
        goalAdapter.notifyItemInserted(0);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void Close(View view) {
        AlertDialog.Builder exit_alert_builder = new AlertDialog.Builder(this);
        exit_alert_builder.setMessage("Are you sure you wan to discard this goal?")
                .setCancelable(false)
                .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Keep Editing", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = exit_alert_builder.create();

        GoalTitle = et_title.getText().toString();
        if (GoalTitle == null || Objects.equals(GoalTitle, "")){
            finish();
        }
        else{alert.show();}
    }

    public void SaveAndCloseGoal(View view) {

        GoalTitle = et_title.getText().toString();
        if (GoalTitle == null || Objects.equals(GoalTitle, "")){
            toastMessage("Please Enter the Title of Your Goal!");
        }
        else{
//            AddGoalData(GoalID, GoalTitle, "This is a test", GoalType);
            AddGoalData(GoalID, GoalTitle, "This should be something else", GoalType, GoalRepeat, GoalDToggle, GoalWToggle, GoalMMode, GoalCalenderCache);
            Intent Back_Main = new Intent(add_goal_.this, MainActivity.class);
            startActivity(Back_Main);
        }

    }
}
