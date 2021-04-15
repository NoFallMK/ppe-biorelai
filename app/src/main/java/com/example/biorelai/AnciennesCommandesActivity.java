package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class AnciennesCommandesActivity extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anciennes_commandes);

        try{
            lesAnciennesCommandes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button btnRetour = (Button) findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnRetour) {
                Intent intentRetour = new Intent(AnciennesCommandesActivity.this, ProducteurActivity.class);
                startActivity(intentRetour);
            }
        });
    }

    public void lesAnciennesCommandes() throws IOException {
        final ArrayList arrayListLesAnciennesCommandes= new ArrayList<String>();
        RequestBody formBody = new FormBody.Builder()
                .add("producteur", idUser)
                .build();
        Log.d("test1", idUser);

        Request request = new Request.Builder()
                .url("http://169.254.78.78/ppe_biorelai/controleurs/lesAnciennesCommandes.php")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();
                JSONArray jsonArrayLesAnciennesCommandes = null;
                Log.d("ancvienns", responseStr);
                try {

                    jsonArrayLesAnciennesCommandes = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayLesAnciennesCommandes.length(); i++) {
                        JSONObject jsonlaCommande = jsonArrayLesAnciennesCommandes.getJSONObject(i);
                        arrayListLesAnciennesCommandes.add("Commande N°" + jsonlaCommande.getString("IDCOMMANDE") + " \r" + "Client : " + jsonlaCommande.getString("mail") +  " \r Date: " + jsonlaCommande.getString("DATECOMMANDE"));
                        Log.d("laCommande", jsonlaCommande.toString());
                    }
                } catch (JSONException e) {
                    Log.d("Test", e.getMessage());
                }

                ListView listViewLesAnciennesCommandes = findViewById(R.id.listeLesAnciennesCommandes);


                ArrayAdapter<String> arrayAdapterLesAnciennesCommandes = new ArrayAdapter<String>(AnciennesCommandesActivity.this, android.R.layout.simple_list_item_1, arrayListLesAnciennesCommandes);
                runOnUiThread(() -> {
                    listViewLesAnciennesCommandes.setAdapter(arrayAdapterLesAnciennesCommandes);
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