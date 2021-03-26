package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;
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

public class MainActivity extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonValiderAuthentification = (Button)findViewById(R.id.btnValider);
        buttonValiderAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appel de la function authentification
                try {
                    authentification();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btnQuitter).setOnClickListener(v -> {finishAffinity(); });
    }


    public void authentification() throws IOException {
        final EditText textLogin = findViewById(R.id.txtLogin);
        final EditText textMdp = findViewById(R.id.txtPassword);

        RequestBody formBody = new FormBody.Builder()
        .add("login", textLogin.getText().toString())
        .add("mdp",  textMdp.getText().toString())
        .build();
        Request request = new Request.Builder()
        .url("http://172.19.228.252/ppe_Biorelai/controleurs/authentification.php")
        .post(formBody)
        .build();
        Log.d("Test", textLogin.getText().toString()+textMdp.getText().toString());
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();

                if (responseStr.compareTo("false") != 0) {
                    try {
                        JSONObject user = new JSONObject(responseStr);

                        if (user.getString("STATUT").compareTo("adherent") == 0) {
                            Intent intent = new Intent(MainActivity.this, ClientActivity.class);
                            intent.putExtra("user", user.toString());
                            startActivity(intent);
                        }else if (user.getString("STATUT").compareTo("producteur") == 0) {
                            Intent intent = new Intent(MainActivity.this, ProducteurActivity.class);
                            intent.putExtra("user", user.toString());
                            startActivity(intent);
                        }else if (user.getString("STATUT").compareTo("responsable") == 0) {
                            Intent intent = new Intent(MainActivity.this, ResponsableActivity.class);
                            intent.putExtra("user", user.toString());
                            startActivity(intent);
                        }
                    }catch(JSONException e){
                        // Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
                        Log.d("Test",e.getMessage());
                    }
                } else {
                    Log.d("Test","Login ou mot de  passe non valide !");
                }
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
            }

        });
    }
}

