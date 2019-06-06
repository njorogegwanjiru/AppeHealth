package com.example.eHealthProject.Patient;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.eHealthProject.Adapters.MedAdapter;
import com.example.eHealthProject.Models.Doctor;
import com.example.eHealthProject.Adapters.DoctorAdapter;
import com.example.eHealthProject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class patient_view_doctors extends AppCompatActivity {
    Button locationFilter, hospitalFilter, specializationFilter, ageFilter, genderFilter, availabilityFilter;
    private static final String TAG = "docdata";
    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    HashMap<String,Doctor> mDocs;
    List<Doctor> doctors;
    LinearLayout noMeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_view_doctors);

        locationFilter = findViewById(R.id.locationFilter);
        hospitalFilter = findViewById(R.id.hospitalFilter);
        specializationFilter = findViewById(R.id.specializationFilter);
        ageFilter = findViewById(R.id.ageFilter);
        genderFilter = findViewById(R.id.genderFilter);
        availabilityFilter = findViewById(R.id.availabilityFilter);

        locationFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationFilter.setBackgroundResource(R.drawable.chipsclicked);
                hospitalFilter.setBackgroundResource(R.drawable.chipsbg);
                specializationFilter.setBackgroundResource(R.drawable.chipsbg);
                ageFilter.setBackgroundResource(R.drawable.chipsbg);
                genderFilter.setBackgroundResource(R.drawable.chipsbg);
                availabilityFilter.setBackgroundResource(R.drawable.chipsbg);
            }
        });
        hospitalFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospitalFilter.setBackgroundResource(R.drawable.chipsclicked);
                locationFilter.setBackgroundResource(R.drawable.chipsbg);
                specializationFilter.setBackgroundResource(R.drawable.chipsbg);
                ageFilter.setBackgroundResource(R.drawable.chipsbg);
                genderFilter.setBackgroundResource(R.drawable.chipsbg);
                availabilityFilter.setBackgroundResource(R.drawable.chipsbg);
            }
        });
        specializationFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                specializationFilter.setBackgroundResource(R.drawable.chipsclicked);
                hospitalFilter.setBackgroundResource(R.drawable.chipsbg);
                locationFilter.setBackgroundResource(R.drawable.chipsbg);
                ageFilter.setBackgroundResource(R.drawable.chipsbg);
                genderFilter.setBackgroundResource(R.drawable.chipsbg);
                availabilityFilter.setBackgroundResource(R.drawable.chipsbg);
            }
        });
        ageFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageFilter.setBackgroundResource(R.drawable.chipsclicked);
                hospitalFilter.setBackgroundResource(R.drawable.chipsbg);
                locationFilter.setBackgroundResource(R.drawable.chipsbg);
                specializationFilter.setBackgroundResource(R.drawable.chipsbg);
                genderFilter.setBackgroundResource(R.drawable.chipsbg);
                availabilityFilter.setBackgroundResource(R.drawable.chipsbg);
            }
        });
        genderFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderFilter.setBackgroundResource(R.drawable.chipsclicked);
                hospitalFilter.setBackgroundResource(R.drawable.chipsbg);
                locationFilter.setBackgroundResource(R.drawable.chipsbg);
                specializationFilter.setBackgroundResource(R.drawable.chipsbg);
                ageFilter.setBackgroundResource(R.drawable.chipsbg);
                availabilityFilter.setBackgroundResource(R.drawable.chipsbg);
            }
        });
        availabilityFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                availabilityFilter.setBackgroundResource(R.drawable.chipsclicked);
                hospitalFilter.setBackgroundResource(R.drawable.chipsbg);
                locationFilter.setBackgroundResource(R.drawable.chipsbg);
                specializationFilter.setBackgroundResource(R.drawable.chipsbg);
                ageFilter.setBackgroundResource(R.drawable.chipsbg);
                genderFilter.setBackgroundResource(R.drawable.chipsbg);
            }
        });

        noMeds = findViewById(R.id.nomeds);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDocs = new HashMap<>();
        doctors = new ArrayList<>();
        readDoctors();
    }


    private void readDoctors() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mDocs.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Doctor doctor = snapshot.getValue(Doctor.class);

                    doctors.add(doctor);
                    Log.d(TAG, "data: "+ snapshot.getChildren());

                }
                if(doctors.isEmpty()){
                    noMeds.setVisibility(View.VISIBLE);
                }else{
                    noMeds.setVisibility(View.GONE);
                }

                doctorAdapter = new DoctorAdapter(patient_view_doctors.this, doctors);
                recyclerView.setAdapter(doctorAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
