package com.example.map.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.map.smartcity.adapters.RouteAdapter;
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

public class Show_gps extends AppCompatActivity {
    private RecyclerView recyclerView;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    RouteAdapter gpsAdapter;
    ArrayList<String> mdata = new ArrayList<>();
    Button btn;
    String s99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gps);
        recyclerView = findViewById(R.id.postList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        gpsAdapter = new RouteAdapter();
        recyclerView.setAdapter(gpsAdapter);
        btn = findViewById(R.id.btn);
        s99 = getIntent().getStringExtra("id");
//        Log.d("YOOOO", s);
        DatabaseReference ref = database.getReference("ClientData");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<String, HashMap<String, String>> s = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();

                Log.e("yooooooooo", "onDataChange: " + s);
                Iterator it = s.entrySet().iterator();
                List<ArrayList> data = new ArrayList<>();

                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    HashMap<String, String> p = (HashMap<String, String>) pair.getValue();
                    String l = pair.getKey().toString();
                    if (l.equals(s99)) {

                        Iterator it1 = p.entrySet().iterator();
                        while (it1.hasNext()) {
                            Map.Entry pair1 = (Map.Entry) it1.next();
                            String k = (String) pair1.getValue();
                            ArrayList<String> a = new ArrayList<>();
                            a.add(k);
                            mdata.add(k);
                            data.add(a);
                            it1.remove();
                        }
                    }

                    it.remove();
                }
                gpsAdapter.setGpsList1(data);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

//        getDistance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Show_gps.this, MapGenerator.class);
//                String ur = "https://www.google.co.in/maps/dir/" + mdata.get(0) + "/" + mdata.get(1) + "/" + mdata.get(2) + "/" + mdata.get(0);
                intent.putExtra("gpsdata", mdata);
                startActivity(intent);
//                Toast.makeText(getBaseContext(), "Building...", Toast.LENGTH_SHORT).show();
            }
        });


    }

    String parsedDistance;
    String response;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.browser_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showHotels:

                Intent i = new Intent(Show_gps.this, ShowLandMark.class);
                i.putExtra("list", mdata);
                i.putExtra("type", 2);
                startActivity(i);
                break;

            case R.id.showRest:
                Intent i1 = new Intent(Show_gps.this, ShowLandMark.class);
                i1.putExtra("list", mdata);
                i1.putExtra("type", 1);
                startActivity(i1);
                break;

        }
        return true;
    }


}
