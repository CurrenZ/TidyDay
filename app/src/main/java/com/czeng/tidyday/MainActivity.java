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

import com.czeng.tidyday.add_goal_memo.add_goal_;
import com.czeng.tidyday.add_goal_memo.add_memo_;
import com.czeng.tidyday.tabs.goal_class;
import com.czeng.tidyday.tabs.memo_class;

public class MainActivity extends AppCompatActivity {

    private boolean fab_add_isOpen = false;
    private FloatingActionButton fab_add_, fab_goal_, fab_memo_;
    private Animation fab_open, fab_close, rotate_fwd, rotate_bwd;
    private View alpha_backgrond;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_goal,
            R.drawable.ic_memo,
            R.drawable.ic_goal_dim,
            R.drawable.ic_memo_dim
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // fab stuff
        fab_add_ = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_goal_ = (FloatingActionButton) findViewById(R.id.fab_goal);
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
        fab_goal_.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent_add_goal = new Intent(MainActivity.this, add_goal_.class);
                animate_close_tabs();
                startActivity(intent_add_goal);

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
        setTabsIc(0);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager){
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        setTabsIc(tab.getPosition());
                    }
                }
        );

    }

    private void animate_close_tabs(){
        fab_add_.startAnimation(rotate_bwd);
        fab_goal_.startAnimation(fab_close);
        fab_memo_.startAnimation(fab_close);
        fab_goal_.setClickable(false);
        fab_memo_.setClickable(false);
        fab_add_isOpen = false;
    }

    private void animateFab(){
        if(fab_add_isOpen){
            animate_close_tabs();
        }
        else{
            fab_add_.startAnimation(rotate_fwd);
            fab_goal_.startAnimation(fab_open);
            fab_memo_.startAnimation(fab_open);
            fab_goal_.setClickable(true);
            fab_memo_.setClickable(true);
            fab_add_isOpen = true;
        }
    }

    private void setTabsIc(int tabNum){
        if (tabNum == 0) {
            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
            tabLayout.getTabAt(1).setIcon(tabIcons[3]);
        }
        else if (tabNum == 1) {
            tabLayout.getTabAt(0).setIcon(tabIcons[2]);
            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        }
        else{
            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
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
                    return new goal_class();
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
                    return "Goals";
                case 1:
                    return "Momnets";
            }
            return null;
        }
    }
}
