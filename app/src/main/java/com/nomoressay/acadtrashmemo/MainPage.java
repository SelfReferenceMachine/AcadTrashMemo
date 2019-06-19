package com.nomoressay.acadtrashmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.nomoressay.acadtrashmemo.Note.NoteActivity;


public class MainPage extends AppCompatActivity {


    private BottomNavigationView mBv;
    private ViewPager mVp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initView();
    }


    private void initView(){
        mBv = (BottomNavigationView)findViewById(R.id.navigation);
        mVp = (ViewPager) findViewById(R.id.vp);

        mBv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mVp.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    //View listView = getActivity().findViewById(R.id.list);
                    //Intent intent = new Intent();
                    //intent.setClass(MainPage.this,NoteActivity.class);
                    //startActivity(intent);
                    mVp.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mVp.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    });

        setupViewPage(mVp);


        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBv.getMenu().getItem(position).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void setupViewPage(ViewPager viewPager){
        BottomViewAdapter adapter = new BottomViewAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new DashFragment());
        adapter.addFragment(new SettingFragment());
        viewPager.setAdapter(adapter);
    }


}

