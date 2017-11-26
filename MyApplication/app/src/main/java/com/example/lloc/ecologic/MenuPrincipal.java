package com.example.lloc.ecologic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by lLoïc on 24/10/2017.
 */

public class MenuPrincipal extends AppCompatActivity {

    String mail;
    String joueur;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        TextView textView = (TextView)findViewById(R.id.textView);
        joueur=getIntent().getStringExtra("pseudo");
        mail=getIntent().getStringExtra("mail");
        textView.setText("Content de vous voir, "+joueur+" !");
        Button button=(Button)findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuPrincipal.this,Progression.class);
                intent.putExtra("mail",mail);
                intent.putExtra("pseudo",joueur);
                startActivity(intent);

            }
        });
        Button button1=(Button)findViewById(R.id.button7);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int jeu=random.nextInt(2);
                if(jeu==0) {
                    Intent intent = new Intent(MenuPrincipal.this, QCM.class);
                    intent.putExtra("mail", mail);
                    intent.putExtra("pseudo", joueur);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(MenuPrincipal.this, VraiFaux.class);
                    intent.putExtra("mail", mail);
                    intent.putExtra("pseudo", joueur);
                    startActivity(intent);
                    finish();
                }

            }
        });
        Button settings=(Button) findViewById(R.id.button2);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuPrincipal.this,Parametres.class);
                startActivity(intent);

            }
        });

    }

}
