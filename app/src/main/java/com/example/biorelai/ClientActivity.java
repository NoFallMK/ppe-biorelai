package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import okhttp3.OkHttpClient;

public class ClientActivity extends AppCompatActivity {

    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);




        findViewById(R.id.btnQuitterClient).setOnClickListener(v -> {finishAffinity(); });
    }
}