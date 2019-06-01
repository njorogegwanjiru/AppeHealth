package com.example.eHealthProject.Patient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eHealthProject.Doctor.PickBusinessHours;
import com.example.eHealthProject.Doctor.PickTime;
import com.example.eHealthProject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Medication extends AppCompatActivity {
    EditText medname, medstart, medend, pres1, pres2;
    Button addNew;
    List<Medication> medication ;
    FirebaseAuth auth;
    DatabaseReference reference;
    Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        medname =findViewById(R.id.medname);
        medstart = findViewById(R.id.medstart);
        medstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate(medstart);
            }
        });
        medend = findViewById(R.id.medend);
        medend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate(medend);
            }
        });
        pres1 = findViewById(R.id.pres1);
        pres2 = findViewById(R.id.pres2);
        addNew = findViewById(R.id.addNew);

        medication = new ArrayList<>();

        auth = FirebaseAuth.getInstance();

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medName = medname.getText().toString();
                String startDate = medstart.getText().toString();
                String endDate = medend.getText().toString();
                String prescription = pres1.getText().append("X").append(pres2.getText()).toString();

                if (TextUtils.isEmpty(medName)){
                    Toast.makeText(Medication.this, "Medicine Name required!", Toast.LENGTH_LONG).show();
                    errorHandle(medname);
                }
                if (TextUtils.isEmpty(startDate)){
                    Toast.makeText(Medication.this, "Start date required!", Toast.LENGTH_LONG).show();
                    errorHandle(medstart);
                }
                if (TextUtils.isEmpty(endDate)){
                    Toast.makeText(Medication.this, "End date required!", Toast.LENGTH_LONG).show();
                    errorHandle(medend);
                }
                if (TextUtils.isEmpty(prescription)){
                    Toast.makeText(Medication.this, "Prescription required!", Toast.LENGTH_LONG).show();
                    errorHandle(pres1);
                    errorHandle(pres2);
                }

                saveMedication(medName, startDate, endDate, prescription);
                Intent intent = new Intent(addNew.getContext(), Home.class);
                final Bundle bundle = new Bundle();
                bundle.putString("TabNumber", "1");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    private void saveMedication(String medName, String startDate, String endDate, String prescription) {
        FirebaseUser firebaseUser = auth.getCurrentUser();
        String patientId = firebaseUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Medication");

        HashMap<String, String> medications = new HashMap<>();
        medications.put("patientid",patientId);
        medications.put("name",String.valueOf(medName));
        medications.put("from",String.valueOf(startDate));
        medications.put("to",String.valueOf(endDate));
        medications.put("times",String.valueOf(prescription));

        reference.push().setValue(medications).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Medication.this, "Added Successfully", Toast.LENGTH_LONG).show();
                    medname.setText("");
                    medstart.setText("");
                    medend.setText("");
                    pres1.setText("");
                    pres2.setText("");

                }
                else {
                    Toast.makeText(Medication.this, "Update failed. Try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void errorHandle(View view){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }
    public void selectDate(EditText view) {
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                medstart.setText(sdf.format(myCalendar.getTime()));
                medend.setText(sdf.format(myCalendar.getTime()));

            }
        };
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Medication.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
    }
}