package com.example.lloc.ecologic;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class SplashScreen extends AppCompatActivity {

    String JSON_String;
    private static int SPLASH_TIME_OUT = 6000;
    DBUtilisateurs dbUtilisateurs;
    DBQCM dbqcm;
    DBVraiFaux dbVraiFaux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        dbqcm=new DBQCM(this);
        dbUtilisateurs=new DBUtilisateurs(this);
        dbVraiFaux=new DBVraiFaux(this);

        if(isOnline()){

            ProgressBar pb = (ProgressBar) findViewById(R.id.progressbar);


            pb.getIndeterminateDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            Intent startMusic=new Intent(this,MusicController.class);
            startService(startMusic);
            chargerQuestions();
            chargerBD();
            chargerVraiFaux();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashScreen.this,Connexion.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }else{
    AlertDialog.Builder alertDialog=new AlertDialog.Builder(SplashScreen.this);
    alertDialog.setTitle("Error");
    alertDialog.setMessage("Verifiez votre connexion internet");
    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            finish();
        }
    });
    alertDialog.show();


        }

    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void chargerQuestions(){
        new ChargerQuestions().execute();
    }
    class ChargerQuestions extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute(){
            json_url="http://ecologic-lyon1.fr/test/GetQCM.php";

        }
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url =new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();

                while((JSON_String=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_String);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result){
            RemplirBDQuestions(result);

            }
        }

    public void RemplirBDQuestions(String données){

        String[] lignes = données.split("NEWLINE");

        for (String ligne:lignes) {

            if(ligne.length()<3){
                // On teste si la donnée est nulle ou pas. Plante si test absent
                // car le script en arivant aux valeurs de fin rajoute une ligne nulle



            }else{
                // On sépare les résultats car ils sont sous la forme Nom_Pseudo_Mdp_Mail
                //On ajoute les résultats a la BD Locale

               String s[] = ligne.split("_");

                AjouterQuestions(s[1],s[2],s[3],s[4],s[5],s[6],s[7],Integer.parseInt(s[8]));
            }


        }
        }
    public void AjouterQuestions(String intitule, String reponse1, String reponse2, String reponse3, String reponse4, String bonneReponse,String explication,int score){
         dbqcm.ajoutQuestions(intitule,reponse1,reponse2,reponse3,reponse4,bonneReponse,explication,score);

    }
    public void chargerVraiFaux(){new ChargerVraiFaux().execute();}
    class ChargerVraiFaux extends AsyncTask<Void,Void,String>{

        String json_url;
        @Override
        protected void onPreExecute(){
            json_url="http://ecologic-lyon1.fr/test/getVraiFaux.php";

        }
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url =new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();

                while((JSON_String=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_String);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result){
            RemplirVraiFaux(result);

        }
    }
    public void RemplirVraiFaux(String données){
        String[] lignes = données.split("NEWLINE");

        for (String ligne:lignes) {

            if(ligne.length()<3){
                // On teste si la donnée est nulle ou pas. Plante si test absent
                // car le script en arivant aux valeurs de fin rajoute une ligne nulle



            }else{
                // On sépare les résultats car ils sont sous la forme Nom_Pseudo_Mdp_Mail
                //On ajoute les résultats a la BD Locale

                String s[] = ligne.split("_");

                AjouterVraiFaux(s[1],s[2],Integer.parseInt(s[3]));
            }


        }
    }
    public void AjouterVraiFaux(String intitule,String reponse, int score){
    dbVraiFaux.ajoutVraiFaux(intitule,reponse,score);
    }
    public void chargerBD(){
        new BackgroundTask().execute();
    }
    class BackgroundTask extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute(){
            json_url="http://ecologic-lyon1.fr/test/getUsersData.php";

        }
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url =new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();

                while((JSON_String=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_String);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result){
            RemplirBD(result);

        }
    }

    public void RemplirBD(String données){

        String[] lignes = données.split("NEWLINE");

        for (String ligne:lignes) {

            if(ligne.length()<3){
                // On teste si la donnée est nulle ou pas. Plante si test absent
                // car le script en arivant aux valeurs de fin rajoute une ligne nulle



            }else{
                // On sépare les résultats car ils sont sous la forme Nom_Pseudo_Mdp_Mail
                //On ajoute les résultats a la BD Locale
                String s[] = ligne.split("_");
                    AjouterDonnées(s[1],s[2],s[0],Integer.parseInt(s[3]));
            }


        }
    }
    public void AjouterDonnées(String pseudo, String mdp, String mail,int score){
        dbUtilisateurs.ajout(pseudo,mdp,mail,score);

    }

}
