package com.example.calculemental.fichiers.database;

import android.content.Context;

public class ComputeBaseHelper extends DataBaseHelper{

    public ComputeBaseHelper(Context context) {
        super(context, "Calcul", 1);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS historique ("+
                "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                CalculDao.cleCalcul + " VARCHAR(255) NOT NULL,"+
                CalculDao.cleNbCalculs + " INTEGER NOT NULL,"+
                CalculDao.cleNbCalculsReussis + " INTEGER NOT NULL"+
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE IF EXISTS historique";
    }
}
