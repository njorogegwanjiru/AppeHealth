package com.example.eHealthProject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eHealthProject.Models.Doctor;
import com.example.eHealthProject.Patient.AppointmentInterface;
import com.example.eHealthProject.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private Context mContext;
    Doctor doc;
    List<List<String>> dayslist;
   // private HashMap<String,Doctor> mDocs;

    private List<Doctor> mDocs;

    public DoctorAdapter(Context mContext, List<Doctor>mDocs) {
        this.mContext = mContext;
        this.mDocs = mDocs;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.doctor_item,parent,false);
        return new DoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoctorAdapter.ViewHolder holder, int position) {
        doc = mDocs.get(position);
        if (mDocs != null) {
            int thisYear = Calendar.getInstance().get(Calendar.YEAR);
            int age = thisYear - (Integer.parseInt(doc.getYOB()));
            String agevalue = Integer.toString(age);

            holder.username.append(" "+doc.getNames());
            holder.specialty.append(doc.getSpecialization());
            holder.age.append(agevalue);
            holder.gender.append(doc.getGender());

            List days =(doc.getDays());
            Iterator iterator = days.iterator();
            while (iterator.hasNext()){
                Button button = new Button(mContext);
                button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                button.setText(iterator.next().toString());
                button.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.border_buttons));

                holder.dayslayout.addView(button);

            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, AppointmentInterface.class);
                    intent.putExtra("docid", doc.getId());
                    mContext.startActivity(intent);
                }
            });

        }
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
        public LinearLayout dayslayout;


        public  ViewHolder(View itemView){
            super(itemView);

            dayslayout = (LinearLayout) itemView.findViewById(R.id.dayslayout);
            username = itemView.findViewById(R.id.username_);
            specialty = itemView.findViewById(R.id.specialty_);
            age = itemView.findViewById(R.id.age_);
            gender = itemView.findViewById(R.id.gender_);
            profile_image = itemView.findViewById(R.id.profile_image_);
        }
    }
}
