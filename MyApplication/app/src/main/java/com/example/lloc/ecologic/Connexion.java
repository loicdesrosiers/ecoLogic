package com.example.lloc.ecologic;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lLoïc on 23/10/2017.
 */

public class Connexion extends AppCompatActivity {
    DBUtilisateurs db;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        db=new DBUtilisateurs(this);
        Button button = (Button) findViewById(R.id.button11);
        Button inscription=(Button) findViewById(R.id.button27);
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Connexion.this,Inscription.class);
                startActivity(intent);
            }
        });
        final EditText email = (EditText) findViewById(R.id.editText5);
        email.setHintTextColor(Color.WHITE);

        final EditText mot_de_passe= (EditText) findViewById(R.id.editText4);
        mot_de_passe.setHintTextColor(Color.WHITE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString();
                String mdp = mot_de_passe.getText().toString();
                Cursor cursor = db.Connect(mail,mdp);


                if ( cursor.moveToNext()){
                    Toast.makeText(Connexion.this,"Connecté ! ",Toast.LENGTH_SHORT).show();

                    new android.os.Handler().postDelayed(new Runnable(){
                        @Override
                        public void run (){
                            Intent intent=new Intent(Connexion.this,MenuPrincipal.class);
                            startActivity(intent);
                            finish();
                        }
                    },1000);

                }
                else {


                    Toast.makeText(Connexion.this,"Echec, veuillez verifier vos données. Etes-vous enregistré ?",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
