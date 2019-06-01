package com.example.eHealthProject.Doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eHealthProject.Models.Hospital;

import com.example.eHealthProject.Models.Location;

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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DoctorRegistration extends AppCompatActivity {
    EditText doc_name, doc_specialization, doc_phone, doc_email, doc_NID;
    RadioGroup doc_practice, doc_gender;
    RadioButton practice, gender, hospital, privatepractice;
    LinearLayout institution_info, location_info;
    TextView location_name, register_doc, inst_location;
    Spinner birth_year, institution_name, doc_loc;

    ProgressDialog pd;
    FirebaseAuth auth;
    DatabaseReference reference;
    int selected ;
    String location="";
    String institution="";

    private List<String> locations;
    private List<String> institutions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        locations = new ArrayList<>();
        institutions = new ArrayList<>();

        pd = new ProgressDialog(this);
        pd.setTitle("Registering Doctor...");
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.setIndeterminate(true);

        auth = FirebaseAuth.getInstance();


//Radio Groups
        doc_practice = findViewById(R.id.doctorPractice);
        doc_gender = findViewById(R.id.docGender);
//Radio buttons
        hospital = findViewById(R.id.hospital);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                institution_info.setVisibility(View.VISIBLE);
                location_info.setVisibility(View.GONE);
            }
        });
        privatepractice = findViewById(R.id.privatepractice);

        privatepractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                institution_info.setVisibility(View.GONE);
                location_info.setVisibility(View.VISIBLE);
            }
        });
//Layouts
        institution_info = findViewById(R.id.instname);
        location_info = findViewById(R.id.locations);

//Edit texts
        doc_name = findViewById(R.id.doctorName);
        doc_specialization = findViewById(R.id.doctorSpecialization);
        doc_phone = findViewById(R.id.doctorPhhone);
        doc_email = findViewById(R.id.doctorEmail);
        doc_NID = findViewById(R.id.doctorID);

//Text Views
        register_doc = findViewById(R.id.registerDoctor);

//Spinners
        birth_year = findViewById(R.id.doctorYOB);
        yearsList();

        doc_loc = findViewById(R.id.docselectloc);
        readLocations();

        institution_name = findViewById(R.id.institutionname);
        readHospitals();

        register_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPractice();
                String practicetext = practice.getText().toString();
                String name = doc_name.getText().toString();
                String docNID =doc_NID.getText().toString();
                String specialization = doc_specialization.getText().toString();
                String phone = doc_phone.getText().toString();
                String yob = birth_year.getSelectedItem().toString();
                String email = doc_email.getText().toString();
                selectedGender();
                String gendertext = gender.getText().toString();
                String status = "Pending";
                List days = new ArrayList();

                int h = R.id.hospital;
                int l = R.id.privatepractice;

                if(selected == h)
                {
                    location= "TODO";
                    institution = institution_name.getSelectedItem().toString();
                }
                else if (selected == l)
                {
                    location= doc_loc.getSelectedItem().toString();
                    institution = "NA";
                }
                if (TextUtils.isEmpty(name)|
                        TextUtils.isEmpty(specialization)|
                        TextUtils.isEmpty(docNID)|
                        TextUtils.isEmpty(phone)|
                        TextUtils.isEmpty(email)|
                        TextUtils.isEmpty(gendertext)|
                        TextUtils.isEmpty(practicetext)|
                        TextUtils.isEmpty(yob)){
                    Toast.makeText(DoctorRegistration.this, "All Fields Required", Toast.LENGTH_LONG).show();
                }else {
                    registerDoc(name,docNID,email,gendertext,practicetext,yob,specialization,phone,location,institution);
                }
            }
        });
    }
    private void yearsList () {
        ArrayList<String> years = new ArrayList();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        years.add("-Select Year Of Birth-");
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        birth_year.setAdapter(adapter);
        birth_year.setSelection(0);
    }
    private void readLocations() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Locations");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locations.clear();
                locations.add("Select Location");
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Location mlocations = snapshot.getValue(Location.class);

                    assert mlocations!=null;
                    String locationnames = mlocations.getLocationName();

                    locations.add(locationnames);
                }
                ArrayAdapter adapter3 = new ArrayAdapter<> (DoctorRegistration.this, android.R.layout.simple_spinner_item, locations);
                doc_loc.setAdapter(adapter3);
                doc_loc.setSelection(0);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void readHospitals() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Hospitals");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                institutions.clear();
                institutions.add("Select Hospital");
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Hospital mhospitals = snapshot.getValue(Hospital.class);

                    assert mhospitals!=null;
                    String hnames = mhospitals.getHospitalName();

                    institutions.add(hnames);
                }
                ArrayAdapter adapter2 = new ArrayAdapter<> (DoctorRegistration.this, android.R.layout.simple_spinner_item, institutions);
                institution_name.setAdapter(adapter2);
                institution_name.setSelection(0);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void selectedGender() {
        int selectedId = doc_gender.getCheckedRadioButtonId();
        gender = findViewById(selectedId);
    }
    private void  selectedPractice(){
        selected =  doc_practice.getCheckedRadioButtonId();
        practice = findViewById(selected);
    }
    private void registerDoc(final String name, String docNID, final String email, final String gendertext, final String practicetext, final String yob, final String specialization, final String phone, final String institution, final String location){
        pd.show();
        auth.createUserWithEmailAndPassword(email,docNID)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Doctors").child(userid);

                            List days = new ArrayList();
                            HashMap<String, Object> doctors = new HashMap<>();
                            doctors.put("id", userid);
                            doctors.put("Names", String.valueOf(name));
                            doctors.put("Email", String.valueOf(email));
                            doctors.put("Gender", String.valueOf(gendertext));
                            doctors.put("Practice", String.valueOf(practicetext));
                            doctors.put("YOB", String.valueOf(yob));
                            doctors.put("Specialization", String.valueOf(specialization));
                            doctors.put("Phone", String.valueOf(phone));
                            doctors.put("Institution", String.valueOf(institution));
                            doctors.put("Location", String.valueOf(location));
                            doctors.put("Days", days);
                            doctors.put("Times", "");
                            pd.dismiss();

                            reference.setValue(doctors).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(DoctorRegistration.this, "Registration successful.", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(DoctorRegistration.this, DocHome.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(DoctorRegistration.this, "Registration failed. Try again.", Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }
                    }
                });

    }
}