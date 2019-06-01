package com.example.eHealthProject.Patient;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eHealthProject.Models.Patient;
import com.example.eHealthProject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class patient_menu extends AppCompatActivity {
    ImageView iv;
    TextView user;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_menu);

        iv = findViewById(R.id.iv);
        user = findViewById(R.id.user);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Patients").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Patient patient = dataSnapshot.getValue(Patient.class);
                assert patient != null;
                user.setText("");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Glide.with(this).load(R.raw.doc).into(iv);
    }
    public void doctor(View view) {

        Intent intent = new Intent(view.getContext(), Filter.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "0");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void hospital(View view) {

        Intent intent = new Intent(view.getContext(), Filter.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "0");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void appointments(View view) {

        Intent intent = new Intent(view.getContext(), Home.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "1");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void meds(View view) {

        Intent intent = new Intent(view.getContext(), Home.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "2");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
