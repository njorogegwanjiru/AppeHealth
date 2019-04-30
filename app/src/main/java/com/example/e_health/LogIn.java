package com.example.e_health;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    EditText Email, Pass;
    Button login;
    private ProgressDialog pd;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        auth = FirebaseAuth.getInstance();

        Email = findViewById(R.id.loginEmail);
        Pass = findViewById(R.id.loginPass);
        login = findViewById(R.id.login);
        pd = new ProgressDialog(this);
        pd.setTitle("CLoading...");
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.setIndeterminate(true);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                String txtEmail = Email.getText().toString();
                String txtPass = Pass.getText().toString();

                if(TextUtils.isEmpty(txtEmail)|TextUtils.isEmpty(txtPass))
                {
                    Toast.makeText(LogIn.this, "All fields required", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
                else{
                    auth.signInWithEmailAndPassword(txtEmail,txtPass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(LogIn.this, Home.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                        pd.dismiss();
                                    }else{
                                        Toast.makeText(LogIn.this, "Log in Failed", Toast.LENGTH_LONG).show();
                                        pd.dismiss();
                                    }
                                }
                            });
                }
            }
        });

    }
}
