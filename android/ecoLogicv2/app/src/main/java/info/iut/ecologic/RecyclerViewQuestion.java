package info.iut.ecologic;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import Adapter.QCMUniqueSolutionAdapter;
import Entites.QCMUniqueSolution;

public class RecyclerViewQuestion extends AppCompatActivity {

    AppDataBase dataBase;

    RecyclerView recyclerView;
    QCMUniqueSolutionAdapter qcmUniqueSolutionAdapter;

    List<QCMUniqueSolution> qcmUniqueSolutionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_question);
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();

        qcmUniqueSolutionList = dataBase.qcmUniqueSolutionDAO().getAllQuestions();
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        qcmUniqueSolutionAdapter = new QCMUniqueSolutionAdapter(this, qcmUniqueSolutionList);
        recyclerView.setAdapter(qcmUniqueSolutionAdapter);
    }
}
