package com.example.lloc.ecologic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lLoïc on 03/11/2017.
 */

public class DBQCM extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "QCM";
    public static final String col1 = "IDquestion";
    public static final String col2 = "intitule";
    public static final String col3 = "reponse1";
    public static final String col4 = "reponse2";
    public static final String col5 = "reponse3";
    public static final String col6 = "reponse4";
    public static final String col7 = "bonnereponse";
    public static final String col8 = "explication";
    public static final String col9 = "score";
    Context context;

    public DBQCM(Context context) {
        super(context, "DB QCM", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creerTable = "CREATE TABLE " + TABLE_NAME + " (IDquestion integer primary key ,intitule varchar(300) , reponse1 varchar(100)," +
                "reponse2 varchar(100), reponse3 varchar(100),reponse4 varchar(100),bonnereponse varchar(100),explication varchar(300)," +
                "score integer);";
        db.execSQL(creerTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean ajoutQuestions(int id, String intitulé, String réponse1, String réponse2, String réponse3, String réponse4, String bonneRéponse, String explication, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col1, id);
        contentValues.put(col2, intitulé);
        contentValues.put(col3, réponse1);
        contentValues.put(col4, réponse2);
        contentValues.put(col5, réponse3);
        contentValues.put(col6, réponse4);
        contentValues.put(col7, bonneRéponse);
        contentValues.put(col8, explication);
        contentValues.put(col9, score);
        long result = db.insert(TABLE_NAME, null, contentValues);
// Si la donnée s'est mal insérée, result sera a -1 sinon 0
        if (result == -1) return false;
        else return true;

    }

    public int getNbrRow() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor donnee = database.rawQuery("Select count(*) from " + TABLE_NAME, null);
        donnee.moveToNext();
        int NbrLignes = donnee.getInt(0);
        return NbrLignes;
    }

    public Question questionFind(int id) {
        Question q = new Question();
        SQLiteDatabase database = this.getReadableDatabase();
        String request = "Select * from " + TABLE_NAME + " where IDquestion=" + id;
        Cursor donnee = database.rawQuery(request, null);


        if (donnee.moveToNext()) {
            q = new Question(
                    id,
                    donnee.getString(1),
                    donnee.getString(2),
                    donnee.getString(3),
                    donnee.getString(4),
                    donnee.getString(5),
                    donnee.getString(6),
                    donnee.getString(7),
                    donnee.getInt(8)
            );

        }
        return q;
    }
}




