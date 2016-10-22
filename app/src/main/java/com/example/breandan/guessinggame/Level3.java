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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Random;
import java.io.IOException;

public class Level3 extends AppCompatActivity implements View.OnClickListener {

    //Max 10 possible points for level 3.
    int possiblePoints = 10;

    //For high score.
    SharedPreferences data;
    public static final String PREFS_NAME = "Game Data";
    String topScore = "TopScore";
    String scoreNow = "Current Score";
    int highScore;
    int currentScore;

    //For wobble and incorrect animations.
    Animation wobble, incorrect;
    //For Toast
    Toast toast;
    CharSequence text;
    Context context;
    int duration = Toast.LENGTH_SHORT;

    //Used to play sounds.
    private SoundPool soundPool;
    int correctSound = -1;          //For use with the "correct" sound.
    int incorrectSound = -1;        //For use with the "incorrect" sound.


    //Create random selection between 1 and 3
    Random randInt = new Random();
    int ourRandom = randInt.nextInt(10) + 1;

    //Make a buttons from each of the 3 buttons in our layout
    Button l3b1, l3b2, l3b3, l3b4,l3b5,l3b6,l3b7,l3b8,l3b9,l3b10;

    TextView pointsPTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);


        //Set wobble object
        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);
        incorrect = AnimationUtils.loadAnimation(this, R.anim.incorrect);

        //Get high score and current score.
        data = getSharedPreferences(PREFS_NAME, 0);
        highScore = data.getInt(topScore, 0);
        currentScore = data.getInt(scoreNow,0);

        //Create buttons.
        l3b1 = (Button) findViewById(R.id.l3b1);
        l3b1.setOnClickListener(this);
        l3b2 = (Button) findViewById(R.id.l3b2);
        l3b2.setOnClickListener(this);
        l3b3 = (Button) findViewById(R.id.l3b3);
        l3b3.setOnClickListener(this);
        l3b4 = (Button) findViewById(R.id.l3b4);
        l3b4.setOnClickListener(this);
        l3b5 = (Button) findViewById(R.id.l3b5);
        l3b5.setOnClickListener(this);
        l3b6 = (Button) findViewById(R.id.l3b6);
        l3b6.setOnClickListener(this);
        l3b7 = (Button) findViewById(R.id.l3b7);
        l3b7.setOnClickListener(this);
        l3b8 = (Button) findViewById(R.id.l3b8);
        l3b8.setOnClickListener(this);
        l3b9 = (Button) findViewById(R.id.l3b9);
        l3b9.setOnClickListener(this);
        l3b10 = (Button) findViewById(R.id.l3b10);
        l3b10.setOnClickListener(this);

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

            context = getApplicationContext();
            text = "File not found!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        //Set possible points.
        pointsPTV = (TextView) findViewById(R.id.l3PossiblePoints);
        pointsPTV.setText("Points Possible: " + possiblePoints +"\nCurrent Score: " + currentScore);

    }//End onCreate override.

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.l3b1:  //when the first button is pressed

                if (ourRandom == 1) {
                    l3b1.startAnimation(wobble);
                    endGame();
                } else {
                    l3b1.startAnimation(incorrect);
                    incorrect();
                }

                break;

            case R.id.l3b2:

                if (ourRandom == 2) {
                    l3b2.startAnimation(wobble);
                    endGame();
                } else {
                    l3b2.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l3b3:

                if (ourRandom == 3) {
                    l3b3.startAnimation(wobble);
                    endGame();
                } else {
                    l3b3.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l3b4:

                if (ourRandom == 4) {
                    l3b4.startAnimation(wobble);
                    endGame();
                } else {
                    l3b4.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l3b5:

                if (ourRandom == 5) {
                    l3b5.startAnimation(wobble);
                    endGame();
                } else {
                    l3b5.startAnimation(incorrect);
                    incorrect();
                }
                break;


            case R.id.l3b6:

                if (ourRandom == 6) {
                    l3b6.startAnimation(wobble);
                    endGame();
                } else {
                    l3b6.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l3b7:

                if (ourRandom == 7) {
                    l3b7.startAnimation(wobble);
                    endGame();
                } else {
                    l3b7.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l3b8:

                if (ourRandom == 8) {
                    l3b8.startAnimation(wobble);
                    endGame();
                } else {
                    l3b8.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l3b9:

                if (ourRandom == 9) {
                    l3b9.startAnimation(wobble);
                    endGame();
                } else {
                    l3b9.startAnimation(incorrect);
                    incorrect();
                }
                break;

            case R.id.l3b10:

                if (ourRandom == 10) {
                    l3b10.startAnimation(wobble);
                    endGame();
                } else {
                    l3b10.startAnimation(incorrect);
                    incorrect();
                }
                break;
        }

    }//End onClick override.

    void endGame() {

        //Add possible points to current score.
        currentScore+=possiblePoints;

        //Play correct sound.
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

        //Reset current score to zero and save in preferences.
        editor.putInt(scoreNow, 0);

        // Commit the edits to current (and high score).
        editor.apply();

        Intent nl = new Intent(this,MainActivity.class);
        startActivity(nl);

    }//End startNextLevel


    void incorrect() {

        //Play "incorrect" sound and do animation.
        soundPool.play(incorrectSound, 1, 1, 0, 0, 1);

        //Decrement number of possible points.
        if(possiblePoints>0)
            possiblePoints--;

        //Set possible points.
        pointsPTV = (TextView) findViewById(R.id.l3PossiblePoints);
        pointsPTV.setText("Points Possible: " + possiblePoints +"\nCurrent Score: " + currentScore);
    }//End incorrect

}//End level 3
