package info.iut.ecologic;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_TIME_OUT = 6000;
    String PHPReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(isOnline()){
            ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
            pb.getIndeterminateDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
            getdata();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this,Connexion.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
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

    String UPDATE_URL="http://ecologic-lyon1.fr/test/GetQCM.php";
    public void getdata(){
        class UpdateUser extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }


            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferReader=null;
                try{
                    URL url = new URL(UPDATE_URL);
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
                PHPReturn = s;
                phpsuite();
            }
        }
        UpdateUser ur =new UpdateUser();
        ur.execute();
    }
    public void phpsuite(){
        Toast.makeText(SplashScreen.this, PHPReturn, Toast.LENGTH_LONG).show();
    }
}
