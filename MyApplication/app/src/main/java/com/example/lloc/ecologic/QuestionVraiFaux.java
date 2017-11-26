package com.example.lloc.ecologic;

/**
 * Created by lLoïc on 26/11/2017.
 */

public class QuestionVraiFaux {
    String intitule,reponse;
    int score;

    public QuestionVraiFaux(){

    }
    public QuestionVraiFaux(String intitule,String reponse, int score){
        this.score=score;
        this.intitule=intitule;
        this.reponse=reponse;
    }
    public int getScore() {return score; }
    public String getReponse() {return reponse; }
    public String getIntitule() {
        return intitule;
    }
}
