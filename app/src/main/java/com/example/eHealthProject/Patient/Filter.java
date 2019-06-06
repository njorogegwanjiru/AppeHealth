package com.example.eHealthProject.Patient;


import android.support.annotation.IdRes;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.eHealthProject.R;


public class Filter extends AppCompatActivity {
    Button locationFilter, hospitalFilter, specializationFilter, ageFilter, genderFilter, availabilityFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        locationFilter = findViewById(R.id.locationFilter);
        hospitalFilter = findViewById(R.id.hospitalFilter);
        specializationFilter = findViewById(R.id.hospitalFilter);
        ageFilter = findViewById(R.id.ageFilter);
        genderFilter = findViewById(R.id.genderFilter);
        availabilityFilter = findViewById(R.id.availabilityFilter);

        locationFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationFilter.setBackgroundResource(R.drawable.chipsclicked);
            }
        });

    }

}
