package com.example.eHealthProject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eHealthProject.Models.Doctor;
import com.example.eHealthProject.Models.Medication;
import com.example.eHealthProject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

    public class MedAdapter  extends RecyclerView.Adapter<com.example.eHealthProject.Adapters.MedAdapter.ViewHolder> {
        private Context mContext;
        private List<Medication> mMeds;

        public MedAdapter(Context mContext, List<Medication> mMeds) {
            this.mContext = mContext;
            this.mMeds = mMeds;
        }

        @NonNull
        @Override
        public com.example.eHealthProject.Adapters.MedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.medication_item,parent,false);
            return new com.example.eHealthProject.Adapters.MedAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.eHealthProject.Adapters.MedAdapter.ViewHolder holder, int position) {
            Medication med = mMeds.get(position);

            String date = med.getTo();
            try {
                Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                String stdate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(stdate);
                if(tdate.after(fdate)){
                    holder.mother.setBackground(ContextCompat.getDrawable(mContext,R.drawable.past));
                }else{
                    holder.mother.setBackground(ContextCompat.getDrawable(mContext,R.drawable.pending));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


            holder.name.append(med.getName());
            holder.start.append(med.getFrom());
            holder.end.append(med.getTo());
            holder.prescription.append(med.getTimes());


        }

        @Override
        public int getItemCount()
        {
            return mMeds.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView name, start, end, prescription;
            public ImageView profile_image;
            public RelativeLayout mother;


            public  ViewHolder(View itemView){
                super(itemView);

                name = itemView.findViewById(R.id.name);
                start = itemView.findViewById(R.id.start);
                end = itemView.findViewById(R.id.end);
                prescription = itemView.findViewById(R.id.prescription);
                profile_image = itemView.findViewById(R.id.profile_image_);
                mother = itemView.findViewById(R.id.mother);
            }
        }
    }


