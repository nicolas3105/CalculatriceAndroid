package com.example.calculemental.fichiers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculemental.R;
import com.example.calculemental.fichiers.database.CalculDao;
import com.example.calculemental.fichiers.database.ComputeBaseHelper;
import com.example.calculemental.fichiers.exceptions.ValeurNullException;
import com.example.calculemental.fichiers.model.Calcul;
import com.example.calculemental.fichiers.services.CalculService;

public class ScoreActivity extends AppCompatActivity {
    private CalculService calculService;
    private String operation;
    private int nbCalculs;
    private int nbCalculsReussis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        calculService=new CalculService(new CalculDao(new ComputeBaseHelper(this)));
        Button boutonPrecedent = findViewById(R.id.button_main);
        boutonPrecedent.setOnClickListener(view -> retourneAuPrecedent());
        TextView textViewLastOperation = findViewById(R.id.textViewLastOperation);
        TextView textViewNbOperations = findViewById(R.id.textViewNbOperations);
        TextView textViewPourcentage = findViewById(R.id.textViewPourcentageReussite);
        try {
            if(calculService.getScoreCount()==0){
                throw new ValeurNullException();
            }
            Calcul dernierCalcul = calculService.getLast();
            operation = dernierCalcul.getCalcul();
            nbCalculs = dernierCalcul.getNbCalculs();
            nbCalculsReussis = dernierCalcul.getNbCalculsReussis();
        }
        catch (ValeurNullException exception){
            operation = "Vide";
            nbCalculs = 0;
            nbCalculsReussis = 0;
        }
        textViewLastOperation.setText(operation);
        textViewNbOperations.setText(nbCalculs+"");
        if(nbCalculs==0){
            textViewPourcentage.setText("0 %");
        }else{
            textViewPourcentage.setText(nbCalculsReussis*100/nbCalculs+" %");
        }
    }

    private void retourneAuPrecedent() {
        finish();
    }
}