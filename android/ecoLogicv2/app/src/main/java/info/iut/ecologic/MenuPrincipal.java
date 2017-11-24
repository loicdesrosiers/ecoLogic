package info.iut.ecologic;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.MergeCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import Entites.QCMUniqueSolution;
import Entites.User;

public class MenuPrincipal extends AppCompatActivity {

    Button BTNusers;
    Button BTNAjout;
    Button BTNQCMUniqueSolution;
    Button BTNGO;

    EditText ETIntitule;
    EditText ETTheme;
    EditText ETRep1;
    EditText ETRep2;
    EditText ETRep3;
    EditText ETRep4;
    EditText ETBonneRep;
    EditText ETExplication;

    User user;
    AppDataBase dataBase;

    List<QCMUniqueSolution> qcmUniqueSolutionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ETIntitule = findViewById(R.id.ETIntitule);
        ETTheme = findViewById(R.id.ETTheme);
        ETRep1 = findViewById(R.id.ETRep1);
        ETRep2 = findViewById(R.id.ETRep2);
        ETRep3 = findViewById(R.id.ETRep3);
        ETRep4 = findViewById(R.id.ETRep4);
        ETBonneRep = findViewById(R.id.ETBonneRep);
        ETExplication = findViewById(R.id.ETExplications);

        TextView text = findViewById(R.id.TV);
        BTNGO = findViewById(R.id.BTNGO);
        BTNusers = findViewById(R.id.BTNUsers);
        BTNQCMUniqueSolution = findViewById(R.id.BTNQCMUniqueSolution);
        BTNAjout = findViewById(R.id.BTNAjoutQuestion);

        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");
        text.setText("User : "+user.getEmail());

        BTNusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this, RecyclerViewUsers.class);
                startActivity(i);
                finish();
            }
        });

        BTNQCMUniqueSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this, RecyclerViewQuestion.class);
                startActivity(i);
                finish();
            }
        });

        BTNAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajoutQuestion();
            }
        });

        BTNGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qcmUniqueSolutionList = dataBase.qcmUniqueSolutionDAO().getAllQuestions();
                Random rd = new Random();
                QCMUniqueSolution question = qcmUniqueSolutionList.get(rd.nextInt(qcmUniqueSolutionList.size()));
                Intent intent = new Intent(MenuPrincipal.this, JeuQCMSimple.class);
                intent.putExtra("user", user);
                intent.putExtra("question", question);
                startActivity(intent);
                finish();
            }
        });
    }

    public void ajoutQuestion(){
        QCMUniqueSolution question = new QCMUniqueSolution(0, 1, ETTheme.getText().toString(), ETIntitule.getText().toString(), ETRep1.getText().toString()
        , ETRep2.getText().toString(), ETRep3.getText().toString(), ETRep4.getText().toString(), Integer.parseInt(ETBonneRep.getText().toString()), ETExplication.getText().toString());
        dataBase.qcmUniqueSolutionDAO().insertAll(question);
        Toast.makeText(MenuPrincipal.this, "Question ajouté", Toast.LENGTH_LONG).show();
        ETIntitule.setText("");
        ETTheme.setText("");
        ETRep1.setText("");
        ETRep2.setText("");
        ETRep3.setText("");
        ETRep4.setText("");
        ETBonneRep.setText("");
        ETExplication.setText("");
    }
}
