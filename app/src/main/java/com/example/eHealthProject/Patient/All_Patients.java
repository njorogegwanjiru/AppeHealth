package com.example.eHealthProject.Patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eHealthProject.Shared.FullscreenActivity;
import com.example.eHealthProject.Shared.Help;
import com.example.eHealthProject.R;
import com.example.eHealthProject.Shared.Settings;
import com.google.firebase.auth.FirebaseAuth;

public class All_Patients extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__patient);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void doctor(View view) {

        Intent intent = new Intent(view.getContext(), patient_view_doctors.class);
        startActivity(intent);
    }
    public void hospital(View view) {

        Intent intent = new Intent(view.getContext(), patient_view_doctors.class);
        startActivity(intent);
    }
    public void appointments(View view) {

        Intent intent = new Intent(view.getContext(), Home.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "0");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void meds(View view) {

        Intent intent = new Intent(view.getContext(), Home.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "1");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.settings:
                startActivity(new Intent(All_Patients.this, Settings.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.help:
                startActivity(new Intent(All_Patients.this, Help.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(All_Patients.this, FullscreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

        }
        return  false;
    }
}
