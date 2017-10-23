package com.czeng.tidyday.add_goal_memo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.czeng.tidyday.R;

public class add_goal_ extends AppCompatActivity {

    LinearLayout ll_repeatsection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ll_repeatsection = (LinearLayout) findViewById(R.id.repeat_section);


    }

    public void Close(View view) {
        finish();
    }

    public void GoalTypeSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    ll_repeatsection.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton2:
                if (checked)
                    ll_repeatsection.setVisibility(View.GONE);
                break;
            case R.id.radioButton3:
                if (checked)
                    ll_repeatsection.setVisibility(View.GONE);
                break;
        }
    }

}
