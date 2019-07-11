package com.example.map.smartcity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home1 extends AppCompatActivity {

    Button logout;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        logout = (Button) findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
            }

        });
    }
}