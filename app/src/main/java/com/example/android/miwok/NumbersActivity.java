package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    /** Handels the playback of the provided soundfiles */
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_container);

        final ArrayList<Word> numbersList = new ArrayList<>();
        numbersList.add(new Word("one", "lutti", R.drawable.number_one,
                R.raw.number_one));
        numbersList.add(new Word("two", "otiiko", R.drawable.number_two,
                R.raw.number_two));
        numbersList.add(new Word("three", "tolookosu", R.drawable.number_three,
                R.raw.number_three));
        numbersList.add(new Word("four", "oyyisa", R.drawable.number_four,
                R.raw.number_four));
        numbersList.add(new Word("five", "massokka", R.drawable.number_five,
                R.raw.number_five));
        numbersList.add(new Word("six", "temmokka", R.drawable.number_six,
                R.raw.number_six));
        numbersList.add(new Word("seven", "kenekaku", R.drawable.number_seven,
                R.raw.number_seven));
        numbersList.add(new Word("eigth", "kawinta", R.drawable.number_eight,
                R.raw.number_eight));
        numbersList.add(new Word("nine", "wo'e", R.drawable.number_nine,
                R.raw.number_nine));
        numbersList.add(new Word("ten", "na'aacha", R.drawable.number_ten,
                R.raw.number_ten));


        WordAdapter wordAdapter = new WordAdapter(this, numbersList, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaPlayer = MediaPlayer.create(adapterView.getContext(),
                        numbersList.get(i).getSoundResourceId());
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
