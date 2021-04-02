package com.example.biorelai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.biorelai.MainActivity.idUser;

public class ClientActivity extends AppCompatActivity {

    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        try {
            final JSONObject user = new JSONObject(getIntent().getStringExtra("user"));
            final TextView textIdentification = findViewById(R.id.txtClient);
            String txtUser = "Bienvenue " + user.getString("NOMUTILISATEUR") + " " + user.getString("PRENOMUTILISATEUR") + " !";
            textIdentification.setText(txtUser);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            commandeDuJour();
        }catch (IOException e) {
            e.printStackTrace();
        }


        Button btnDeconnexion = findViewById(R.id.btnDecoClient);
        btnDeconnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnDeconnexion) {
                Intent intent1 = new Intent(ClientActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }


    public void commandeDuJour() throws IOException {
        ArrayList ArrayListLigneCommande = new ArrayList();
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(now);
        RequestBody formBody = new FormBody.Builder()
                .add("idAdherent",idUser)
                .add("date",strDate)
                .build();

        Request request = new Request.Builder()
                .url("http://172.19.228.252/ppe_Biorelai/controleurs/commandeDuJour.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();

                if (responseStr.compareTo("false") != 0) {
                    try {

                        JSONArray jsonArrayLaCommande = new JSONArray(responseStr);


                        for(int i=0;i<jsonArrayLaCommande.length();i++){
                            JSONObject ligneCommande = jsonArrayLaCommande.getJSONObject(i);






                        }


                    }catch(JSONException e){
                        // Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
                        Log.d("Test",e.getMessage());
                    }
                } else {
                    //Toast.makeText(MainActivity.this, "Erreur ,Login ou mot de  passe incorrect !", Toast.LENGTH_SHORT).show();

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