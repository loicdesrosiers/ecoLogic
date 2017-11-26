package info.iut.ecologic;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import java.util.List;
import Entites.QCMUniqueSolution;
import Entites.User;

public class SplashScreen extends AppCompatActivity {

    AppDataBase dataBase;
    Thread Tdownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        if(isOnline()){
            ProgressBar pb = findViewById(R.id.progressBar);
            pb.getIndeterminateDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
            clearDataBase();

            Tdownload = new Thread(new Runnable() {
                @Override
                public void run() {
                    GestionBDExterne.UpdateBDUser(dataBase);
                    GestionBDExterne.UpdateBDQuestionsUniqueSolution(dataBase);
                }
            });
            try{
                Tdownload.start();
                Tdownload.join();
                Intent intent = new Intent(SplashScreen.this,Connexion.class);
                startActivity(intent);
                finish();
            }catch(Exception ex){

            }
        }else{
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(SplashScreen.this);
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("Connection");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alertDialog.show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void clearDataBase(){
        List<User> users = dataBase.userDAO().getAllUsers();
        for(User u : users){
            dataBase.userDAO().delete(u);
        }
        List<QCMUniqueSolution> qcmQuestionSimple = dataBase.qcmUniqueSolutionDAO().getAllQuestions();
        for(QCMUniqueSolution qcm : qcmQuestionSimple){
            dataBase.qcmUniqueSolutionDAO().delete(qcm);
        }
    }
}
