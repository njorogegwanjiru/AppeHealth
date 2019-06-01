package com.example.eHealthProject.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eHealthProject.Adapters.DoctorAdapterAdmin;
import com.example.eHealthProject.Models.Doctor;
import com.example.eHealthProject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminViewDoctors extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DoctorAdapterAdmin doctorAdapter;
    private List<Doctor> mDocs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_doctors);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDocs = new ArrayList<>();
        readDoctors();
    }
    private void readDoctors() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mDocs.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Doctor doctor = snapshot.getValue(Doctor.class);

                    assert doctor != null;
                    assert firebaseUser != null;
                    mDocs.add(doctor);

                }
                doctorAdapter = new DoctorAdapterAdmin(AdminViewDoctors.this, mDocs);
                recyclerView.setAdapter(doctorAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

