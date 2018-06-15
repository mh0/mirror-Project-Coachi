package miage.al3c.g4.coachi.GUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;

import miage.al3c.g4.coachi.Animal;
import miage.al3c.g4.coachi.GUI.Guide.GuideNourir;
import miage.al3c.g4.coachi.R;
import miage.al3c.g4.coachi.Utilisateur;

public class Nourir extends AppCompatActivity {

    Button btnWikiNourir, btnNourir;
    ImageButton btnNourrir1, btnNourrir2, btnNourrir3;

    private SharedPreferences myPrefs;
    private SharedPreferences.Editor myPrefsEditor;
    private Gson gson = new Gson();
    private Utilisateur utilisateur;
    private Animal animal;

    public Nourir() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTGUI", "onCreate Nourir");
        setContentView(R.layout.activity_nourir);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Récupération SharedPreferences
        myPrefs = getSharedPreferences("Coachi", Context.MODE_PRIVATE);
        myPrefsEditor = myPrefs.edit();

        // Récupérer l'utilisateur
        String json = myPrefs.getString("Utilisateur", "");
        utilisateur = gson.fromJson(json, Utilisateur.class);

        // Récupérer l'animal
        animal = utilisateur.getAnimal();

        //Initialisations Boutons
        btnWikiNourir = findViewById(R.id.btWikiNourir);
        btnWikiNourir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToGuideNourir;
                goToGuideNourir = new Intent(Nourir.this, GuideNourir.class);
                startActivity(goToGuideNourir);
            }
        });

        btnNourrir1 = findViewById(R.id.ibtNourir1);
        btnNourrir1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nourir(1, 1, 1);
            }
        });

        btnNourrir2 = findViewById(R.id.ibtNourir2);
        btnNourrir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nourir(2, 2, 2);
            }
        });

        btnNourrir3 = findViewById(R.id.ibtNourir3);
        btnNourrir3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nourir(3,3,3);
            }
        });
    }

    public void nourir(int energie, int sante, int moral){
        Intent intent = new Intent(Nourir.this, AnimalPerso.class);
        intent.putExtra("Action", "Nourir");
        intent.putExtra("EnergieBonus", energie);
        intent.putExtra("SanteBonus", sante);
        intent.putExtra("MoralBonus", moral);
        finish();
        startActivity(intent);
    }

}
