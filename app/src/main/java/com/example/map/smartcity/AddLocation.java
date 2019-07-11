package com.example.map.smartcity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.map.smartcity.adapters.RouteAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddLocation extends AppCompatActivity {

    private RecyclerView recyclerView;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText etdata;
    Button b;
    TextView v;
    RouteAdapter gpsAdapter;
    private String m_Text = "";
    //    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//    ArrayList<String> gpsall = new ArrayList<>();

    public void plan() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                v.setText(m_Text);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locations);
        v = findViewById(R.id.plan);
//        while(m_Text.equals(""))
            plan();
        recyclerView = findViewById(R.id.postList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        gpsAdapter = new RouteAdapter();
        recyclerView.setAdapter(gpsAdapter);


        etdata = findViewById(R.id.et);
        b = findViewById(R.id.btn);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> data = new ArrayList<>();
                String k = etdata.getText().toString();
                data.add(k);
                gpsAdapter.setGpsList(data);

                DatabaseReference myRef = database.getReference("ClientData");
                myRef.child(m_Text).child(k).setValue(k);
            }
        });

    }
//
}
