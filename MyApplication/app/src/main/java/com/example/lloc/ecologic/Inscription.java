package com.example.lloc.ecologic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by lLoïc on 24/10/2017.
 */

public class Inscription extends AppCompatActivity {
    EditText Eusername, Epassword, Eemail, Epseudo;
    Button signup;
    private static final String REGISTER_URL = "http://ecologic-lyon1.fr/test/Ajout_bd.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        Eusername =(EditText) findViewById(R.id.username);
        Eusername.setHintTextColor(Color.WHITE);
        Epassword =(EditText) findViewById(R.id.password);
        Epassword.setHintTextColor(Color.WHITE);
        Eemail =(EditText) findViewById(R.id.email);
        Eemail.setHintTextColor(Color.WHITE);
        Epseudo =(EditText) findViewById(R.id.pseudo);
        Epseudo.setHintTextColor(Color.WHITE);
        signup =(Button) findViewById(R.id.button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }
    private void registerUser() {
        String name=Eusername.getText().toString().trim();
        String email=Eemail.getText().toString().trim();
        String password=Epassword.getText().toString().trim();
        String pseudo=Epseudo.getText().toString().trim();
        register(name,email,password,pseudo);
    }

    private void register(String name, String email, String password, String pseudo) {
        String urlsuffix="?username="+name+"&password="+password+"&email="+email+"&pseudo="+pseudo;
        class RegisterUser extends AsyncTask<String,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading=ProgressDialog.show(Inscription.this,"Patientez...",null,true,true);
            }


            @Override
            protected String doInBackground(String... params) {
                String s =params[0];

                BufferedReader bufferReader=null;
                try{

                    URL url = new URL(REGISTER_URL+s);
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
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                loading.dismiss();
                if(s.equals("Inscription validée")){
                    Intent intent=new Intent(Inscription.this, MenuPrincipal.class);
                    intent.putExtra("mail",Eemail.getText().toString().trim());
                    intent.putExtra("pseudo",Epseudo.getText().toString().trim());
                    startActivity(intent);
                }
            }
        }
        RegisterUser ur =new RegisterUser();
        ur.execute(urlsuffix);
    }
}
