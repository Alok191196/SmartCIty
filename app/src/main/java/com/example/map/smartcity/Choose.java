package com.example.map.smartcity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Choose extends AppCompatActivity {


    Button b1;
    Button b2;
    Button b3;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        b1 = findViewById(R.id.showList);
        b2 = findViewById(R.id.AddList);
        b3 = findViewById(R.id.button3);
        auth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose.this, plan.class);
                startActivity(intent);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Choose.this, AddLocation.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                Intent intent = new Intent(Choose.this, LoginActivity.class);
                startActivity(intent);
            }

        });

    }
}
