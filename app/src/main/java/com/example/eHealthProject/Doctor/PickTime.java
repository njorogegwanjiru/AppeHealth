package com.example.eHealthProject.Doctor;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eHealthProject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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


    String[] docdays;
    FirebaseAuth auth;
    DatabaseReference reference;
    DatabaseReference reference2;
    String doctorId;
    String selectedday;
    String myFormat = "dd/MM/yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);;

    public PickTime() {
    }


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
        docdays = new String[7];

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
                docdays[0] = "";docdays[1] = "";docdays[2] = "";docdays[3] = "";docdays[4] = "";docdays[5] = "";docdays[6] = "";

                int x = 0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Log.d(TAG, "data: "+ snapshot.getChildren());

                    String doc = snapshot.getValue().toString();
                    if(doc == null){
                        Log.d(TAG, "Doctor object is null: ");
                    }else {
                        Log.d(TAG, "Doctor Object : "+ doc);
                        doctorsDays.add(doc);
                        docdays[x] = doc;
                    }
                    x++;
                }

                ArrayAdapter adapter = new ArrayAdapter(PickTime.this, android.R.layout.simple_spinner_item, docdays);
                days.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void saveTime(View view) {
        selectedday = days.getSelectedItem().toString();
        String starttext = start.getText().toString();
        String endtext = end.getText().toString();
        String durationtext =  duration.getText().toString();
        String maxtext = max.getText().toString();


        HashMap<String, String> details = new HashMap<>();
        details.put("Start Time", String.valueOf(starttext));
        details.put("End Time", String.valueOf(endtext));
        details.put("Duration", String.valueOf(durationtext));
        details.put("Maximum", String.valueOf(maxtext));

        FirebaseUser firebaseUser = auth.getCurrentUser();
        doctorId = firebaseUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference("Doctors").child(doctorId).child("Times").child(selectedday);
        reference.setValue(details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PickTime.this, "Time Updated", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(PickTime.this, PickTime.class);
//                    startActivity(intent);

                }
                else {
                    Toast.makeText(PickTime.this, "Update failed. Try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
        ArrayList timeslots = new ArrayList();

        int fromHour, fromMinute, toHour, toMinute;

        DateFormat df = new SimpleDateFormat("HH:mm");
        String startFormat = df.format(starttext);
        String endFormat = df.format(endtext);

        fromHour = Integer.parseInt(startFormat.split(":")[0]);
        fromMinute = Integer.parseInt(startFormat.split(":")[1]);

        toHour = Integer.parseInt(endFormat.split(":")[0]);
        toMinute = Integer.parseInt(endFormat.split(":")[1]);

        int slotMinute = Integer.parseInt(durationtext);
        long slot = slotMinute*60*1000;

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, fromHour);
        calendar2.set(Calendar.MINUTE, fromMinute);

        long currentTime = calendar2.getTimeInMillis();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, toHour);
        calendar1.set(Calendar.MINUTE, toMinute);

        long endTime = calendar1.getTimeInMillis();

        while (currentTime < endTime){
            timeslots.add(sdf.format(new Date(currentTime)));
            currentTime = currentTime + slot;}

            HashMap<String, List> Slots = new HashMap<>();
            Slots.put(selectedday,timeslots );
            reference2 = FirebaseDatabase.getInstance().getReference("Doctors").child(doctorId).child("Slots").child(selectedday);
            reference2.setValue(Slots);

    }

    public  ArrayList<String> getTimeSlot(int slotMinute, String strFromTme, String strToTime,  ArrayList<String> timeSlot){
        int fromHour, fromMinute, toHour, toMinute;
        fromHour = Integer.parseInt(strFromTme.split(":")[0]);
        fromMinute = Integer.parseInt(strFromTme.split(":")[1]);

        toHour = Integer.parseInt(strToTime.split(":")[0]);
        toMinute = Integer.parseInt(strToTime.split(":")[1]);

        long slot = slotMinute*60*1000;
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, fromHour);
        calendar2.set(Calendar.MINUTE, fromMinute);

        long currentTime = calendar2.getTimeInMillis();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, toHour);
        calendar1.set(Calendar.MINUTE, toMinute);

        long endTime = calendar1.getTimeInMillis();

        timeSlot = new ArrayList<>();
        while (currentTime < endTime){
            timeSlot.add(sdf.format(new Date(currentTime)));
            currentTime = currentTime + slot;
        }

return timeSlot;
    }
}
