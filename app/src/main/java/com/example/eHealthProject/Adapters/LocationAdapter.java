package com.example.eHealthProject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eHealthProject.Models.Location;
import com.example.eHealthProject.Patient.patient_view_doctors;
import com.example.eHealthProject.R;

import java.util.List;


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private Context mContext;
    private List<Location> locations;

    public LocationAdapter(Context mContext, List<Location> locations) {
        this.mContext = mContext;
        this.locations = locations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.location_item, parent, false);
        return new LocationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
    final Location location = locations.get(position);
    holder.loc.setText(location.getLocationName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, patient_view_doctors.class);
            intent.putExtra("location", location.getLocationName());
            mContext.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount()
    {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView loc;
        public ViewHolder(View itemView) {
            super(itemView);
            loc = itemView.findViewById(R.id.location_item_view);

        }
    }
}