package com.czeng.tidyday;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.czeng.tidyday.tabs.habit_class;
import com.czeng.tidyday.tabs.memo_class;

public class MainActivity extends AppCompatActivity {

    private TextView habit_popup, memeo_popup;
    private boolean fab_add_isOpen = false;
    private boolean fab_habit_isOpen = false;
    private boolean fab_memo_isOpen = false;
    private FloatingActionButton fab_add_, fab_habit_, fab_memo_;
    private Animation fab_open, fab_close, rotate_fwd, rotate_bwd;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_fitness,
            R.drawable.ic_create
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // fab stuff
        fab_add_ = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_habit_ = (FloatingActionButton) findViewById(R.id.fab_habit);
        fab_memo_ = (FloatingActionButton) findViewById(R.id.fab_memo);
        fab_open = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this,R.anim.fab_close);
        rotate_fwd = AnimationUtils.loadAnimation(this,R.anim.rotate_fwd);
        rotate_bwd = AnimationUtils.loadAnimation(this, R.anim.rotate_bwd);

        fab_add_.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        fab_habit_.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent_add_habit = new Intent(MainActivity.this, add_habit_.class);
                animate_close_tabs();
                startActivity(intent_add_habit);

            }
        });
        fab_memo_.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent_add_memo = new Intent(MainActivity.this, add_memo_.class);
                animate_close_tabs();
                startActivity(intent_add_memo);

            }
        });

        // tab stuff
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setTabsIc(2);

    }

    private void animate_close_tabs(){
        fab_add_.startAnimation(rotate_bwd);
        fab_habit_.startAnimation(fab_close);
        fab_memo_.startAnimation(fab_close);
        fab_habit_.setClickable(false);
        fab_memo_.setClickable(false);
        fab_add_isOpen = false;
    }

    private void animateFab(){
        if(fab_add_isOpen){
            animate_close_tabs();
        }
        else{
            fab_add_.startAnimation(rotate_fwd);
            fab_habit_.startAnimation(fab_open);
            fab_memo_.startAnimation(fab_open);
            fab_habit_.setClickable(true);
            fab_memo_.setClickable(true);
            fab_add_isOpen = true;
        }
    }

    private void setTabsIc(int tabNum){
        for (int i = 0; i < tabNum; i ++){
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new habit_class();
                case 1:
                    return new memo_class();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Habits";
                case 1:
                    return "Memos";
            }
            return null;
        }
    }
}
