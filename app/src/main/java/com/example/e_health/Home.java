package com.example.e_health;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.e_health.Adapter.ViewPagerAdapter;
import com.example.e_health.Fragments.Profile;
import com.example.e_health.Fragments.Schedule;
import com.example.e_health.Model.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

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
        viewPagerAdapter.addFragment(new com.example.e_health.Fragments.Home(), "Book Appointment");
        viewPagerAdapter.addFragment(new Schedule(),"My Appointments");
        viewPagerAdapter.addFragment(new Profile(), "My Medication");
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
        tabLayout.getTabAt(2).setIcon(tabIcons[1]);
        tabLayout.getTabAt(3).setIcon(tabIcons[2]);
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
                startActivity(new Intent(Home.this, Settings.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home.this, FullscreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

        }
        return  false;
    }
}
