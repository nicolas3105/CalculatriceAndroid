package com.example.calculemental.fichiers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculemental.R;
import com.example.calculemental.fichiers.database.CalculDao;
import com.example.calculemental.fichiers.database.ComputeBaseHelper;
import com.example.calculemental.fichiers.exceptions.ValeurNullException;
import com.example.calculemental.fichiers.model.Calcul;
import com.example.calculemental.fichiers.services.CalculService;
import com.example.calculemental.fichiers.services.GenerationCalculService;
import com.example.calculemental.fichiers.services.VerificationCalculService;

import java.util.Locale;

public class PartieEnCoursActivity extends AppCompatActivity {

    private int nbCalculsBons=0;
    private int nbCalculs=0;
    private CalculService calculService;
    private String stringCalcul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie_en_cours);
        calculService = new CalculService(new CalculDao(new ComputeBaseHelper(this)));
        Button boutonPrecedent = findViewById(R.id.bouton_main);
        boutonPrecedent.setOnClickListener(view -> retourneAuPrecedent());
        LancerPartie();
        Button boutonSuivant = findViewById(R.id.button_suivant);
        boutonSuivant.setOnClickListener(view -> LancerPartie());

    }

    private void VerificationDuCalcul(int premierNombre,int deuxiemeNombre, String symbol){
        try {
            EditText editTextReponse = findViewById(R.id.reponseEditText);
            if ( editTextReponse.getText().length()==0) {
                throw new ValeurNullException();
            }
            String reponseString = editTextReponse.getText().toString();
            editTextReponse.setEnabled(false);
            int reponse = Integer.parseInt(reponseString);
            VerificationCalculService verifCalcul = new VerificationCalculService(premierNombre, deuxiemeNombre, symbol, reponse);


            TextView textViewReponse = findViewById(R.id.SuccesTextView);

            if (verifCalcul.getResultat() == reponse) {
                textViewReponse.setText(R.string.calcul_vrai);
                textViewReponse.setTextColor(getResources().getColor(R.color.calcul_vrai));
                nbCalculsBons++;
                stringCalcul = premierNombre+symbol+deuxiemeNombre;
            } else {
                textViewReponse.setText(R.string.calcul_faux);
                textViewReponse.setTextColor(getResources().getColor(R.color.calcul_faux));

            }

            Calcul calcul = new Calcul();
            calcul.setCalcul(stringCalcul);
            calcul.setNbCalculs(nbCalculs);
            calcul.setNbCalculsReussis(nbCalculsBons);
            calculService.storeInDb(calcul);
            calculService.updateInDb(calcul);

        }
        catch (ValeurNullException exception){
            Toast.makeText(this, R.string.toast_calcul,Toast.LENGTH_LONG).show();
        }
    }

    private void LancerPartie(){
        GenerationCalculService generationCalcul = new GenerationCalculService();
        int nombreUn = generationCalcul.getPremierNombre();
        int nombreDeux = generationCalcul.getDeuxiemeNombre();
        String symbol = generationCalcul.getSymbol();
        nbCalculs++;
        TextView textViewCalculAEffectuer = findViewById(R.id.CalculTextView);
        TextView textViewReponse = findViewById(R.id.SuccesTextView);
        textViewReponse.setText("");
        textViewCalculAEffectuer.setText(nombreUn + " " + symbol + " " + nombreDeux);
        Button verification = findViewById(R.id.button_valider);
        EditText editTextReponse = findViewById(R.id.reponseEditText);
        editTextReponse.setEnabled(true);
        editTextReponse.setText("");
        verification.setOnClickListener(view -> VerificationDuCalcul(nombreUn, nombreDeux, symbol));


    }

    private boolean ViderCalculs(){
        nbCalculsBons=0;
        nbCalculs=0;
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar,menu);
        MenuItem itemScores= menu.findItem(R.id.bouton_scores);
        MenuItem itemVider= menu.findItem(R.id.bouton_vider);
        itemScores.setOnMenuItemClickListener(menuItem -> ouvreActiviteScores());
        itemVider.setOnMenuItemClickListener(menuItem -> ViderCalculs());
        return super.onCreateOptionsMenu(menu);
    }

    private void retourneAuPrecedent() {
        finish();
    }

    private boolean ouvreActiviteScores() {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
        return true;
    }
}