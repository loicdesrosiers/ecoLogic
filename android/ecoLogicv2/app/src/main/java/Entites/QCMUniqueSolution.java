package Entites;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.Serializable;

@Entity(tableName = "qcmuniquesolution")
public class QCMUniqueSolution implements Serializable{

    @PrimaryKey(autoGenerate = true) @NonNull
    private int id;
    @NonNull
    private int score;
    @NonNull
    private String theme;
    @NonNull
    private String intitule;
    @NonNull
    private String rep1;
    @NonNull
    private String rep2;
    @NonNull
    private String rep3;
    @NonNull
    private String rep4;
    @NonNull
    private int idRep;
    @NonNull
    private String explication;

    public QCMUniqueSolution(JSONObject jObject) {
        this.id = jObject.optInt("id");
        this.score = jObject.optInt("score");;
        this.theme = jObject.optString("theme");;
        this.intitule = jObject.optString("intitule");;
        this.rep1 = jObject.optString("reponse1");;
        this.rep2 = jObject.optString("reponse2");;
        this.rep3 = jObject.optString("reponse3");;
        this.rep4 = jObject.optString("reponse4");;
        this.idRep = jObject.optInt("idReponse");;
        this.explication = jObject.optString("explication");;
    }

    public QCMUniqueSolution(int id, int score, String theme, String intitule, String rep1, String rep2, String rep3, String rep4, int idRep, String explication) {
        this.id = id;
        this.score = score;
        this.theme = theme;
        this.intitule = intitule;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
        this.rep4 = rep4;
        this.idRep = idRep;
        this.explication = explication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {this.theme = theme;}

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getRep1() {
        return rep1;
    }

    public void setRep1(String rep1) {
        this.rep1 = rep1;
    }

    public String getRep2() {
        return rep2;
    }

    public void setRep2(String rep2) {
        this.rep2 = rep2;
    }

    public String getRep3() {
        return rep3;
    }

    public void setRep3(String rep3) {
        this.rep3 = rep3;
    }

    public String getRep4() {
        return rep4;
    }

    public void setRep4(String rep4) {
        this.rep4 = rep4;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }
}
