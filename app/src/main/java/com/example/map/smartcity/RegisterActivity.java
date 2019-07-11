package com.example.map.smartcity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();

        final EditText etAge =(EditText) findViewById(R.id.etAge);
        final EditText etfname =(EditText) findViewById(R.id.etfname);
        final EditText etEmail =(EditText) findViewById(R.id.etEmail);
        final EditText etPassword =(EditText) findViewById(R.id.etpass);


        final Button bRegister =(Button) findViewById(R.id.bRegister);


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                Toast.makeText(RegisterActivity.this, email +"  "+password ,
                        Toast.LENGTH_SHORT).show();


                auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Authentication failed."
                                    ,Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Done! " ,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
