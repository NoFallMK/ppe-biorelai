package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ModifQuantite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_quantite);
        Bundle extras = getIntent().getExtras();

        String idCommandeActuelle = extras.getString("idCommande");
        final TextView textIdentificationCommande = findViewById(R.id.lblCommande);
        String txtCommande = "Commande n° " + idCommandeActuelle;
        textIdentificationCommande.setText(txtCommande);

        String produitActuel = extras.getString("nomProduit");
        final TextView textIdentificationProduit = findViewById(R.id.lblProduit);
        String txtProduit = produitActuel;
        textIdentificationProduit.setText(txtProduit);

        String qtteProduit = extras.getString("qtteProduit");;
        final Text

        Button btnRetour = (Button) findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnRetour) {
                Intent intentRetour = new Intent(ModifQuantite.this, SignalerActivity.class);
                startActivity(intentRetour);
            }
        });
    }
}