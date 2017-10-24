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
                    AlertDialog.Builder alert = new AlertDialog.Builder(Connexion.this);
                    alert.setTitle("Information");
                    alert.setMessage("Connecté !");
                    alert.setPositiveButton("OK", null);
                    alert.show();

                    new android.os.Handler().postDelayed(new Runnable(){
                        @Override
                        public void run (){
                            /*Intent intent=new Intent(Connexion.this,MenuPrincipal.class);
                            startActivity(intent);*/
                            finish();
                        }
                    },1000);

                }
                else {


                    AlertDialog.Builder alert = new AlertDialog.Builder(Connexion.this);
                    alert.setTitle("Information");
                    alert.setMessage("Echec de la connexion !");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                }

            }
        });
    }

}
