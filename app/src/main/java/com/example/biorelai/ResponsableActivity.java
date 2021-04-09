package com.example.biorelai;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ResponsableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsable);
        try {
            final JSONObject user = new JSONObject(getIntent().getStringExtra("user"));
            final TextView textIdentification = findViewById(R.id.txtResponsable);
            String txtUser = "Bienvenue " + user.getString("NOMUTILISATEUR") + " " + user.getString("PRENOMUTILISATEUR") + " !";
            textIdentification.setText(txtUser);

        } catch (
                JSONException e) {
            e.printStackTrace();
        }
        Button btnDeconnexion = (Button) findViewById(R.id.btnDeconnexion);
        btnDeconnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btnDeconnexion) {
                Intent intent1 = new Intent(ResponsableActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        Button btnAfficherCommandes = (Button) findViewById(R.id.btnAfficherCommandes);
        btnAfficherCommandes.setOnClickListener(new View.OnClickListener(){
           public void onClick(View btnAfficherCommandes){
               Intent commandes = new Intent(ResponsableActivity.this, CommandesActivity.class);
               startActivity(commandes);
           }
        });
    }
}