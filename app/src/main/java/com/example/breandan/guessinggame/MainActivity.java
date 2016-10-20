package com.example.breandan.guessinggame;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Used to play start sound.
    private SoundPool soundPool;

    int playsound = -1;

    //Used to load persistent high score.
    SharedPreferences prefs;

    //The play button.
    public Button playButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String theHighScore = "00";

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



        //Set button
        playButton = (Button) findViewById(R.id.play);
        playButton.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {

        //Play start sound.
        soundPool.play(playsound, 1, 1, 0, 0, 1);

        //Create new intent for level1 and start level.
        Intent m;
        m = new Intent(this, Level1.class);
        startActivity(m);
    }





}
