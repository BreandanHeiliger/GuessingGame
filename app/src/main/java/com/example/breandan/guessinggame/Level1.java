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

public class Level1 extends AppCompatActivity implements View.OnClickListener {

    private SoundPool soundPool;   //Used to play sounds.

    int correctSound = -1;          //For use with the "correct" sound.
    int incorrectSound = -1;        //For use with the "incorrect" sound.
    int possiblePoints = 3;         //Max possible points.
    int guesses = 0;                //Score is possiblePoints minus guesses.
    int currentScore = 0;           //Used to keep track of current score.

    //Create random selection between 1 and 3
    Random randInt = new Random();
    int ourRandom = randInt.nextInt(3) + 1;

    //Make a buttons from each of the 3 buttons in our layout
    Button l1b1, l1b2, l1b3;

    //For high score.
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    int highScore;

    //For wobble animation.
    Animation wobble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        //Set wobble object
        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);

        //Create buttons.
        l1b1 = (Button) findViewById(R.id.l1button1);
        l1b1.setOnClickListener(this);

        l1b2 = (Button) findViewById(R.id.l1button2);
        l1b2.setOnClickListener(this);

        l1b3 = (Button) findViewById(R.id.l1button3);
        l1b3.setOnClickListener(this);

        //Used to play sounds.
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        //Try to get start sound.
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

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.l1button1:  //when the first button is pressed

                if (ourRandom == 1) {
                    l1b1.startAnimation(wobble);
                    startNextLevel();
                } else
                    incorrect();
                break;

            case R.id.l1button2:

                if (ourRandom == 2) {
                    l1b2.startAnimation(wobble);
                    startNextLevel();
                } else
                    incorrect();
                break;

            case R.id.l1button3:

                if (ourRandom == 3) {
                    l1b3.startAnimation(wobble);
                    startNextLevel();
                } else
                    incorrect();
                break;
        }

    }//End onClick override.

    void startNextLevel() {

        Context context = getApplicationContext();
        CharSequence text = "Correct!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Play "correct" sound and do animation.
        soundPool.play(correctSound, 1, 1, 0, 0, 1);

        //Compare number of counts and assign correct score.
        //3 points for first guess, 2 for second, 1 for third and zero for all others.



        //Save score then start next level.

        Intent nl = new Intent(this, Level2.class);
        startActivity(nl);

    }//End startNextLevel

    void incorrect() {

        //Play "incorrect" sound and do animation.
        soundPool.play(incorrectSound, 1, 1, 0, 0, 1);


        //Display toast saying guess was not correct.
        Context context = getApplicationContext();
        CharSequence text = "NOPE!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        //Decrement number of possible points.
        if(possiblePoints != 0)
            possiblePoints--;

    }

}//End level 1 activity.
