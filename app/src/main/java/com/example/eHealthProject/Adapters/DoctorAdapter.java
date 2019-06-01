package com.example.eHealthProject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eHealthProject.Models.Doctor;
import com.example.eHealthProject.R;

import java.util.Calendar;
import java.util.List;



public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private Context mContext;
    private List<Doctor> mDocs;

    public DoctorAdapter(Context mContext, List<Doctor> mDocs){
        this.mDocs = mDocs;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.doctor_item,parent,false);
        return new DoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        Doctor doc= mDocs.get(position);
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = thisYear  - (Integer.parseInt(doc.getYOB()));
        String agevalue = Integer.toString(age);

        holder.username.setText(doc.getNames());
        holder.specialty.append(doc.getSpecialization());
        holder.age.append(agevalue);
        holder.gender.append(doc.getGender());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Appointment_Booking.class);
                 mContext.startActivity(intent);
            }
        });

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


        public  ViewHolder(View itemView){
            super(itemView);

            username = itemView.findViewById(R.id.username_);
            specialty = itemView.findViewById(R.id.specialty_);
            age = itemView.findViewById(R.id.age_);
            gender = itemView.findViewById(R.id.gender_);
            profile_image = itemView.findViewById(R.id.profile_image_);
        }
    }
}
