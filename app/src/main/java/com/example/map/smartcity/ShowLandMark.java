package com.example.map.smartcity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.map.smartcity.adapters.RouteAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowLandMark extends AppCompatActivity {
    private RecyclerView recyclerView;
    RouteAdapter gpsAdapter;
    ArrayList<String> mdata = new ArrayList<>();
    RequestQueue queue;
    List<ArrayList> data;

    int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_land_mark);

        queue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.postList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        gpsAdapter = new RouteAdapter();
        recyclerView.setAdapter(gpsAdapter);
        ArrayList<String> mdata = getIntent().getStringArrayListExtra("list");
        type = getIntent().getIntExtra("type", 1);
        data = new ArrayList<>();

        for (int i = 0; i < mdata.size(); i++) {
            if (i == mdata.size() - 1)
                getDistance(mdata.get(i), 1);
            else
                getDistance(mdata.get(i), 0);

        }

    }

    public void getDistance(final String loc, final int x) {
        String url;
        if (type == 1)
            url = "https://www.google.co.in/search?q=restaurants+in+" + loc;
        else
            url = "https://www.google.co.in/search?q=hotels+in+" + loc;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Document doc = null;
                        try {
                            doc = Jsoup.parse(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        Elements newsHeadlines = doc.select("div._uee");
                        Elements al = doc.select("a.rllt__text");
                        for (Element headline : al) {
                            ArrayList<String> a = new ArrayList<>();

                            a.add("<html><b>"+headline.select("span").text() + "</b> - [" + loc + "] <br> " +
                                    "<a href='https://www.google.co.in" + headline.attr("href")+"'>Visit Website</a></html>");
                            data.add(a);

                        }
                        if (x == 1)
                            gpsAdapter.setGpsList1(data);

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
