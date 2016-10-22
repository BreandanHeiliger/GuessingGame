package com.example.breandan.guessinggame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Random;
import java.io.IOException;

public class Level2 extends AppCompatActivity implements View.OnClickListener {

    //Max 5 possible points for level 2.
    int possiblePoints = 5;

    //For high score.
    public static final String PREFS_NAME = "Game Data";
    String topScore = "TopScore";
    String scoreNow = "Current Score";
    int highScore;
    int currentScore;

    //For wobble and incorrect animations.
    Animation wobble, incorrect;

    //Used to play sounds.
    private SoundPool soundPool;
    int correctSound = -1;          //For use with the "correct" sound.
    int incorrectSound = -1;        //For use with the "incorrect" sound.


    //Create random selection between 1 and 3
    Random randInt = new Random();
    int ourRandom = randInt.nextInt(5) + 1;

    //Make a buttons from each of the 3 buttons in our layout
    Button l2b1, l2b2, l2b3, l2b4,l2b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        //Set wobble object
        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);
        incorrect = AnimationUtils.loadAnimation(this, R.anim.incorrect);

        //Get preferences to change high score.
        SharedPreferences data;
        data = getSharedPreferences(PREFS_NAME, 0);
        highScore = data.getInt(topScore, 0);
        currentScore = data.getInt(topScore,0);

        //Create buttons.
        l2b1 = (Button) findViewById(R.id.l2b1);
        l2b1.setOnClickListener(this);
        l2b2 = (Button) findViewById(R.id.l2b2);
        l2b2.setOnClickListener(this);
        l2b3 = (Button) findViewById(R.id.l2b3);
        l2b3.setOnClickListener(this);
        l2b4 = (Button) findViewById(R.id.l2b4);
        l2b4.setOnClickListener(this);
        l2b5 = (Button) findViewById(R.id.l2b5);
        l2b5.setOnClickListener(this);

        //Used to play sounds.
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            AssetManager assetsManager = getAssets();
            AssetFileDescriptor descriptor;

            //Create "correct" sound in memory.
            descriptor = assetsManager.openFd("correct.wav");
            correctSound = soundPool.load(descriptor, 0);

            //Create "incorrect" sound in memory.
            descriptor = assetsManager.openFd("incorrect.wav");
            incorrectSound = soundPool.load(descriptor, 0);

        } catch (IOException e) {
            Context context = getApplicationContext();
            CharSequence text = "File not found!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }//End onCreate override.


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.l2b1:  //when the first button is pressed

                if (ourRandom == 1) {
                    l2b1.startAnimation(wobble);
                    startNextLevel();
                } else {
                    l2b1.startAnimation(incorrect);
                    incorrect();
                }

                break;

            case R.id.l2b2:

                if (ourRandom == 2) {
                    l2b2.startAnimation(wobble);
                    startNextLevel();
                } else {
                    l2b2.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l2b3:

                if (ourRandom == 3) {
                    l2b3.startAnimation(wobble);
                    startNextLevel();
                } else {
                    l2b3.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l2b4:

                if (ourRandom == 4) {
                    l2b4.startAnimation(wobble);
                    startNextLevel();
                } else {
                    l2b4.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l2b5:

                if (ourRandom == 5) {
                    l2b5.startAnimation(wobble);
                    startNextLevel();
                } else {
                    l2b5.startAnimation(incorrect);
                    incorrect();
                }
                break;
        }

    }//End onClick override.

    void startNextLevel() {

        //Add possible points to current score.
        currentScore+=possiblePoints;

        //Show toast for correct answer, play sound,set scores, start next level.
        Context context = getApplicationContext();
        CharSequence text = "Correct!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        soundPool.play(correctSound, 1, 1, 0, 0, 1);


        SharedPreferences data;
        data = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = data.edit();

        //Compare current score with high score and update high score if needed.
        if (currentScore > highScore) {

            //Toast for new high score.
            text = "New High Score!";
            toast = Toast.makeText(context, text, duration);
            toast.show();

            //Put current score as new high score.
            editor.putInt(topScore, currentScore);
        }

        //Save current score, then start next level.
        editor.putInt(scoreNow, currentScore);

        // Commit the edits to current (and high score).
        editor.commit();

        Intent nl = new Intent(this, Level3.class);
        startActivity(nl);

    }//End startNextLevel

    void incorrect() {

        //Decrement number of possible points.
        if(possiblePoints>0){
            possiblePoints--;
        }


        //Play "incorrect" sound and do animation.
        soundPool.play(incorrectSound, 1, 1, 0, 0, 1);

        //Display toast saying guess was not correct.
        Context context = getApplicationContext();
        CharSequence text = "NOPE!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }//End incorrect


}//End class.
