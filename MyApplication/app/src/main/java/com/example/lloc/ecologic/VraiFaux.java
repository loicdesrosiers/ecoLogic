package com.example.lloc.ecologic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by lLoïc on 20/11/2017.
 */

public class VraiFaux extends AppCompatActivity {
    Random random;
    TextView enonce,numQuestion;
    DBVraiFaux dbVraiFaux=new DBVraiFaux(this);
    int j=0;
    int NuméroQuestion;
    String mail,joueur;
    Button vrai,faux;
    int scoreTotal=0;
    QuestionVraiFaux question;
    String UPDATE_URL="http://ecologic-lyon1.fr/test/updateBDUsers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vraifaux);
        mail=getIntent().getStringExtra("mail");
        joueur=getIntent().getStringExtra("pseudo");

        enonce=(TextView) findViewById(R.id.lbl_enonce1);
        numQuestion=(TextView) findViewById(R.id.lbl_noquestion1);
        vrai=(Button) findViewById(R.id.Vrai);
        faux=(Button) findViewById(R.id.Faux);
        random=new Random(System.currentTimeMillis());
        Jeu(j);

    }
    public void Jeu(int j){
        if(j<5) {
            NuméroQuestion = j;
            int idQuestion = random.nextInt(dbVraiFaux.getNbrRow()) + 1;
            question = dbVraiFaux.questionFind(idQuestion);
            enonce.setText(question.getIntitule());
            numQuestion.setText("Question N°" + (j + 1));
            vrai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkReponse("Vrai",question.getReponse());
                }
            });
            faux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkReponse("Faux",question.getReponse());
                }
            });

        }else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(VraiFaux.this);
            alertDialog.setTitle("Information !");
            alertDialog.setMessage("Fin du jeu, vous avez gagné "+scoreTotal+" points !");
            alertDialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    updateBDUser(mail,scoreTotal);
                    Intent intent=new Intent(VraiFaux.this,MenuPrincipal.class);
                    intent.putExtra("mail",mail);
                    intent.putExtra("pseudo",joueur);

                    startActivity(intent);
                    finish();
                }
            });
            alertDialog.show();
        }
    }
    public void checkReponse(String reponseUtilisateur,String bonneReponse){
        if (reponseUtilisateur.equals(bonneReponse)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(VraiFaux.this);
            alertDialog.setTitle("Bien joué !");
            alertDialog.setMessage("Vous avez gagné " + question.getScore() + " points !");
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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(VraiFaux.this);
            alertDialog.setTitle("Mauvaise réponse !");
            alertDialog.setMessage("Dommage");
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

