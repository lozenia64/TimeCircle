package com.ljy93.timecircle;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.util.Random;

public class Myservice extends Service {
    MediaPlayer mp;
    Random generator = new Random();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        int rNum = generator.nextInt(5);
        if (rNum == 0) mp = MediaPlayer.create(this, R.raw.anthem);
        else if (rNum == 1) mp = MediaPlayer.create(this, R.raw.chicken);
        else if (rNum == 2) mp = MediaPlayer.create(this, R.raw.hard);
        else if (rNum == 3) mp = MediaPlayer.create(this, R.raw.ticker);
        else mp = MediaPlayer.create(this, R.raw.traveller);
        mp.setLooping(false);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}