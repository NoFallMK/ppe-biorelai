package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ProducteurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producteur);

        try {
            final JSONObject user = new JSONObject(getIntent().getStringExtra("user"));
            final TextView textIdentification = findViewById(R.id.txtProducteur);
            String txtUser = "Bienvenue " + user.getString("NOMUTILISATEUR") + " " + user.getString("PRENOMUTILISATEUR") + " !";
            textIdentification.setText(txtUser);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button btnCommandes = (Button) findViewById(R.id.btnCommandes);
        btnCommandes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnCommandes) {
                Intent intentCommandes = new Intent(ProducteurActivity.this, CommandeProducteurActivity.class);
                startActivity(intentCommandes);
            }
        });

        Button btnOldCommandes = (Button) findViewById(R.id.btnOldCommandes);
        btnOldCommandes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnOldCommandes) {
                Intent intentOldCommandes = new Intent(ProducteurActivity.this, AnciennesCommandesActivity.class);
                startActivity(intentOldCommandes);
            }
        });

        Button btnDeconnexion = (Button) findViewById(R.id.btnDeconnexionProducteur);
        btnDeconnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnDeconnexion) {
                Intent intentDeconnexion = new Intent(ProducteurActivity.this, MainActivity.class);
                startActivity(intentDeconnexion);
            }
        });
    }
}