package com.example.lloc.ecologic;

/**
 * Created by lLoïc on 05/11/2017.
 */

public class Question {
    private String intitule,rep1,rep2,rep3,rep4,bonnereponse,explication;
    private int id,score;

    public Question(){}

    public Question (int id,String intitule,String rep1, String rep2, String rep3, String rep4, String bonnereponse,String explication,int score)
    {
        this.id = id;
        this.intitule = intitule;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
        this.rep4 = rep4;
        this.bonnereponse = bonnereponse;
        this.explication=explication;
        this.score =score;
    }

    public int getScore() {return score; }
    public String getExplication() {return explication; }
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getReponse() {
        return bonnereponse;
    }

    public void setReponse(String reponse) {
        this.bonnereponse = reponse;
    }

    public String getRep4() {

        return rep4;
    }

    public void setRep4(String rep4) {
        this.rep4 = rep4;
    }

    public String getRep3() {

        return rep3;
    }

    public void setRep3(String rep3) {
        this.rep3 = rep3;
    }

    public String getRep2() {

        return rep2;
    }

    public void setRep2(String rep2) {
        this.rep2 = rep2;
    }

    public String getRep1() {

        return rep1;
    }

    public void setRep1(String rep1) {
        this.rep1 = rep1;
    }

    public String verifierReponse(String repUtilisateur){
        return (this.bonnereponse == repUtilisateur ? "Bonne Réponse" : "Mauvaise Réponse");
    }

}