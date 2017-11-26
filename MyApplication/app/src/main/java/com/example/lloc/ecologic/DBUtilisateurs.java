package com.example.lloc.ecologic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.security.MessageDigest;

/**
 * Created by lLoïc on 20/10/2017.
 */

public class DBUtilisateurs extends SQLiteOpenHelper {
    public static final String TABLE_NAME="Utilisateurs";
    public static final String col2="user_pseudo";
    public static final String col3="user_password";
    public static final String col4="user_mail";
    public static final String col5="user_score";
    Context context;

    public DBUtilisateurs(Context context){
        super(context,"DB Utilisateurs", null,1);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String creerTable= "CREATE TABLE "+TABLE_NAME+" (user_mail varchar2(30) primary key, user_password varchar2(256),user_pseudo varchar2(20), user_score number);";
        db.execSQL(creerTable);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j){
        db.execSQL("DROP IF TABLE EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean ajout( String pseudo, String mdp,String mail,int score){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();


        contentValues.put(col2,pseudo);
        contentValues.put(col3,mdp);
        contentValues.put(col4,mail);
        contentValues.put(col5,score);
        long result = db.insert(TABLE_NAME,null,contentValues);
// Si la donnée s'est mal insérée, result sera a -1 sinon 0
        if (result==-1){
            long resulte=db.update(TABLE_NAME,contentValues,"user_mail=?",new String[]{mail});
            if (resulte==-1)return false;
            else return true;
        }
        else return true;

    }
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public Cursor getData(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor donnee= database.rawQuery("Select * from "+TABLE_NAME,null);
        return donnee;
    }
    public int getScore(String mail){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor donnee= database.rawQuery("Select user_score from "+TABLE_NAME+" where user_mail='"+mail+"'",null);
        donnee.moveToNext();
        return donnee.getInt(0);

    }
    public Cursor Connect(String mail, String password){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor donnee= database.rawQuery("Select user_pseudo,user_score from "+TABLE_NAME+" where user_mail='"+mail+"' and user_password='"+sha256(password)+"'",null);
        return donnee;
    }
}
