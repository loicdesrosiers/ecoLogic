package com.example.lloc.ecologic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

/**
 * Created by lLoïc on 24/10/2017.
 */

public class MenuPrincipal extends AppCompatActivity {
    int NbrJeux;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        Button button=(Button)findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(MenuPrincipal.this,Progression.class);
                startActivity(intent);*/
            }
        });
        Button button1=(Button)findViewById(R.id.button7);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random =new Random();
                int jeu=random.nextInt()%NbrJeux;
                /*if (jeu==1){
                    Intent intent=new Intent(MenuPrincipal.this,Jeu1.class);
                startActivity(intent);
                }
                if (jeu==2){
                    Intent intent=new Intent(MenuPrincipal.this,Jeu2.class);
                startActivity(intent);
                }*/

            }
        });

    }
}
