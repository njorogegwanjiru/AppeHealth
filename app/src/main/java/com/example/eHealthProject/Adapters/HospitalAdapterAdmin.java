package com.example.eHealthProject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eHealthProject.Models.Hospital;
import com.example.eHealthProject.R;

import java.util.List;



public class HospitalAdapterAdmin extends RecyclerView.Adapter<HospitalAdapterAdmin.ViewHolder> {
    private Context mContext;
    private List<Hospital> mHospitals;

    public HospitalAdapterAdmin(Context mContext, List<Hospital> mHospitals){
        this.mHospitals = mHospitals;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public HospitalAdapterAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hospital_item_admin,parent,false);
        return new HospitalAdapterAdmin.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapterAdmin.ViewHolder holder, int position) {
        Hospital hos= mHospitals.get(position);



    }

    @Override
    public int getItemCount()
    {
        return mHospitals.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView hospitalName,Location, Doctors;

        public  ViewHolder(View itemView) {
            super(itemView);

            hospitalName = itemView.findViewById(R.id.hospitalname_);
            Location = itemView.findViewById(R.id.location_);
            Doctors = itemView.findViewById(R.id.doctors_);
        }
    }
}
