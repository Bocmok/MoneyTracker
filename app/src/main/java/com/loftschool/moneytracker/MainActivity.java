package com.loftschool.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.loftschool.moneytracker.ItemsFragment.ADD_ITEM_REQUEST_CODE;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.main_screen_title);

        viewPager =findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(this);

        tabLayout =findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIndicator));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorInactive),getResources().getColor(R.color.colorActive));

        MainPagesAdapter adapter = new MainPagesAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage=viewPager.getCurrentItem();
                String type=null;
                if (currentPage==MainPagesAdapter.PAGE_INCOMES){
                    type=Item.TYPE_INCOMES;
                } else if (currentPage==MainPagesAdapter.PAGE_EXPENSES){
                    type=Item.TYPE_EXPENSES;
                }


                Intent intent=new Intent(MainActivity.this, AddItemActivity.class);
                intent.putExtra(AddItemActivity.TYPE_KEY,type);
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case MainPagesAdapter.PAGE_INCOMES:
                case MainPagesAdapter.PAGE_EXPENSES:
                    fab.show();
                    break;
                    case MainPagesAdapter.PAGE_BALANCE:
                        fab.hide();
                        break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state){
            case ViewPager.SCROLL_STATE_IDLE:
                fab.setEnabled(true);
                break;
                case ViewPager.SCROLL_STATE_DRAGGING:
                    case ViewPager.SCROLL_STATE_SETTLING:
                        fab.setEnabled(false);
                        break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (Fragment fragment: getSupportFragmentManager().getFragments()){
            fragment.onActivityResult(requestCode,resultCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
