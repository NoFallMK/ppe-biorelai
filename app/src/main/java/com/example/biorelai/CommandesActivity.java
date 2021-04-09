package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommandesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);
    }

    public void afficherCommandes() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://nofall.fr/ppe-biorelai/commandes.php")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Test", "Erreur!!! Connexion impossible");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseStr = response.body().string();
                JSONArray jsonArrayCommandes = null;
                try{
                    jsonArrayCommandes = new JSONArray(responseStr);
                    for(int i = 0; i < jsonArrayCommandes.length(); i++){
                        JSONObject jsonClasse = null;
                        jsonClasse = jsonArrayCommandes.getJSONObject(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ListView listViewCommandes = findViewById(R.id.listViewCommandes);

                ArrayAdapter<String> arrayAdapterCommandes = new ArrayAdapter<String>(CommandesActivity.this, android.R.layout.simple_list_item_1);
                listViewCommandes.setAdapter(arrayAdapterCommandes);
            }
        });
    }
}