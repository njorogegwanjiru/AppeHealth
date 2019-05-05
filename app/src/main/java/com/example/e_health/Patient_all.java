package com.example.e_health;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_health.Adapter.ViewPagerAdapter;
import com.example.e_health.Fragments.P_Registration.one;
import com.example.e_health.Fragments.P_Registration.three;
import com.example.e_health.Fragments.P_Registration.two;
import com.example.e_health.Fragments.Profile;
import com.example.e_health.Fragments.Schedule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Patient_all extends AppCompatActivity {
    ViewPager viewPager;
    Calendar myCalendar ;
    FirebaseAuth auth;
    ProgressDialog pd;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_all);

        auth = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        pd.setTitle("Creating Account...");
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.setIndeterminate(true);


        viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new one(), "Home");
        viewPagerAdapter.addFragment(new two(),"Schedule");
        viewPagerAdapter.addFragment(new three(), "Profile");

        viewPager.setAdapter(viewPagerAdapter);
    }
    public void selectFragment(int position){
        viewPager.setCurrentItem(position, true);
    }

    public void birthDate(final EditText x) {
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                x.setText(sdf.format(myCalendar.getTime()));

            }
        };
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Patient_all.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

    }
    public  void  saveData(int id, Bundle data){}
    public void retrieveData(){}

    public void register (final String fname, final String mname, final String lname, final String email, final String phone, final String dob, final String sex, String password){
        pd.show();
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            String userid =firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Patients").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("First Name",String.valueOf(fname));
                            hashMap.put("Middle Name",String.valueOf(mname));
                            hashMap.put("Last Name",String.valueOf(lname));
                            hashMap.put("Email Address",String.valueOf(email));
                            hashMap.put("Phone Number",String.valueOf(phone));
                            hashMap.put("Date Of Birth",String.valueOf(dob));
                            hashMap.put("Gender",String.valueOf(sex));

                            pd.dismiss();

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(Patient_all.this, patient_menu.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(Patient_all.this, "Registration failed. Try again.", Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }
                    }
                });
    }



}
