package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ModifQuantite extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();
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

        String quantiteProduit = extras.getString("qtteLivree");
        final EditText textIdentificationQtte = findViewById(R.id.txtQtte);
        String txtQtteLivree = quantiteProduit;
        textIdentificationQtte.setText(txtQtteLivree);

        Button btnRetour = (Button) findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnRetour) {
                Intent intentRetour = new Intent(ModifQuantite.this, SignalerActivity.class);
                startActivity(intentRetour);
            }
        });
    }
    /*public void liste() throws IOException {
        Bundle bundle = getIntent().getExtras();
        str = bundle.getString("qtteProduit");
        Log.d("Qtte3", str);
    }*/
    public void modifQunatite(String qtte) throws IOException {
        Bundle bundle = getIntent().getExtras();
        String idCommande = bundle.getString("IDCOMMANDE");
        String idProduit = bundle.getString("IDPRODUIT");

        RequestBody formBody = new FormBody.Builder()
                .add("producteur", idCommande)
                .add("produit", idProduit)
                .add("qtte", qtte)
                .build();

        Request request = new Request.Builder()
                .url("http://169.254.78.78/ppe_biorelai/controleurs/modifQuantite.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();
                Log.d("rps", responseStr);
                if (responseStr.compareTo("false") != 0) {
                    Log.d("Test IF","Fonctionne !");
                } else {
                    Log.d("Test ELSE","Erreur");
                }
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
            }

        });
    }

}