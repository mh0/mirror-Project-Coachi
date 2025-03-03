package miage.al3c.g4.coachi.GUI.Actions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import miage.al3c.g4.coachi.Animal;
import miage.al3c.g4.coachi.GUI.Accueil.AnimalPerso;
import miage.al3c.g4.coachi.GUI.Guide.GuideSoigner;
import miage.al3c.g4.coachi.R;
import miage.al3c.g4.coachi.Utilisateur;

public class Soigner extends AppCompatActivity {

    Button btnWikiSoigner, btnSoigner;

    private SharedPreferences myPrefs;
    private SharedPreferences.Editor myPrefsEditor;
    private Gson gson = new Gson();
    private Utilisateur utilisateur;
    private Animal animal;

    public Soigner() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTGUI", "onCreate Soigner");
        setContentView(R.layout.activity_soigner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Récupération SharedPreferences
        myPrefs = getBaseContext().getSharedPreferences("Coachi", Context.MODE_PRIVATE);
        myPrefsEditor = myPrefs.edit();

        // Récupérer l'utilisateur
        String json = myPrefs.getString("Utilisateur", "");
        utilisateur = gson.fromJson(json, Utilisateur.class);

        // Récupérer l'animal
        animal = utilisateur.getAnimal();

        //Initialisations Boutons
        btnWikiSoigner = findViewById(R.id.btWikiSoigner);
        btnWikiSoigner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToGuideSoigner;
                goToGuideSoigner = new Intent(Soigner.this, GuideSoigner.class);
                startActivity(goToGuideSoigner);
            }
        });

        btnSoigner = findViewById(R.id.btSoigner);
        btnSoigner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soigner(0,100,-20, "Normal");
            }
        });
    }

    public void soigner(int energie, int sante, int moral, String etat){
        Intent intent = new Intent(Soigner.this, AnimalPerso.class);
        intent.putExtra("Action", "Soigner");
        intent.putExtra("EnergieBonus", energie);
        intent.putExtra("SanteBonus", sante);
        intent.putExtra("MoralBonus", moral);
        intent.putExtra("Etat", etat);
        finish();
        startActivity(intent);
    }

}
