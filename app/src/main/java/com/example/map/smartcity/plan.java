package com.example.map.smartcity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.map.smartcity.adapters.RouteAdapter1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class plan extends AppCompatActivity implements RouteAdapter1.ListItemClickListener {

    private RecyclerView recyclerView;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    RouteAdapter1 gpsAdapter;
    ArrayList<String> mdata = new ArrayList<>();
    Button btn;
    List<ArrayList> data = new ArrayList<>();
    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        recyclerView = findViewById(R.id.postList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        gpsAdapter = new RouteAdapter1(this);
        recyclerView.setAdapter(gpsAdapter);
        DatabaseReference ref = database.getReference("ClientData");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, HashMap<String, String>> s = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();
                Iterator it = s.entrySet().iterator();

                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    String l = pair.getKey().toString();
                    ArrayList<String> a = new ArrayList<>();
                    a.add(l);
                    data.add(a);
                    it.remove();
                }
                gpsAdapter.setGpsList1(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    @Override
    public void onListItemClick(int id) {

        String s = data.get(id).get(0).toString();
        Log.d("YOOOO", s + " ----------");
        Intent intent = new Intent(plan.this, Show_gps.class);
        intent.putExtra("id", s);
        startActivity(intent);
    }
}
