package com.example.map.smartcity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapGenerator extends AppCompatActivity {

    double matrix[][];
    int f = 0;
    Button btn;
    ArrayList<String> m;
    int[] res;
    TextView tv;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_generator);
        queue = Volley.newRequestQueue(this);
        m = getIntent().getStringArrayListExtra("gpsdata");
        Log.e("dfgdfgdgd", m.toString());

        matrix = new double[m.size()][m.size()];
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.status);
        btn.setEnabled(false);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapGenerator.this, CreateMap.class);
                String sr = "https://www.google.co.in/maps/dir/";
                for (int x : res) {
                    sr += m.get(x - 1) + "/";
                }
                sr += m.get(0);
                intent.putExtra("gpsdata", sr);
                startActivity(intent);
//                Toast.makeText(getBaseContext(), "Building...", Toast.LENGTH_SHORT).show();
            }
        });


        f = m.size();
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.size(); j++) {

                if (i != j) {
                    getDistance(i, j, m.get(i), m.get(j));
                }

            }
        }
    }

    public void getDistance(final int i, final int j, final String s, final String d) {
        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + s + "&destination=" + d + "&sensor=false&units=metric&mode=driving";
        Log.e("dfgdfgdgd", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        JSONObject distance = null;
                        double d = 0;
                        try {
                            jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("routes");
                            JSONObject routes = array.getJSONObject(0);
                            JSONArray legs = routes.getJSONArray("legs");
                            JSONObject steps = legs.getJSONObject(0);
                            distance = steps.getJSONObject("distance");
                            d = Double.parseDouble(distance.getString("text").replace(",", "").split(" ")[0]);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        matrix[i][j] = d;
                        if ((i == f - 1) && (j == f - 2)) {
                            try {
                                Thread.sleep(5000);
                                TSM db = new TSM(matrix, f);
                                res = db.res;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            btn.setEnabled(true);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);


    }
}
