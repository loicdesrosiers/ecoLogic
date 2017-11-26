package info.iut.ecologic;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Entites.User;

public class Inscription extends AppCompatActivity {

    EditText email;
    EditText pseudo;
    EditText password;
    EditText password2;
    Button inscription;
    Button retour;
    AppDataBase appDataBase;
    Thread T;
    boolean inscriptionStatus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //Initialisation variables
        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        email = findViewById(R.id.ETEmail);
        pseudo = findViewById(R.id.ETPseudo);
        password = findViewById(R.id.ETPwd);
        password2 = findViewById(R.id.ETPwd2);
        inscription = findViewById(R.id.BTNInscription);
        retour = findViewById(R.id.BTNRetour);

        //On clickListener Inscription
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText() != null && pseudo.getText() != null && password.getText() != null && password2.getText() != null){
                    T = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            GestionBDExterne.UpdateBDUser(appDataBase);
                        }
                    });
                    try{
                        T.start();
                        T.join();
                    }catch(Exception ex){

                    }
                    User userTest = appDataBase.userDAO().checkUserEmail(email.getText().toString());
                    if(userTest == null){
                        if(password.getText().toString().equals(password2.getText().toString())){
                            T = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String test ="http://ecologic-lyon1.fr/test/insertUser.php?email="+email.getText().toString()+"&pseudo="+pseudo.getText().toString()+"&password="+hash256(password.getText().toString());
                                    inscriptionStatus = GestionBDExterne.insertUsers(appDataBase, email.getText().toString(), test);
                                }
                            });
                            try{
                                T.start();
                                T.join();
                                if(inscriptionStatus){
                                    Intent i = new Intent(Inscription.this, Connexion.class);
                                    Toast.makeText(Inscription.this, "Inscription réussi", Toast.LENGTH_LONG).show();
                                    i.putExtra("email", email.getText().toString());
                                    startActivity(i);
                                    finish();
                                }else{
                                    Toast.makeText(Inscription.this, "Inscription Echec", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception ex){

                            }
                        }else{
                            Toast.makeText(Inscription.this, getString(R.string.inscription_mot_passe_differents), Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(Inscription.this, getString(R.string.insription_email_deja_utilise), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Inscription.this, getString(R.string.inscription_remplir_champs), Toast.LENGTH_LONG).show();
                }
            }
        });

        //On clickListener Retour
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Inscription.this);
                builder.setCancelable(true);
                builder.setTitle(getString(R.string.fenetre_titre_confirmer));
                builder.setMessage(getString(R.string.insription_fenetre_confirmer));
                builder.setPositiveButton(getString(R.string.boutton_quitter),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Inscription.this, Connexion.class);
                                if(email.getText() != null) i.putExtra("email", email.getText().toString());
                                startActivity(i);
                                finish();
                            }
                        });
                builder.setNegativeButton(getString(R.string.boutton_rester), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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
