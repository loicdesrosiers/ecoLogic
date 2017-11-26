package com.example.lloc.ecologic;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import java.util.Random;

/**
 * Created by lLoïc on 05/11/2017.
 */

public class QCM extends AppCompatActivity {
    List<Button> tabboutons=new ArrayList<Button>();
    ArrayList <String>reponsesPossibles=new ArrayList<String>();

    Random random;
    TextView enonce,numQuestion;
    DBQCM dbqcm=new DBQCM(this);
    int j=0;
     String mail;
    String joueur;
    int scoreTotal=0;
    String UPDATE_URL="http://ecologic-lyon1.fr/test/updateBDUsers.php";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qcm);
        mail=getIntent().getStringExtra("mail");
        joueur=getIntent().getStringExtra("pseudo");

        enonce=(TextView) findViewById(R.id.lbl_enonce);
        tabboutons.add((Button) findViewById(R.id.b_reponse1));
        tabboutons.add((Button) findViewById(R.id.b_reponse2));
        tabboutons.add((Button) findViewById(R.id.b_reponse3));
        tabboutons.add((Button) findViewById(R.id.b_reponse4));
        numQuestion=(TextView) findViewById(R.id.lbl_noquestion);
        random=new Random(System.currentTimeMillis());
        Jeu(j);




    }
    public void Jeu(int j){
        if(j<5) {
            final int NuméroQuestion = j;
            int idQuestion = random.nextInt(dbqcm.getNbrRow()) + 1;
            final Question question = dbqcm.questionFind(idQuestion);
            reponsesPossibles.add(question.getRep1());
            reponsesPossibles.add(question.getRep2());
            reponsesPossibles.add(question.getRep3());
            reponsesPossibles.add(question.getRep4());
            for (int i = 0; i < tabboutons.size(); i++) {
                final int button = i;
                int choix = random.nextInt(reponsesPossibles.size());
                String choixRéponse = reponsesPossibles.get(choix);
                tabboutons.get(i).setText(choixRéponse);
                reponsesPossibles.remove(choixRéponse);

                tabboutons.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if ((question.getReponse().equals(tabboutons.get(button).getText()))) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(QCM.this);
                            alertDialog.setTitle("Bien joué !");
                            alertDialog.setMessage(question.getExplication() + ", vous avez gagné " + question.getScore() + " points !");
                            scoreTotal+=question.getScore();
                            alertDialog.setCancelable(false);
                            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Jeu(NuméroQuestion + 1);
                                }
                            });
                            alertDialog.show();
                        } else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(QCM.this);
                            alertDialog.setTitle("Mauvaise réponse !");
                            alertDialog.setMessage(question.getExplication());
                            alertDialog.setCancelable(false);
                            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Jeu(NuméroQuestion + 1);
                                }
                            });
                            alertDialog.show();
                        }
                    }
                });

            }
            enonce.setText(question.getIntitule());
            numQuestion.setText("Question N°" + (j + 1));

        }else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(QCM.this);
            alertDialog.setTitle("Information !");
            alertDialog.setMessage("Fin du jeu, vous avez gagné "+scoreTotal+" points !");
            alertDialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    updateBDUser(mail,scoreTotal);
                    Intent intent=new Intent(QCM.this,MenuPrincipal.class);
                    intent.putExtra("mail",mail);
                    intent.putExtra("pseudo",joueur);

                    startActivity(intent);
                    finish();
                }
            });
            alertDialog.show();
        }
    }

    public void updateBDUser(String mail,int scoreTotal){
        String urlsuffix="?email="+mail+"&score="+scoreTotal;
        class UpdateUser extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();

            }


            @Override
            protected String doInBackground(String... params) {
                String s =params[0];

                BufferedReader bufferReader=null;
                try{

                    URL url = new URL(UPDATE_URL+s);
                    HttpURLConnection con =(HttpURLConnection) url.openConnection();
                    bufferReader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();

                    return result;

                }catch (Exception e ){
                    Toast.makeText(getApplicationContext(),"Erreur ",Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);

            }
        }
        UpdateUser ur =new UpdateUser();
        ur.execute(urlsuffix);

    }
}
