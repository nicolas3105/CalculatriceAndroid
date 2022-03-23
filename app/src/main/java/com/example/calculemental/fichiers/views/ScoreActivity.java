package com.example.calculemental.fichiers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculemental.R;
import com.example.calculemental.fichiers.database.CalculDao;
import com.example.calculemental.fichiers.database.ComputeBaseHelper;
import com.example.calculemental.fichiers.model.Calcul;
import com.example.calculemental.fichiers.services.CalculService;

public class ScoreActivity extends AppCompatActivity {
    private CalculService calculService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        calculService=new CalculService(new CalculDao(new ComputeBaseHelper(this)));
        Calcul dernierCalcul = calculService.getLast();

        Button boutonPrecedent = findViewById(R.id.button_main);
        boutonPrecedent.setOnClickListener(view -> retourneAuPrecedent());
        TextView textViewLastOperation = findViewById(R.id.textViewLastOperation);
        TextView textViewNbOperations = findViewById(R.id.textViewNbOperations);
        TextView textViewPourcentage = findViewById(R.id.textViewPourcentageReussite);
        Intent intent =getIntent();
        String operation = dernierCalcul.getCalcul();
        int nbCalculs = dernierCalcul.getNbCalculs();
        int nbCalculsReussis = dernierCalcul.getNbCalculsReussis();
        if(operation != null){
            textViewLastOperation.setText(operation);
        }else{
            textViewLastOperation.setText("Vide");
        }
        textViewNbOperations.setText(nbCalculs+"");
        textViewPourcentage.setText(nbCalculsReussis*100/nbCalculs+" %");

    }

    private void retourneAuPrecedent() {
        finish();
    }
}