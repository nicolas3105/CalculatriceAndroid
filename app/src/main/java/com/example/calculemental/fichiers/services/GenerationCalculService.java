package com.example.calculemental.fichiers.services;

import com.example.calculemental.fichiers.model.TypeOperationEnum;

import java.util.Random;

public class GenerationCalculService {

    private String Symbol;
    private int PremierNombre;
    private int DeuxiemeNombre;

    private void GeneratePremierNombre (){
        Random nbRandom = new Random();
        PremierNombre = nbRandom.nextInt(49)+1;
    }

    private void GenerateDeuxiemeNombre (){
        Random nbRandom = new Random();
        DeuxiemeNombre = nbRandom.nextInt(49)+1;
    }

    private void GenerateSymbol (){
        Random nbRandom = new Random();
        int nombreSymbol = nbRandom.nextInt(4);
        if(nombreSymbol==1){
            Symbol= TypeOperationEnum.ADD.getSymbol();
        }
        else if(nombreSymbol==2){
            Symbol= TypeOperationEnum.DIVIDE.getSymbol();
        }
        else if(nombreSymbol==3){
            Symbol= TypeOperationEnum.MULTIPLY.getSymbol();
        }
        else{
            Symbol= TypeOperationEnum.SUBSTRACT.getSymbol();
        }
    }

    public String getSymbol() {
        GenerateSymbol();
        return Symbol;
    }

    public int getPremierNombre() {
        GeneratePremierNombre();
        return PremierNombre;
    }

    public int getDeuxiemeNombre() {
        GenerateDeuxiemeNombre();
        return DeuxiemeNombre;
    }


}
