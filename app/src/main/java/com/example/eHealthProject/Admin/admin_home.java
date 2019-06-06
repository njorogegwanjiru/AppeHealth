package com.example.eHealthProject.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eHealthProject.Doctor.DoctorRegistration;
import com.example.eHealthProject.Patient.All_Patients;
import com.example.eHealthProject.R;
import com.example.eHealthProject.Shared.FullscreenActivity;
import com.example.eHealthProject.Shared.Help;
import com.example.eHealthProject.Shared.Settings;
import com.google.firebase.auth.FirebaseAuth;

public class admin_home extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void register_hospital(View view) {
        Intent intent = new Intent(view.getContext(), HospitalRegistration.class);
        startActivity(intent);
    }

    public void register_location(View view) {
        Intent intent = new Intent(view.getContext(), LocationRegistration.class);
        startActivity(intent);
    }

    public void view_doctors(View view) {
        Intent intent = new Intent(view.getContext(),AdminViewDoctors.class);
        startActivity(intent);
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
                startActivity(new Intent(admin_home.this, Settings.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.help:
                startActivity(new Intent(admin_home.this, Help.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(admin_home.this, FullscreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

        }
        return  false;
    }
}
