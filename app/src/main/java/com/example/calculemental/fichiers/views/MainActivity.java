package com.example.calculemental.fichiers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.calculemental.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutonJeu = findViewById(R.id.nouvelPartieBouton);
        Button boutonScore = findViewById(R.id.scoreBoutton);
        boutonJeu.setOnClickListener(view -> ouvreActiviteJeu());
        boutonScore.setOnClickListener(view -> ouvreActiviteScores());
    }

    private void ouvreActiviteScores() {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    private void ouvreActiviteJeu() {
        Intent intent = new Intent(this, PartieEnCoursActivity.class);
        startActivity(intent);
    }
}