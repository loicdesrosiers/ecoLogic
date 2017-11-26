package com.example.lloc.ecologic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by lLoïc on 17/11/2017.
 */

public class MusicController extends Service {
    private MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }
    @Override
    public void onCreate(){
        super.onCreate();
        player = MediaPlayer.create(this,R.raw.background_music);
        player.setLooping(true);
        player.setVolume(25,25);


    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy(){
        player.stop();
        player.release();
    }



}