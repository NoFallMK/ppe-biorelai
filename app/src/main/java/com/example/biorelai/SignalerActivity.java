package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.biorelai.MainActivity.utilisateur;
import static com.example.biorelai.MainActivity.idUser;


public class SignalerActivity extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signaler);

        Bundle extras = getIntent().getExtras();
        String idCommandeActuelle = extras.getString("IDCOMMANDE");
        String produitActuel = extras.getString("nomProduit");
        int qtteProduit = extras.getInt("qtteLivree");

        final TextView textIdentification = findViewById(R.id.lblCommande);
        String txtCommande = "Commande n° " + idCommandeActuelle;
        textIdentification.setText(txtCommande);

        Button btnModifQtte = (Button) findViewById(R.id.btnModifierQtte);
        btnModifQtte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnModifQtte) {
                Intent intent = new Intent(SignalerActivity.this, ModifQuantite.class);
                intent.putExtra("idCommande", idCommandeActuelle);
                intent.putExtra("nomProduit", produitActuel);
                intent.putExtra("qtteProduit", qtteProduit);
                Log.d("Qtte2", String.valueOf(qtteProduit));
                startActivity(intent);
            }
        });

        Button btnPartRecup = (Button) findViewById(R.id.btnPartRecup);
        btnPartRecup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnPartRecup) {
                try {
                    modifEtat("Partiellement récupérée");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SignalerActivity.this, CommandeProducteurActivity.class);
                intent.putExtra("user", idUser);
                startActivity(intent);
            }
        });

        Button btnNonRecup = (Button) findViewById(R.id.btnNonRecup);
        btnNonRecup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnNonRecup) {
                try {
                    modifEtat("Non récupérée");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent2 = new Intent(SignalerActivity.this, CommandeProducteurActivity.class);
                intent2.putExtra("user", idUser);
                startActivity(intent2);
            }
        });

        Button btnCliAbs = (Button) findViewById(R.id.btnCliAbs);
        btnCliAbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnCliAbs) {
                try {
                    modifEtat("Client absent");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent3 = new Intent(SignalerActivity.this, CommandeProducteurActivity.class);
                intent3.putExtra("user", idUser);
                startActivity(intent3);
            }
        });

        Button btnRetour = (Button) findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnRetour) {
                Intent intentRetour = new Intent(SignalerActivity.this, CommandeProducteurActivity.class);
                startActivity(intentRetour);
            }
        });
    }

    public void modifEtat(String etat) throws IOException {
        Bundle bundle = getIntent().getExtras();
        String idCommande = bundle.getString("IDCOMMANDE");
        Log.d("cmd", idCommande);

        RequestBody formBody = new FormBody.Builder()
                .add("idcommande", idCommande)
                .add("etat",etat)
                .build();

        Request request = new Request.Builder()
                .url("http://172.19.228.188/ppe_biorelai/controleurs/modifEtat.php")
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