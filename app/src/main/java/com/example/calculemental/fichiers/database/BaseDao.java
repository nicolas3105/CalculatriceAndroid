package com.example.calculemental.fichiers.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.calculemental.fichiers.model.BaseEntity;
import com.example.calculemental.fichiers.model.Calcul;

import java.util.ArrayList;
import java.util.List;

public abstract  class BaseDao<T extends BaseEntity> {
    private final DataBaseHelper dbHelper;

    public BaseDao(DataBaseHelper helper){
        this.dbHelper = helper;
    }

    protected abstract String getTableName();
    protected abstract void putValues(ContentValues values, T entity);
    protected abstract T getEntity(Cursor cursor);

    /**
     * @param entity : element a ajouter dans la base
     * @return : l element créait avec son ID
     */
    public T create(T entity){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        putValues(values, entity);

        long newRowId = db.insert(getTableName(), null, values);
        entity.id = newRowId;
        return entity;
    }

    protected List<T> query(String selection, String[] selectionArgs, String sortOrder){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                getTableName(),
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List items = new ArrayList<T>();
        while(cursor.moveToNext()) {
            items.add(getEntity(cursor));

        }
        cursor.close();

        return items;
    }


    public T lastOrNull() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor =db.query(
                getTableName(),
                null,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToLast();
        T last = this.getEntity(cursor);
        cursor.close();

        return last;
    }


    public long count() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select count(*) from "+getTableName(), null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();

        return count;
    }

    public void updateQuery(T values){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        putValues(cv,values);
        db.update(getTableName(),cv,null,null);
    }

    public void drop(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(getDeleteSql());
        db.execSQL(getCreationSql());
    }

    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS historique ("+
                "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                CalculDao.cleCalcul + " VARCHAR(255) NOT NULL,"+
                CalculDao.cleNbCalculs + " INTEGER NOT NULL,"+
                CalculDao.cleNbCalculsReussis + " INTEGER NOT NULL"+
                ")";
    }

    protected String getDeleteSql(){return "DROP TABLE IF EXISTS historique";};
}