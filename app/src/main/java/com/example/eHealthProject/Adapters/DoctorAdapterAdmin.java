package com.example.eHealthProject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eHealthProject.Admin.AdminViewDoctors;
import com.example.eHealthProject.Models.Doctor;
import com.example.eHealthProject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;



public class DoctorAdapterAdmin extends RecyclerView.Adapter<DoctorAdapterAdmin.ViewHolder> {
    private Context mContext;
    private List<Doctor> mDocs;
    Doctor doc;

    public DoctorAdapterAdmin(Context mContext, List<Doctor> mDocs){
        this.mDocs = mDocs;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public DoctorAdapterAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.doctor_item_admin,parent,false);
        return new DoctorAdapterAdmin.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapterAdmin.ViewHolder holder, int position) {
        doc = mDocs.get(position);
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = thisYear - (Integer.parseInt(doc.getYOB()));
        String agevalue = Integer.toString(age);

        holder.username.setText(doc.getNames());
        holder.specialty.append(doc.getSpecialization());
        holder.age.append(agevalue);
        holder.gender.append(doc.getGender());
/*
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Doctors")
                        .child("id")
                        .child(doc.getId())
                        .removeValue();
            }
        });
        */
    }

    @Override
    public int getItemCount()
    {
        return mDocs.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView username, specialty, age, gender;
        public ImageView profile_image;
        Button delete ;


        public  ViewHolder(View itemView){
            super(itemView);

            username = itemView.findViewById(R.id.username_);
            specialty = itemView.findViewById(R.id.specialty_);
            age = itemView.findViewById(R.id.age_);
            gender = itemView.findViewById(R.id.gender_);
            profile_image = itemView.findViewById(R.id.profile_image_);
            delete = itemView.findViewById(R.id.deleteDoctor);
        }
    }
}
