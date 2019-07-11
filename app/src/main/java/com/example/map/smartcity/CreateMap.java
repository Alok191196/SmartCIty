package com.example.map.smartcity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CreateMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_map);
        String s = getIntent().getStringExtra("gpsdata");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
        startActivity(intent);
    }

}
