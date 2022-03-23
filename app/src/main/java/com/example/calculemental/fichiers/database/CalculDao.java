package com.example.calculemental.fichiers.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.calculemental.fichiers.model.Calcul;

public class CalculDao extends BaseDao<Calcul>{
    public CalculDao(DataBaseHelper helper) {
        super(helper);
    }
    static String cleCalcul = "calcul";
    static String cleNbCalculs = "nbCalculs";
    static String cleNbCalculsReussis = "nbCalculsReussis";

    @Override
    protected String getTableName() {
        return "historique";
    }

    @Override
    protected void putValues(ContentValues values, Calcul entity) {
        values.put(cleCalcul,entity.getCalcul());
        values.put(cleNbCalculs,entity.getNbCalculs());
        values.put(cleNbCalculsReussis,entity.getNbCalculsReussis());
    }

    @Override
    protected Calcul getEntity(Cursor cursor) {
        Calcul calcul  =new Calcul();
        cursor.moveToFirst();
        Integer indexCalcul = cursor.getColumnIndex(cleCalcul);
        Integer indexNbCalculs = cursor.getColumnIndex(cleNbCalculs);
        Integer indexNbCalculsReussis = cursor.getColumnIndex(cleNbCalculsReussis);
        calcul.setCalcul(cursor.getString(indexCalcul));
        calcul.setNbCalculs(cursor.getInt(indexNbCalculs));
        calcul.setNbCalculsReussis(cursor.getInt(indexNbCalculsReussis));
        return calcul;
    }
}
