package com.example.e_health;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class PatientRegistration extends AppCompatActivity {

EditText patientFirstName, patientMiddleName, patientLastName, patientEmail, patientPhone, patientDOB, patientPass, patientPass2;
RadioGroup patientGender;
RadioButton gender;
Calendar myCalendar ;
TextView register;
FirebaseAuth auth;
ProgressDialog pd;
DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        auth = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        pd.setTitle("Creating Account...");
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.setIndeterminate(true);

        patientFirstName = findViewById(R.id.patientFirstName);
        patientMiddleName = findViewById(R.id.patientMiddleName);
        patientLastName = findViewById(R.id.patientLastName);
        patientEmail = findViewById(R.id.patientEmail);
        patientPhone = findViewById(R.id.patientPhone);
        patientDOB = findViewById(R.id.patientDOB);
        birthDate();
        patientGender = findViewById(R.id.patientGender);
        selectedGender();
        patientPass = findViewById(R.id.patientPass);
        patientPass2 = findViewById(R.id.patientPass2);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = patientFirstName.getText().toString();
                String mname = patientMiddleName.getText().toString();
                String lname = patientLastName.getText().toString();
                String email = patientEmail.getText().toString();
                String phone = patientPhone.getText().toString();
                String dob = patientDOB.getText().toString();
                String pass1 = patientPass.getText().toString();
                String pass2 = patientPass2.getText().toString();
                String sex = gender.getText().toString();

                if (TextUtils.isEmpty(fname)|
                        TextUtils.isEmpty(mname)|
                        TextUtils.isEmpty(lname)|
                        TextUtils.isEmpty(email)|
                        TextUtils.isEmpty(phone)|
                        TextUtils.isEmpty(dob)|
                        TextUtils.isEmpty(sex)|
                        TextUtils.isEmpty(pass1)|
                        TextUtils.isEmpty(pass2))
                {
                    Toast.makeText(PatientRegistration.this, "All fields required!", Toast.LENGTH_LONG).show();
                }
                else
                    if (!pass1.equals(pass2))
                {
                    Toast.makeText(PatientRegistration.this, "The Passwords do not match", Toast.LENGTH_LONG).show();
                }
                else
                    if(pass1.length()<6)
                {
                    Toast.makeText(PatientRegistration.this, "The Passwords are too short", Toast.LENGTH_LONG).show();
                }
                else {
                    register(fname, mname, lname,email, phone, dob, sex, pass2);
                    }
            }
        });

    }
            private void selectedGender(){
                int selectedId = patientGender.getCheckedRadioButtonId();
                gender = findViewById(selectedId);
            }

            public void birthDate() {
                myCalendar = Calendar.getInstance();
                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "MM/dd/yy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        patientDOB.setText(sdf.format(myCalendar.getTime()));

                    }
                };
                patientDOB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(PatientRegistration.this,
                                date,
                                myCalendar.get(Calendar.YEAR),
                                myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH))
                                .show();
                    }
                });

            }

            private void register (final String fname, final String mname, final String lname, final String email, final String phone, final String dob, final String sex, String password){
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
                                                Intent intent = new Intent(PatientRegistration.this, patient_menu.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                            }
                                        }
                                    });

                                }else{
                                    Toast.makeText(PatientRegistration.this, "Registration failed. Try again.", Toast.LENGTH_LONG).show();
                                    pd.dismiss();
                                }
                            }
                        });
            }


            public void callTwo(){

            }
}
