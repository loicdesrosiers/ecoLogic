package com.example.lloc.ecologic;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lLoïc on 26/11/2017.
 */

public class Parametres extends AppCompatActivity {
    Button son;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

         son=(Button)findViewById(R.id.button3);
        son.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMyServiceRunning(MusicController.class)){
                    son.setBackgroundResource(R.drawable.ic_volume_off_white_18dp);
                    stopService(new Intent(getApplicationContext(), MusicController.class));
                }else{
                    son.setBackgroundResource(R.drawable.ic_volume_up_white_18dp);
                    startService(new Intent(getApplicationContext(), MusicController.class));
                }
            }
        });

    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(this.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
