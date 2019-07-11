package com.example.map.smartcity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText etEmail;
    EditText etpassword;
    Button blogin;
    Button signup;

    public void reset() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your email!");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = input.getText().toString();
                if (s.length() != 0) {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(s)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getBaseContext(), "check your email for reset link.", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                } else {
                    Toast.makeText(getBaseContext(), "Fill all the fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    TextView forget;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  Log.i("step","1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        forget = findViewById(R.id.forget);

        etEmail = findViewById(R.id.etEmail);
        etpassword = findViewById(R.id.etpassword);
        blogin = findViewById(R.id.blogin);
        signup = findViewById(R.id.tvRegisterHere);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reset();
            }
        });
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, Choose.class));
        }

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(LoginActivity.this, "Signing in..", Toast.LENGTH_SHORT).show();

                String email = etEmail.getText().toString();
                String password = etpassword.getText().toString();

                auth.signInWithEmailAndPassword(email, password)

                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    finish();
                                    Intent intent = new Intent(LoginActivity.this, Choose.class);
                                    startActivity(intent);
                                }
                            }
                        });


            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);


            }
        });


//        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);


    }
}
