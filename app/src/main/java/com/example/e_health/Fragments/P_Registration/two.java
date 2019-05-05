package com.example.e_health.Fragments.P_Registration;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_health.PatientRegistration;
import com.example.e_health.Patient_all;
import com.example.e_health.R;

import java.util.Calendar;


public class two extends Fragment implements View.OnClickListener {
    EditText patientFirstName, patientMiddleName, patientLastName, patientDOB;
    RadioGroup patientGender;
    RadioButton gender;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate((R.layout.fragment_two), container, false);

        patientFirstName = v.findViewById(R.id.patientFirstName);
        patientMiddleName = v.findViewById(R.id.patientMiddleName);
        patientLastName = v.findViewById(R.id.patientLastName);
        patientDOB = v.findViewById(R.id.patientDOB);
        ((Patient_all)getActivity()).birthDate(patientDOB);

        patientGender = v.findViewById(R.id.patientGender);
        int selectedId = patientGender.getCheckedRadioButtonId();
        gender = v.findViewById(selectedId);

        TextView b = v.findViewById(R.id.two);
        b.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        String fname = patientFirstName.getText().toString();
        String mname = patientMiddleName.getText().toString();
        String lname = patientLastName.getText().toString();
        String dob = patientDOB.getText().toString();
        String sex = gender.getText().toString();
        if (TextUtils.isEmpty(fname)|TextUtils.isEmpty(mname)| TextUtils.isEmpty(lname)| TextUtils.isEmpty(dob)| TextUtils.isEmpty(sex)){
            Toast.makeText(getActivity(), "All fields required!", Toast.LENGTH_LONG).show();
        }
        else {
           three three = new three();
            Bundle bundle = new Bundle();
            bundle.putString("fname",fname);
            bundle.putString("mname",mname);
            bundle.putString("lname",lname);
            bundle.putString("dob",dob);
            bundle.putString("sex",sex);
            three.setArguments(bundle);


            ((Patient_all)getActivity()).selectFragment(2);

        }

    }
}




