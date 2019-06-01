package com.example.eHealthProject.Doctor;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eHealthProject.Admin.LocationRegistration;
import com.example.eHealthProject.Patient.All_Patients;
import com.example.eHealthProject.Patient.PatientRegistration;
import com.example.eHealthProject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class PickBusinessHours extends AppCompatActivity {

    Switch monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private List<String> businessDays;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_business_hours);
        auth = FirebaseAuth.getInstance();

        businessDays = new ArrayList<>();

        monday = findViewById(R.id.monday);
        monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (monday.isChecked()){
                        businessDays.add("Monday");
                    }else {
                        if(businessDays.contains("Monday")){
                            businessDays.remove("Monday");
                        }
                    }
            }
        });

        tuesday = findViewById(R.id.tuesday);
        tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tuesday.isChecked()){
                    businessDays.add("Tuesday");
                }else {
                    if(businessDays.contains("Tuesday")){
                        businessDays.remove("Tuesday");
                    }
                }
            }
        });

        wednesday = findViewById(R.id.wednesday);
        wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (wednesday.isChecked()){
                        businessDays.add("Wednesday");
                    }else {
                        if(businessDays.contains("Wednesday")){
                            businessDays.remove("Wednesdayy");
                        }
                    }
                }
            });

        thursday = findViewById(R.id.thursday);
        thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (thursday.isChecked()){
                    businessDays.add("Thursday");
                }else {
                    if(businessDays.contains("Thursday")){
                        businessDays.remove("Thursday");
                    }
                }
            }
        });

        friday = findViewById(R.id.friday);
        friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (friday.isChecked()){
                    businessDays.add("Friday");
                }else {
                    if(businessDays.contains("Friday")){
                        businessDays.remove("Friday");
                    }
                }
            }
        });

        saturday = findViewById(R.id.saturday);
        saturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (saturday.isChecked()){
                    businessDays.add("Saturday");
                }else {
                    if(businessDays.contains("Saturday")){
                        businessDays.remove("Saturday");
                    }
                }
            }
        });

        sunday = findViewById(R.id.sunday);
        sunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sunday.isChecked()){
                    businessDays.add("Sunday");
                }else {
                    if(businessDays.contains("Sunday")){
                        businessDays.remove("Sunday");
                    }
                }
            }
        });

    }

    private void saveDays(List list){
        FirebaseUser firebaseUser = auth.getCurrentUser();

        String doctorId = firebaseUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference("Doctors").child(doctorId).child("Days");
        reference.setValue(list).addOnCompleteListener(new OnCompleteListener<Void>(){
        @Override 
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                Toast.makeText(PickBusinessHours.this, "Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PickBusinessHours.this, PickTime.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(PickBusinessHours.this, "Update failed. Try again.", Toast.LENGTH_LONG).show();
            }
        }
    });
    }
    public void submitDays(View view) {
        saveDays(businessDays);
    }
}
