package com.czeng.tidyday.add_goal_memo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.czeng.tidyday.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_memo_ extends AppCompatActivity {

    private TextView memo_date;

    public Calendar cal = Calendar.getInstance();

    private SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
    private DateFormat date_format = DateFormat.getDateInstance();

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateDateTextView();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        long date = System.currentTimeMillis();
        String dateString = sdf.format(date);
        memo_date = (TextView) findViewById(R.id.memo_the_date);
        memo_date.setText(dateString);

        memo_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog(){
        new DatePickerDialog(this, d, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateDateTextView() {
        memo_date.setText(date_format.format(cal.getTime()));
    }

    public void Close(View view) {
        finish();
    }
}
