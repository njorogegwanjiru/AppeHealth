package com.example.eHealthProject.Patient;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.eHealthProject.Patient.Fragments.Medication;
import com.example.eHealthProject.Shared.Help;
import com.example.eHealthProject.Adapters.ViewPagerAdapter;
import com.example.eHealthProject.Patient.Fragments.Profile;
import com.example.eHealthProject.Patient.Fragments.Schedule;
import com.example.eHealthProject.Shared.FullscreenActivity;
import com.example.eHealthProject.R;
import com.example.eHealthProject.Shared.Settings;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_home_black_small,
            R.drawable.ic_schedule_black_small,
            R.drawable.ic_person_black_small
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Schedule(),"Appointments");
        viewPagerAdapter.addFragment(new Medication(), "Medication");
        viewPagerAdapter.addFragment(new Profile(), "Profile");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        final Intent intent = getIntent();
        if (intent.hasExtra("TabNumber")){
            String tab = intent.getExtras().getString("TabNumber");
            switchToTab(tab);
        }
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
    public void switchToTab(String tab){
        if (tab.equals("0")) {
            viewPager.setCurrentItem(0);
        }else
            if(tab.equals("1")){
                    viewPager.setCurrentItem(1);
                }else
                    if(tab.equals("2")){
                        viewPager.setCurrentItem(2);
                    }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.settings:
                startActivity(new Intent(Home.this, Settings.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.help:
                startActivity(new Intent(Home.this, Help.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home.this, FullscreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

        }
        return  false;
    }
}
