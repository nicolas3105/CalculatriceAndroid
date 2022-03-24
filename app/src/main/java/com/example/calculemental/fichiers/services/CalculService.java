package com.example.calculemental.fichiers.services;


import com.example.calculemental.fichiers.database.CalculDao;
import com.example.calculemental.fichiers.model.Calcul;

public class CalculService {
    private CalculDao calculDao;

    public CalculService(CalculDao calculDao){
        this.calculDao=calculDao;

    }
    public long getScoreCount(){
        return calculDao.count();
    }
    public void storeInDb(Calcul calcul){
        calculDao.create(calcul);
    }

    public void updateInDb(Calcul calcul){
        calculDao.updateQuery(calcul);
    }

    public Calcul getLast() {
        return calculDao.lastOrNull();
    }
}
