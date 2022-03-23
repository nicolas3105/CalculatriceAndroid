package com.example.calculemental.fichiers.services;

import com.example.calculemental.fichiers.model.TypeOperationEnum;

public class VerificationCalculService {

    private int resultat;
    private boolean calculCorrect=false;
    
    public VerificationCalculService(int premierNombre, int deuxiemeNombre, String symbol,int resultatEntree) {
        CalculResultat(premierNombre,deuxiemeNombre,symbol);
        VerifResultat(resultatEntree);
    }

    private void CalculResultat(int premierNombre, int deuxiemeNombre, String symbol){
        switch (symbol){
            case "+":
                resultat=premierNombre+deuxiemeNombre;
                break;

            case "-":
                resultat=premierNombre-deuxiemeNombre;
                break;
            case "/":
                resultat=premierNombre/deuxiemeNombre;
                break;
            case "x":
                resultat=premierNombre*deuxiemeNombre;
                break;
            default:
                resultat=0;
                break;
        }
    }

    private void VerifResultat(int nombre){
        if(resultat==nombre){
            calculCorrect=true;
        }
        else{
            calculCorrect=false;
        }
    }

    public int getResultat() {
        return resultat;
    }

    public boolean isCalculCorrect() {
        return calculCorrect;
    }
}
