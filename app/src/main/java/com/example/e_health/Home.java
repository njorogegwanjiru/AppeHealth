package com.example.e_health;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.e_health.Adapter.ViewPagerAdapter;
import com.example.e_health.Model.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {
    CircleImageView profile_image;
    TextView username;
    Toolbar toolbar;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        profile_image = findViewById(R.id.profile);
        username = findViewById(R.id.username);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Patients").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Patient patient = dataSnapshot.getValue(Patient.class);
               username.setText(patient.getFname()+""+patient.getMname()+""+patient.getLname());
               username.setAllCaps(true);

               profile_image.setImageResource(R.drawable.profile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);



    }
}
