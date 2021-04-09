package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

        Button btnClients = (Button) findViewById(R.id.btnParClient);
        btnClients.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnClients) {
                try {
                    lesCommandes("clients");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnProduits = (Button) findViewById(R.id.btnParProduit);
        btnProduits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnProduits) {
                try {
                    lesCommandes("produits");
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
        RequestBody formBody = new FormBody.Builder()
                .add("idProducteur", idUser)
                .add("tri", tri)
                .build();
        Request request = new Request.Builder()
                .url("http://169.254.78.78/ppe_biorelai/controleurs/lesCommandes.php")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();
                Log.d("Test", responseStr);
                if (responseStr.compareTo("false") != 0) {
                    try {
                        JSONObject lesCommandes = new JSONObject(responseStr);

                        if (lesCommandes != null) {

                        }
                        else{
                            //Afficher "Aucune commande de prévue aujourd'hui"
                        }
                    }catch(JSONException e){
                        Log.d("Test",e.getMessage());
                    }
                }
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
                Log.d("Test", e.getMessage());
            }

        });
    }
}