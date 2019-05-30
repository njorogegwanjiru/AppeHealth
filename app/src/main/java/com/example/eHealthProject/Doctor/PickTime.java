package com.example.eHealthProject.Doctor;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eHealthProject.Admin.DoctorRegistration;
import com.example.eHealthProject.Admin.HospitalRegistration;
import com.example.eHealthProject.Models.Doctor;
import com.example.eHealthProject.Models.Location;
import com.example.eHealthProject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickTime extends AppCompatActivity {
    private static final String TAG = "PickTime";
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    Spinner days;
    EditText start, end, duration, max;
    List<String> doctorsDays;
    List<String> details;

    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);

        auth = FirebaseAuth.getInstance();

        days = findViewById(R.id.spinner);
        readDays();
        start = findViewById(R.id.start_time);
        end = findViewById(R.id.end_time);
        duration = findViewById(R.id.duration);
        max = findViewById(R.id.max);

        doctorsDays = new ArrayList();
        details = new ArrayList<>();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime(start);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTime(end);
            }
        });
    }

    public EditText startTime(final EditText editText){
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes)
            {
                if (hourOfDay >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                editText.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
            }
        }, currentHour, currentMinute, false);


        timePickerDialog.show();
        return editText;
    }
    public EditText endTime(final EditText editText){
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes)
            {
                if (hourOfDay >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                editText.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
            }
        }, currentHour, currentMinute, false);


        timePickerDialog.show();
        return editText;
    }

    public void EditDays(View view) {
        Intent intent = new Intent(this, PickBusinessHours.class);
        startActivity(intent);
    }

    public void readDays(){

        FirebaseUser firebaseUser = auth.getCurrentUser();

        String doctorId = firebaseUser.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors").child(doctorId).child("Days");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doctorsDays.clear();
                doctorsDays.add("Select a day");

                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Log.d(TAG, "data: "+ snapshot.getChildren());

                    String doc = (String) snapshot.getValue();
                    if(doc == null){
                        Log.d(TAG, "Doctor object is null: ");
                    }else {
                        Log.d(TAG, "Doctor Object : "+ doc);
                        doctorsDays.add(doc);
                    }
                }
                ArrayAdapter adapter = new ArrayAdapter (PickTime.this, android.R.layout.simple_spinner_item, doctorsDays);
                days.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void saveTime(View view) {
        String selectedday = days.getSelectedItem().toString();
        String starttext = start.getText().toString();
        String endtext = end.getText().toString();
        String durationtext =  duration.getText().toString();
        String maxtext = max.getText().toString();

        details.add(0,starttext);
        details.add(1,endtext);
        details.add(2, durationtext);
        details.add(3, maxtext);

        FirebaseUser firebaseUser = auth.getCurrentUser();
        String doctorId = firebaseUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference("Doctors").child(doctorId).child("Days").child(selectedday);
        reference.setValue(details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PickTime.this, "Time Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PickTime.this, PickTime.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PickTime.this, "Update failed. Try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
