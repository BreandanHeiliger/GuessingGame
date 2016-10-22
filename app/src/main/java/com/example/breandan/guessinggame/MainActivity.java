package com.example.breandan.guessinggame;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Used to load persistent high score.
    public static  final String PREFS_NAME = "Game Data";
    String topScore = "TopScore";
    String scoreNow = "Current Score";
    public static int highScore;
    public static int currentScore = 0;
    TextView textHighScore;

    //Used to play start sound.
    private SoundPool soundPool;
    int playsound = -1;

    //The play button.
    public Button buttonPlay;

    //For wobble animation.
    Animation wobble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set wobble object
        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);

        //Get high score and set it to the proper location.
        SharedPreferences data;
        data = getSharedPreferences(PREFS_NAME, 0);
        highScore = data.getInt(topScore, 0);

        //Set current score to zero.
        currentScore = 0;
        SharedPreferences.Editor editor = data.edit();
        editor.putInt(scoreNow, currentScore);
        editor.apply();

        textHighScore = (TextView) findViewById(R.id.highScoreTV);
        textHighScore.setText(highScore + "");


        //Set button
        buttonPlay = (Button) findViewById(R.id.play);
        buttonPlay.setOnClickListener(this);

        //Set soundPool.
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        //Try to get start sound.
        try {
            AssetManager assetsManager = getAssets();
            AssetFileDescriptor descriptor;

            //Create play sound in memory.
            descriptor = assetsManager.openFd("start.wav");
            playsound = soundPool.load(descriptor, 0);

        } catch (IOException e) {
            Context context = getApplicationContext();
            CharSequence text = "File not found!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }

    @Override
    public void onClick(View v) {

        buttonPlay.startAnimation(wobble);

        //Play start sound.
        soundPool.play(playsound, 1, 1, 0, 0, 1);

        //Create new intent for level1 and start level.
        Intent m = new Intent(this, Level1.class);
        startActivity(m);


    }//End onClick override.

}//End main activity.
