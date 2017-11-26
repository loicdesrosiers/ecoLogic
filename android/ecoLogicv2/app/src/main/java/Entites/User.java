package Entites;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {

    @PrimaryKey @NonNull @SerializedName("mail")
    private String email;

    @SerializedName("pseudo")
    private String pseudo;

    @SerializedName("password")
    private String password;

    @SerializedName("score")
    private int score;

    public User(JSONObject jObject){
        this.email = jObject.optString("mail");
        this.pseudo = jObject.optString("pseudo");
        this.password = jObject.optString("password");
        this.score = jObject.optInt("score");
    }

    public User(String email, String pseudo, String password, int score){
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
