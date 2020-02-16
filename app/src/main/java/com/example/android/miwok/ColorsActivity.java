package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    /** Handels the playback of the provided soundfiles */
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_container);

        final ArrayList<Word> colorsList = new ArrayList<>();
        colorsList.add(new Word("red", "weṭeṭṭi",
                R.drawable.color_red, R.raw.color_red));
        colorsList.add(new Word("green", "chokokki",
                R.drawable.color_green, R.raw.color_green));
        colorsList.add(new Word("brown", "ṭakaakki",
                R.drawable.color_brown, R.raw.color_brown));
        colorsList.add(new Word("gray", "ṭopoppi",
                R.drawable.color_gray, R.raw.color_gray));
        colorsList.add(new Word("black", "kululli",
                R.drawable.color_black, R.raw.color_black));
        colorsList.add(new Word("white", "kelelli",
                R.drawable.color_white, R.raw.color_white));
        colorsList.add(new Word("dusty yello", "ṭopiisә",
                R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsList.add(new Word("mustard yellow", "chiwiiṭә",
                R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter wordAdapter = new WordAdapter(this, colorsList, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaPlayer = MediaPlayer.create(adapterView.getContext(),
                        colorsList.get(i).getSoundResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        releaseMediaPlayer();
                    }
                });
            }
        });
    }

    /**
     * Clean up the media player by releasing it's resourced
     */
    private void releaseMediaPlayer() {
        if(mediaPlayer != null) {
            // Regardless of the state of the mediaPlayer, release its resources
            // because we don't need it anymore
            mediaPlayer.release();

            // Set the media player to null. For our project, we decided that having
            // the mediaPlayer set to null is an easy way to decide that it is not
            // being used playing music or used in another state.
            mediaPlayer = null;
            Toast.makeText(getApplicationContext(), "Released MediaPlayer",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
