package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
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
    private MediaPlayer mMediaPlayer;

    /** Handles the AudioFocus */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener =  new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_container);

        // Create and setup the AudioManager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                // release MediaPlayer resources BEFORE the MediaPlayer is initialized
                releaseMediaPlayer();

                Word word = numbersList.get(index);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // use the music stream
                        AudioManager.STREAM_MUSIC,
                        // request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have the audio focus

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this,
                            word.getSoundResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the MediaPlayer's resources even
        // if the audiofile didn't finish playing
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing it's resourced
     */
    private void releaseMediaPlayer() {
        if(mMediaPlayer != null) {
            // Regardless of the state of the mMediaPlayer, release its resources
            // because we don't need it anymore
            mMediaPlayer.release();

            // Set the media player to null. For our project, we decided that having
            // the mMediaPlayer set to null is an easy way to decide that it is not
            // being used playing music or used in another state.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, we abandon it here.
            // This also unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

            // Show simple Toast message (dev only)
            Toast.makeText(getApplicationContext(), "Released MediaPlayer",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
