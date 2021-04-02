package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProducteurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producteur);

        Button btnDeconnexion = (Button) findViewById(R.id.btnDeconnexionProducteur);
        btnDeconnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnDeconnexion) {
                Intent intentDeconnexion = new Intent(ProducteurActivity.this, MainActivity.class);
                startActivity(intentDeconnexion);
            }
        });
    }
}