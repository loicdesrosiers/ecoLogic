package com.example.lloc.ecologic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by lLoïc on 05/11/2017.
 */

public class Progression extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progression);
        spinner=(Spinner) findViewById(R.id.spinner);
        adapter =ArrayAdapter.createFromResource(this,R.array.Titres,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

       String mail=getIntent().getStringExtra("mail");
       String joueur=getIntent().getStringExtra("pseudo");
        DBUtilisateurs dbUtilisateurs=new DBUtilisateurs(this);
       int score= dbUtilisateurs.getScore(mail);
        TextView pseudo=(TextView) findViewById(R.id.textView7);
        TextView points=(TextView) findViewById(R.id.textView8);
        if (score <250){
            spinner.setSelection(0);
        }else if(score >=250 && score <500){
            spinner.setSelection(1);
        }else if(score >=500 && score <750){
            spinner.setSelection(2);
        }else {
            spinner.setSelection(3);
        }
        spinner.setEnabled(false);
        pseudo.setText(joueur);
        points.setText(Integer.toString(score));
    }
}
