package info.iut.ecologic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Wheel extends AppCompatActivity {

    Button button;
    TextView textView;
    ImageView wheel;

    private static final float FACTOR = 360/7;

    Random r;
    int degree = 0, degree_old = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.resultat);
        wheel = findViewById(R.id.ic_wheel);

        r = new Random();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                degree_old = degree%360;
                degree = r.nextInt(3600)+720;
                RotateAnimation rotate = new RotateAnimation(degree_old, degree, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(3600);
                rotate.setFillAfter(true);
                rotate.setInterpolator(new DecelerateInterpolator());

                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textView.setText(currentNumber(360-(degree%360)));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                wheel.startAnimation(rotate);
            }
        });
    }

    private String currentNumber(int degree){
        String text = "";
        if(degree >= (FACTOR * 0) && degree < (FACTOR * 1)){
            text = "Vert";
        }else if(degree >= (FACTOR * 1) && degree < (FACTOR * 2)){
            text = "Cyan";
        }else if(degree >= (FACTOR * 2) && degree < (FACTOR * 3)){
            text = "Viol";
        }else if(degree >= (FACTOR * 3) && degree < (FACTOR * 4)){
            text = "Noir";
        }else if(degree >= (FACTOR * 4) && degree < (FACTOR * 5)){
            text = "Yellow";
        }else if(degree >= (FACTOR * 5) && degree < (FACTOR * 6)){
            text = "Rien";
        }else if(degree >= (FACTOR * 6) && degree < (FACTOR * 7)){
            text = "Rouge";
        }else{
            text = "erreur";
        }
        return text;
    }
}
