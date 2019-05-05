package com.example.e_health.Fragments.P_Registration;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_health.PatientRegistration;
import com.example.e_health.Patient_all;
import com.example.e_health.R;


public class three extends Fragment implements View.OnClickListener {
    EditText patientEmail, patientPhone,patientPass, patientPass2;
    String fname, mname, lname, dob, sex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle  savedInstanceState) {
        View v = inflater.inflate((R.layout.fragment_three), container, false);

        patientEmail = v.findViewById(R.id.patientEmail);
        patientPhone = v.findViewById(R.id.patientPhone);
        patientPass = v.findViewById(R.id.patientPass);
        patientPass2 = v.findViewById(R.id.patientPass2);

        TextView b = v.findViewById(R.id.register_btn);
        b.setOnClickListener(this);
        return v;

    }
    @Override
    public void onClick(View view) {
        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            fname = bundle.getString("fname");
            mname = bundle.getString("mname");
            lname = bundle.getString("lname");
            dob = bundle.getString("dob");
            sex = bundle.getString("sex");
        }
        String email = patientEmail.getText().toString();
        String phone = patientPhone.getText().toString();
        String pass1 = patientPass.getText().toString();
        String pass2 = patientPass2.getText().toString();

        if (TextUtils.isEmpty(email)|
                TextUtils.isEmpty(phone)|
                TextUtils.isEmpty(pass1)|
                TextUtils.isEmpty(pass2))
        {
            Toast.makeText(getActivity(), "All fields required!", Toast.LENGTH_LONG).show();
        }
        else
        if (!pass1.equals(pass2))
        {
            Toast.makeText(getActivity(), "The Passwords do not match", Toast.LENGTH_LONG).show();
        }
        else {
            ((Patient_all)getActivity()).register (fname, mname, lname, email, phone, dob, sex, pass2);
        }

    }
}
