package info.iut.ecologic;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Entites.User;

public class Connexion extends AppCompatActivity {

    EditText email;
    EditText password;
    Button inscription;
    Button connexion;

    AppDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        email = findViewById(R.id.ETEmail);
        password = findViewById(R.id.ETPassword);
        inscription = findViewById(R.id.BTNInscription);
        connexion = findViewById(R.id.BTNConnexion);
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            if (extras.containsKey("email"))
            {
                Toast.makeText(Connexion.this, ""+extras.getString("email"), Toast.LENGTH_LONG).show();
                email.setText(extras.getString("email"));
            }
        }

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Connexion.this, Inscription.class);
                startActivity(i);
                finish();
            }
        });
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText() == null || password == null){
                    Toast.makeText(Connexion.this, getString(R.string.connexion_remplir_champs), Toast.LENGTH_LONG).show();
                    connexion.setActivated(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            connexion.setActivated(true);
                        }
                    }, 5000);
                }else{
                    if(checkUser(email.getText().toString())){
                        User cuser = dataBase.userDAO().getUser(email.getText().toString(), hash256(password.getText().toString()));
                        if(cuser == null){
                            Toast.makeText(Connexion.this, getString(R.string.connexion_email_incorrect), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Connexion.this, getString(R.string.connexion_reussite), Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(Connexion.this,MenuPrincipal.class);
                            intent.putExtra("user",cuser);
                            startActivity(intent);
                            finish();
                        }
                    }else{
                        Toast.makeText(Connexion.this, getString(R.string.connexion_user_inconnu), Toast.LENGTH_LONG).show();
                        connexion.setActivated(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                connexion.setActivated(true);
                            }
                        }, 5000);
                    }
                }
            }
        });
    }
    private boolean checkUser(String email){
        User user = dataBase.userDAO().checkUserEmail(email);
        if(user == null){
            return false;
        }else{
            return true;
        }
    }

    private static String hash256(String data){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytesToHex(md.digest());
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

}
