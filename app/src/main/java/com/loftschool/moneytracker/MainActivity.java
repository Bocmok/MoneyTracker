package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.main_screen_title);

        viewPager =findViewById(R.id.viewPager);

        tabLayout =findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIndicator));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorInactive),getResources().getColor(R.color.colorActive));

        MainPagesAdapter adapter = new MainPagesAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

}
