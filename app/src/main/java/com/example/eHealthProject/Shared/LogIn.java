package com.example.eHealthProject.Shared;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.eHealthProject.Models.Doctor;
import com.example.eHealthProject.Admin.admin_home;
import com.example.eHealthProject.Doctor.DocHome;
import com.example.eHealthProject.Patient.All_Patients;
import com.example.eHealthProject.Models.Patient;
import com.example.eHealthProject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LogIn extends AppCompatActivity {
       private EditText Email, Pass;
       private Button login;
       private RadioGroup category;
       private ProgressDialog pd;
       private FirebaseAuth auth;
       private DatabaseReference reference ;
       private List<Patient> patientList;
       private List<Doctor> doctorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        auth = FirebaseAuth.getInstance();

        Email = findViewById(R.id.loginEmail);
        Pass = findViewById(R.id.loginPass);
        login = findViewById(R.id.login);
        category = findViewById(R.id.category);

        patientList = new ArrayList<>();
        doctorList = new ArrayList<>();

        pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.setIndeterminate(true);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                final String txtEmail = Email.getText().toString();
                final String txtPass = Pass.getText().toString();

                if(TextUtils.isEmpty(txtEmail)|TextUtils.isEmpty(txtPass))
                {
                    Toast.makeText(LogIn.this, "All fields required", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }else
                    if (txtEmail.equals("admin@gmail.com") && txtPass.equals("admins")){
                        pd.show();
                        Intent intent = new Intent(LogIn.this, admin_home.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        pd.dismiss();
                    }
                else{
                    auth.signInWithEmailAndPassword(txtEmail,txtPass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        int selectedId = category.getCheckedRadioButtonId();
                                        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                                        if (txtEmail.equals("admin@gmail.com") && txtPass.equals("admins")){
                                            pd.show();
                                            Intent intent = new Intent(LogIn.this, admin_home.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                            pd.dismiss();
                                        }
                                        else
                                        if(selectedId == R.id.radioDoc)
                                        {
                                            reference = FirebaseDatabase.getInstance().getReference("Patients");
                                            reference.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    patientList.clear();
                                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                        Patient patient = snapshot.getValue(Patient.class);

                                                        assert patient != null;
                                                        assert firebaseUser != null;
                                                        if (firebaseUser.getUid().equals(patient.getId())) {
                                                            patientList.add(patient);
                                                        }
                                                    }
                                                    if (patientList.isEmpty()) {
                                                        Intent intent = new Intent(LogIn.this, DocHome.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(intent);
                                                        finish();
                                                        pd.dismiss();


                                                    } else {
                                                        Toast.makeText(LogIn.this, "Log in Failed. User not registered as a Doctor!", Toast.LENGTH_LONG).show();
                                                        pd.dismiss();
                                                    }

                                                }


                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            }

                                        else
                                            if(selectedId==R.id.radioPatient) {
                                                reference = FirebaseDatabase.getInstance().getReference("Patients");
                                                reference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                        patientList.clear();
                                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                            Patient patient = snapshot.getValue(Patient.class);

                                                            assert patient != null;
                                                            assert firebaseUser != null;
                                                            if (firebaseUser.getUid().equals(patient.getId())) {
                                                               patientList.add(patient);
                                                            }
                                                        }
                                                        if (patientList.isEmpty()) {
                                                            Toast.makeText(LogIn.this, "Log in Failed. User not registered as a patient!", Toast.LENGTH_LONG).show();
                                                            pd.dismiss();

                                                        } else {
                                                            Intent intent = new Intent(LogIn.this, All_Patients.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(intent);
                                                            finish();
                                                            pd.dismiss();
                                                        }

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                                }

                                    }
                                    else{
                                        Toast.makeText(LogIn.this, "Log in Failed", Toast.LENGTH_LONG).show();
                                        pd.dismiss();
                                    }
                                }
                            });
                }
            }
        });

    }

}