package com.example.eHealthProject.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.eHealthProject.Models.Location;
import com.example.eHealthProject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HospitalRegistration extends AppCompatActivity {
    CircleImageView hospitalImage;
    EditText hospitalName;
    Spinner hospitalLocation;
    Button addHospital;

    private List<String> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_registration);

        locations = new ArrayList<>();

        hospitalImage = findViewById(R.id.hospitalImage);

        hospitalName = findViewById(R.id.hospitalName);
        hospitalLocation = findViewById(R.id.hospitalLocation);
        readLocations();
        addHospital = findViewById(R.id.addHospital);
        addHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = hospitalName.getText().toString();
                String loc = hospitalLocation.getSelectedItem().toString();
                String imageUrl = "default";
                if (TextUtils.isEmpty(loc)|TextUtils.isEmpty(name)){
                    Toast.makeText(HospitalRegistration.this, "All fields Required", Toast.LENGTH_LONG).show();
                }else{
                    addHospital(name, loc, imageUrl );
                }
            }
        });

    }

    private void readLocations() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Locations");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locations.clear();
                locations.add("Select Location");
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Location mlocations = snapshot.getValue(Location.class);

                    assert mlocations!=null;
                    String locationnames = mlocations.getLocationName();

                    locations.add(locationnames);
                }
                ArrayAdapter adapter = new ArrayAdapter<> (HospitalRegistration.this, android.R.layout.simple_spinner_item, locations);
                hospitalLocation.setAdapter(adapter);
                hospitalLocation.setSelection(0);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addHospital(String name, String location, String imageUrl) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap =  new HashMap<>();

        hashMap.put("HospitalName", name);
        hashMap.put("HospitalLocation", location);
        hashMap.put("Image", imageUrl);

        reference.child("Hospitals").push().setValue(hashMap);
        Toast.makeText(HospitalRegistration.this, "Hospital Added", Toast.LENGTH_LONG).show();

    }
}
