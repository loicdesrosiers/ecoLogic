package info.iut.ecologic;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Entites.QCMUniqueSolution;
import Entites.User;

public class GestionBDExterne {

    public static void UpdateBDUser(AppDataBase dataBase){
        clearUser(dataBase);
        updateUsers(dataBase);
    }

    private static void clearUser(AppDataBase dataBase){
        List<User> users = dataBase.userDAO().getAllUsers();
        for(User u : users){
            dataBase.userDAO().delete(u);
        }
    }
    final static String UPDATE_USERS_URL="http://ecologic-lyon1.fr/test/getUsers.php";
    private static void updateUsers(final AppDataBase dataBase){
        class UpdateUser extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferReader=null;
                try{
                    URL url = new URL(UPDATE_USERS_URL);
                    HttpURLConnection con =(HttpURLConnection) url.openConnection();
                    bufferReader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();
                    return result;

                }catch (Exception e ){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String s){
                parseUser(s, dataBase);
            }
        }
        UpdateUser ur =new UpdateUser();
        ur.execute();
    }

    private static void parseUser(final String json, AppDataBase dataBase) {
        try {
            final List<User> users = new ArrayList<>();
            final JSONArray jUserArray = new JSONArray(json);
            for (int i = 0; i < jUserArray.length(); i++) {
                users.add(new User(jUserArray.optJSONObject(i)));
                dataBase.userDAO().insertAll(new User(jUserArray.optJSONObject(i)));
            }
        } catch (Exception e) {
            System.err.println("Erreur");
        }
    }

    //Ajout utilisateur
    public static boolean insertUsers(final AppDataBase dataBase, String email, final String URL){
        class UpdateUser extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferReader=null;
                try{
                    URL url = new URL(URL);
                    HttpURLConnection con =(HttpURLConnection) url.openConnection();
                    bufferReader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();
                    return result;

                }catch (Exception e ){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String s){
                
            }
        }
        UpdateBDUser(dataBase);
        List<User> users = dataBase.userDAO().getAllUsers();
        boolean suite = true;
        for(User u : users){
            if(u.getEmail() == email) suite = false;
        }
        if(suite){
            UpdateUser ur =new UpdateUser();
            ur.execute();
            UpdateBDUser(dataBase);
            return true;
        }else{
            return false;
        }
    }

    //Update Questions
    public static void UpdateBDQuestionsUniqueSolution(AppDataBase dataBase){
        clearQuestionsUniqueSolution(dataBase);
        updateQuestionsUniqueSolution(dataBase);
    }

    private static void clearQuestionsUniqueSolution(AppDataBase dataBase){
        List<QCMUniqueSolution> qcmQuestionSimple = dataBase.qcmUniqueSolutionDAO().getAllQuestions();
        for(QCMUniqueSolution qcm : qcmQuestionSimple){
            dataBase.qcmUniqueSolutionDAO().delete(qcm);
        }
    }
    final static String UPDATE_QUESTION_UNIQUE_SOLUTION_URL="http://ecologic-lyon1.fr/test/getQCMUniqueSolution.php";
    private static void updateQuestionsUniqueSolution(final AppDataBase dataBase){
        class UpdateUser extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferReader=null;
                try{
                    URL url = new URL(UPDATE_QUESTION_UNIQUE_SOLUTION_URL);
                    HttpURLConnection con =(HttpURLConnection) url.openConnection();
                    bufferReader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();
                    return result;

                }catch (Exception e ){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String s){
                parseQuestionsUniqueSolution(s, dataBase);
            }
        }
        UpdateUser ur =new UpdateUser();
        ur.execute();
    }

    private static void parseQuestionsUniqueSolution(final String json, AppDataBase dataBase) {
        try {
            final JSONArray jQCMArray = new JSONArray(json);
            for (int i = 0; i < jQCMArray.length(); i++) {
                dataBase.qcmUniqueSolutionDAO().insertAll(new QCMUniqueSolution(jQCMArray.optJSONObject(i)));
            }
        } catch (Exception e) {
            System.err.println("Erreur");
        }
    }
}
