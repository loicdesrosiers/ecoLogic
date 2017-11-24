package info.iut.ecologic;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import Entites.QCMUniqueSolution;
import Entites.User;

public class JeuQCMSimple extends AppCompatActivity {

    TextView TVID;
    TextView TVTheme;
    TextView TVIntitule;

    Button BTNRep1;
    Button BTNRep2;
    Button BTNRep3;
    Button BTNRep4;
    boolean terminer = false;

    QCMUniqueSolution question;
    User user;
    AppDataBase dataBase;

    View.OnClickListener ReponseJuste = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!terminer){
                terminer = true;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(JeuQCMSimple.this);
                alertDialog.setTitle("Bonne réponse");
                alertDialog.setMessage("vous avez gagné "+question.getScore()+" points !");
                alertDialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.setScore(user.getScore()+question.getScore());
                        Toast.makeText(JeuQCMSimple.this, ""+user.getScore(), Toast.LENGTH_LONG).show();
                        dataBase.userDAO().updateUsers(user);
                        Intent intent = new Intent(JeuQCMSimple.this, MenuPrincipal.class);
                       intent.putExtra("user", user);
                       startActivity(intent);
                       finish();
                    }
                });
                alertDialog.show();
            }
        }
    };

    View.OnClickListener ReponseFausse = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!terminer){
                terminer = true;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(JeuQCMSimple.this);
                alertDialog.setTitle("Mauvaise réponse");
                alertDialog.setMessage("Explication : "+question.getExplication());
                alertDialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(JeuQCMSimple.this, MenuPrincipal.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialog.show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_qcmsimple);
        Intent i = getIntent();
        question =(QCMUniqueSolution) i.getSerializableExtra("question");

        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        user =(User) i.getSerializableExtra("user");
        TVID = findViewById(R.id.TVID);
        TVTheme = findViewById(R.id.TVTheme);
        TVIntitule = findViewById(R.id.TVIntitule);

        BTNRep1 = findViewById(R.id.BTNRep1);
        BTNRep2 = findViewById(R.id.BTNRep2);
        BTNRep3 = findViewById(R.id.BTNRep3);
        BTNRep4 = findViewById(R.id.BTNRep4);

        TVID.setText(question.getId()+"");
        TVTheme.setText(question.getTheme().toString());
        TVIntitule.setText(question.getIntitule().toString());
        BTNRep1.setText(question.getRep1().toString());
        BTNRep2.setText(question.getRep2().toString());
        BTNRep3.setText(question.getRep3().toString());
        BTNRep4.setText(question.getRep4().toString());

        switch (question.getIdRep()){
            case 1:
                BTNRep1.setOnClickListener(ReponseJuste);
                BTNRep2.setOnClickListener(ReponseFausse);
                BTNRep3.setOnClickListener(ReponseFausse);
                BTNRep4.setOnClickListener(ReponseFausse);
                break;
            case 2:
                BTNRep1.setOnClickListener(ReponseFausse);
                BTNRep2.setOnClickListener(ReponseJuste);
                BTNRep3.setOnClickListener(ReponseFausse);
                BTNRep4.setOnClickListener(ReponseFausse);
                break;
            case 3:
                BTNRep1.setOnClickListener(ReponseFausse);
                BTNRep2.setOnClickListener(ReponseFausse);
                BTNRep3.setOnClickListener(ReponseJuste);
                BTNRep4.setOnClickListener(ReponseFausse);
                break;
            case 4:
                BTNRep1.setOnClickListener(ReponseFausse);
                BTNRep2.setOnClickListener(ReponseFausse);
                BTNRep3.setOnClickListener(ReponseFausse);
                BTNRep4.setOnClickListener(ReponseJuste);
                break;
        }
    }
}
