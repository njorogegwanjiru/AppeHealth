package com.example.eHealthProject.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.eHealthProject.Doctor.DoctorRegistration;
import com.example.eHealthProject.R;

public class admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    public void register_doctor(View view) {
        Intent intent = new Intent(view.getContext(), DoctorRegistration.class);
        startActivity(intent);
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
}
