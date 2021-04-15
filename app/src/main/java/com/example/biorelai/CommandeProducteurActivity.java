package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

import static com.example.biorelai.MainActivity.idUser;

public class CommandeProducteurActivity extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande_producteur);
        try {
            lesCommandes("ad.MAIL");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button btnClients = (Button) findViewById(R.id.btnParClient);
        btnClients.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnClients) {
                try {
                    lesCommandes("ad.MAIL");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnProduits = (Button) findViewById(R.id.btnParProduit);
        btnProduits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnProduits) {
                try {
                    lesCommandes("p.NOMPRODUIT");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnRetour = (Button) findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnRetour) {
                Intent intentRetour = new Intent(CommandeProducteurActivity.this, ProducteurActivity.class);
                startActivity(intentRetour);
            }
        });
    }

    public void lesCommandes(String tri) throws IOException {
        final ArrayList arrayListLesCommandes = new ArrayList<String>();
        RequestBody formBody = new FormBody.Builder()
                .add("producteur", idUser)
                .add("tri", tri)
                .build();
        Log.d("test1", idUser);
        Log.d("test1", tri);

        Request request = new Request.Builder()
                .url("http://169.254.78.78/ppe_biorelai/controleurs/lesCommandes.php")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();
                JSONArray jsonArrayLesCommandes = null;
                try {

                    jsonArrayLesCommandes = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayLesCommandes.length(); i++) {
                        JSONObject jsonlaCommande = jsonArrayLesCommandes.getJSONObject(i);
                        arrayListLesCommandes.add("Commande n°" + jsonlaCommande.getString("IDCOMMANDE") + " \r" + "Client : " + jsonlaCommande.getString("MAIL") + "\r Produit : " + jsonlaCommande.getString("NOMPRODUIT") + " \r Quantité: " + jsonlaCommande.getString("QUANTITE"));
                        Log.d("laCommande", jsonlaCommande.toString());
                    }
                } catch (JSONException e) {
                    Log.d("Test", e.getMessage());
                }

                ListView listViewLesCommandes = findViewById(R.id.listeLesAnciennesCommandes);


                ArrayAdapter<String> arrayAdapterLesCommandes = new ArrayAdapter<String>(CommandeProducteurActivity.this, android.R.layout.simple_list_item_1, arrayListLesCommandes);
                runOnUiThread(() -> {
                    listViewLesCommandes.setAdapter(arrayAdapterLesCommandes);
                });

                JSONArray finalJsonArrayCommande = jsonArrayLesCommandes;
                listViewLesCommandes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String idListe =(String) listViewLesCommandes.getItemAtPosition(position);
                        try {
                            Intent intent = new Intent(CommandeProducteurActivity.this, SignalerActivity.class);
                            intent.putExtra("IDCOMMANDE",finalJsonArrayCommande.getJSONObject(position).getString("IDCOMMANDE"));
                            intent.putExtra("nomProduit",finalJsonArrayCommande.getJSONObject(position).getString("NOMPRODUIT"));
                            intent.putExtra("qtteLivree",finalJsonArrayCommande.getJSONObject(position).getInt("QUANTITE"));
                            Log.d("qtte ", String.valueOf(finalJsonArrayCommande.getJSONObject(position).getInt("QUANTITE")));
                            Log.d("TEST",finalJsonArrayCommande.getJSONObject(position).getString("IDCOMMANDE"));
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
                Log.d("Test", e.getMessage());
            }

        });
    }
}