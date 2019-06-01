package com.example.eHealthProject.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eHealthProject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LocationRegistration extends AppCompatActivity {
    EditText location_name;

    Button save_location;
    String loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_registration);

        location_name = findViewById(R.id.location_name);
        save_location = findViewById(R.id.save_location);


        save_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loc = location_name.getText().toString();
                if (TextUtils.isEmpty(loc)){
                    Toast.makeText(LocationRegistration.this, "Nothing entered", Toast.LENGTH_LONG).show();
                }
                saveLocation(loc);
                location_name.setText("");
            }
        });

    }

    private void saveLocation(String locationName){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap =  new HashMap<>();
        hashMap.put("locationName", locationName);
        reference.child("Locations").push().setValue(hashMap);
        Toast.makeText(LocationRegistration.this, "Location Added", Toast.LENGTH_LONG).show();
    }

}