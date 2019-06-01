package com.example.eHealthProject.Patient;


import android.support.annotation.IdRes;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.eHealthProject.R;


public class Filter extends AppCompatActivity {
    Chip choicechip, choicechip2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ChipGroup choiceChipGroup = findViewById(R.id.choice_chip_group);
        choicechip = findViewById(R.id.choice_chip);
        choicechip = findViewById(R.id.choice_chip2);
        choiceChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, @IdRes int i) {
                if(choicechip.isChecked()){}
                else
                    if(choicechip2.isChecked()){}
            }
        });
    }
}
