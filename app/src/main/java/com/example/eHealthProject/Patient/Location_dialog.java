package com.example.eHealthProject.Patient;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.example.eHealthProject.Adapters.LocationAdapter;
import com.example.eHealthProject.Models.Location;
import com.example.eHealthProject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Location_dialog extends Dialog implements  android.view.View.OnClickListener {

    RecyclerView recyclerView;
    private LocationAdapter locationAdapter;
    private List<Location> locations;

    public Activity activity;

    public Location_dialog() {
        super(null);
    }

    public Location_dialog(Activity activity){
        super(activity);
        this.activity=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_location_dialog);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        locations = new ArrayList<>();
        readLocations();
    }

    private void readLocations() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Locations");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locations.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Location mlocations = snapshot.getValue(Location.class);
                    assert mlocations!=null;
                    locations.add(mlocations);
                }
                locationAdapter = new LocationAdapter(getContext(),locations);
                recyclerView.setAdapter(locationAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                break;
        }
        dismiss();
    }
}
