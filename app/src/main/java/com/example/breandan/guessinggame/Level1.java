package com.example.breandan.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Level1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        //Make a button from each of the 3 buttons in our layout
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        //make each of them listen for clicks
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.button1:  //when the first button is pressed

                //Check if pressed button matches random button.

                //If good guess, then see how many attempts and give correct amount of points.
                //Also, play correct sound and then start next leve.

                //If not, play sound for incorrect answer,reduce points possible and increment counter.

                //play sample 1
                soundPool.play(sample1,1,1,0,0,1);

                break;
            case R.id.button2:  //when the first button is pressed
                //play sample 1
                soundPool.play(sample2,1,1,0,0,1);



                break;
            case R.id.button3:  //when the first button is pressed
                //play sample 1
                soundPool.play(sample3,1,1,0,0,1);
                break;

        }

}
