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

public class SplashScreen extends AppCompatActivity {

    String JSON_String;
    private static int SPLASH_TIME_OUT = 6000;
    DBUtilisateurs dbUtilisateurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        if(isOnline()){

            ProgressBar pb = (ProgressBar) findViewById(R.id.progressbar);
            dbUtilisateurs=new DBUtilisateurs(this);
            pb.getIndeterminateDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            chargerBD();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

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

    public void chargerBD(){
        new BackgroundTask().execute();}
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

            String[] lignes = result.split("NEWLINE");

            for (String ligne:lignes){
                String s[]=ligne.split("_");


                    Toast.makeText(SplashScreen.this,s[0],Toast.LENGTH_SHORT).show();
                    //Affiche informations par informations





            }
        }
    }

}
