package com.example.lloc.ecologic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lLoïc on 21/11/2017.
 */

public class DBVraiFaux extends SQLiteOpenHelper {
    public static final String TABLE_NAME="VraiFaux";

    public static final String col2="intitule";
    public static final String col3="reponse";
    public static final String col4="score";
    Context context;

    public DBVraiFaux(Context context) {
        super(context, "DBVraiFaux", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creerTable="create table "+TABLE_NAME+" (IDq integer primary key autoincrement, intitule varchar(300), reponse varchar(10),"
                + " score integer);";
        db.execSQL(creerTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public int getNbrRow() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor donnee = database.rawQuery("Select count(*) from " + TABLE_NAME, null);
        donnee.moveToNext();
        int NbrLignes = donnee.getInt(0);
        return NbrLignes;
    }
    public boolean ajoutVraiFaux(String intitulé, String réponse, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, intitulé);
        contentValues.put(col3, réponse);
        contentValues.put(col4, score);

        long result = db.insert(TABLE_NAME, null, contentValues);
// Si la donnée s'est mal insérée, result sera a -1 sinon 0
        if (result == -1) return false;
        else return true;

    }
    public QuestionVraiFaux questionFind(int id) {
        QuestionVraiFaux q = new QuestionVraiFaux();
        SQLiteDatabase database = this.getReadableDatabase();
        String request = "Select * from " + TABLE_NAME + " where IDq=" + id;
        Cursor donnee = database.rawQuery(request, null);


        if (donnee.moveToNext()) {
            q = new QuestionVraiFaux(
                    donnee.getString(1),
                    donnee.getString(2),
                    donnee.getInt(3)
            );

        }
        return q;
    }
}
