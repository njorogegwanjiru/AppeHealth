package com.example.e_health;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class All_Patients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__patient);
    }

    public void doctor(View view) {

        Intent intent = new Intent(view.getContext(), Home.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "0");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void hospital(View view) {

        Intent intent = new Intent(view.getContext(), Home.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "0");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void appointments(View view) {

        Intent intent = new Intent(view.getContext(), Home.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "1");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void meds(View view) {

        Intent intent = new Intent(view.getContext(), Home.class);
        final Bundle bundle = new Bundle();
        bundle.putString("TabNumber", "2");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
