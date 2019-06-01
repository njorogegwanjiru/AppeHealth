package com.example.eHealthProject.Doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.eHealthProject.R;

public class DocHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_home);
    }

    public void updateTime(View view) {
        Intent intent = new Intent(this, PickBusinessHours.class);
        startActivity(intent);
    }
}
