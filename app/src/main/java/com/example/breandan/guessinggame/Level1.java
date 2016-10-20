package com.example.breandan.guessinggame;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import java.util.Random;
import java.io.IOException;

public class Level1 extends AppCompatActivity implements View.OnClickListener {

    private SoundPool soundPool;   //Used to play sounds.

    int correctSound = -1;          //For use with the "correct" sound.
    int incorrectSound = -1;        //For use with the "incorrect" sound.
    int guessCount = 1;             //Used to keep track of guesses.
    int currentScore = 0;           //Used to keep track of current score.

    //Create random selection between 1 and 3
    Random randInt = new Random();
    int ourRandom = randInt.nextInt(3) + 1;

    //Make a button from each of the 3 buttons in our layout
    Button button1 = (Button) findViewById(R.id.button1);
    Button button2 = (Button) findViewById(R.id.button2);
    Button button3 = (Button) findViewById(R.id.button3);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

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



        //make each of them listen for clicks
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1:  //when the first button is pressed

                //Check if 1 is the correct answer.
                if (ourRandom == 1)
                    startNextLevel();
                else
                    incorrect();

                break;

            case R.id.button2:  //when the second button is pressed

                //Check if 2 is the correct answer.
                if (ourRandom == 2)
                    startNextLevel();
                else
                    incorrect();
                break;

            case R.id.button3:  //when the third button is pressed

                //Check if 3 is the correct answer.
                if (ourRandom == 3)
                    startNextLevel();
                else
                    incorrect();
                break;
        }

    }//End onClick override.

    void startNextLevel() {

        //Play "correct" sound and do animation.
        soundPool.play(correctSound, 1, 1, 0, 0, 1);

        //Compare number of counts and assign correct score.
        //3 points for first guess, 2 for second, 1 for third and zero for all others.

        //Maybe do toast for score with explanation.

        //Save score then start next level.

    }//End startNextLevel

    void incorrect() {

        //Play "incorrect" sound and do animation.
        //Play "correct" sound and do animation.
        soundPool.play(incorrectSound, 1, 1, 0, 0, 1);

        //Increment number of guesses in guessCount.
        guessCount++;

    }

}//End level 1 activity.
