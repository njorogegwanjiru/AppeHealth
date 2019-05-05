package com.example.e_health.Fragments.P_Registration;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_health.PatientRegistration;
import com.example.e_health.Patient_all;
import com.example.e_health.R;


public class one extends Fragment implements View.OnClickListener {
TextView b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle  savedInstanceState) {
        View v = inflater.inflate((R.layout.fragment_one), container, false);

        b = v.findViewById(R.id.startbtn);
        b.setOnClickListener(this);
        return v;

    }
    @Override
    public void onClick(View view) {
        ((Patient_all)getActivity()).selectFragment(1);
    }
}
