package com.example.eHealthProject.Patient.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eHealthProject.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile extends Fragment {

    CircleImageView image_profile;
    TextView username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        return  view;
    }
}
