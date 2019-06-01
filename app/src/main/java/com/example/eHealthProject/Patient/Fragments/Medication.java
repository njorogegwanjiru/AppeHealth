package com.example.eHealthProject.Patient.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.eHealthProject.Adapters.MedAdapter;
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


public class Medication extends Fragment {
    FloatingActionButton addMedication;
    private RecyclerView recyclerView;
    LinearLayout noMeds;
    List<com.example.eHealthProject.Models.Medication> medications;
    MedAdapter medAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medication, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        medications = new ArrayList<>();

        noMeds = view.findViewById(R.id.nomeds);

        addMedication = view.findViewById(R.id.addMedication);
        addMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.eHealthProject.Patient.Medication.class);
                startActivity(intent);
            }
        });
        readMedications();
        return view;
    }

    private void readMedications() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String pid = firebaseUser.getUid();

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Medication");
        reference.orderByChild("to");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                medications.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    com.example.eHealthProject.Models.Medication med = snapshot.getValue(com.example.eHealthProject.Models.Medication.class);

                    assert med != null;
                    if(med.getPatientid().equals(pid)){
                        medications.add(med);
                    }
                }
                if(medications.isEmpty()){
                    noMeds.setVisibility(View.VISIBLE);
                }else{
                    noMeds.setVisibility(View.GONE);
                }
                medAdapter = new MedAdapter(getContext(), medications);
                recyclerView.setAdapter(medAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
