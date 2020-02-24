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

public class PhrasesActivity extends AppCompatActivity {

    /** Handels the playback of the provided soundfiles */
    private MediaPlayer mMediaPlayer;

    /** Handles the AudioFocus */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
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

        final ArrayList<Word> phrasesList = new ArrayList<>();
        phrasesList.add(new Word("Where are you going?", "minto wuksus",
                R.raw.phrase_where_are_you_going));
        phrasesList.add(new Word("What is your name?", "tinnә oyaase'nә",
                R.raw.phrase_what_is_your_name));
        phrasesList.add(new Word("My name is...", "My name is...",
                R.raw.phrase_my_name_is));
        phrasesList.add(new Word("How are you feeling?", "michәksәs?",
                R.raw.phrase_how_are_you_feeling));
        phrasesList.add(new Word("I’m feeling good.", "kuchi achit",
                R.raw.phrase_im_feeling_good));
        phrasesList.add(new Word("Are you coming?", "әәnәs'aa?",
                R.raw.phrase_are_you_coming));
        phrasesList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",
                R.raw.phrase_yes_im_coming));
        phrasesList.add(new Word("I’m coming.", "әәnәm",
                R.raw.phrase_im_coming));
        phrasesList.add(new Word("Let's go.", "yoowutis",
                R.raw.phrase_lets_go));
        phrasesList.add(new Word("Come here", "әnni'nem",
                R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this, phrasesList, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // release MediaPlayer resources BEFORE the MediaPlayer is initialized
                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(adapterView.getContext(),
                        phrasesList.get(i).getSoundResourceId());
                mMediaPlayer.start();

                // release MediaPlayer resources AFTER the MediaPlayer finished playing the
                // audio file which was provided.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
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
