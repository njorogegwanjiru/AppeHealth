package com.example.eHealthProject.Shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eHealthProject.Doctor.DoctorRegistration;
import com.example.eHealthProject.Patient.PatientRegistration;
import com.example.eHealthProject.R;


public class FullscreenActivity extends AppCompatActivity {
TextView register, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        register = findViewById(R.id.register_btn);
        login = findViewById(R.id.login_btn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FullscreenActivity.this, PatientRegistration.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FullscreenActivity.this, LogIn.class);
                startActivity(intent);
            }
        });

    }


    public void docRegister(View view) {
        Intent intent = new Intent(FullscreenActivity.this, DoctorRegistration.class);
        startActivity(intent);
    }
}
