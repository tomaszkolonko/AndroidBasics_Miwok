package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NumbersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NumbersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumbersFragment extends Fragment {

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

    private OnFragmentInteractionListener mListener;

    public NumbersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumbersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumbersFragment newInstance(String param1, String param2) {
        NumbersFragment fragment = new NumbersFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list_container, container, false);

        // Create and setup the AudioManager to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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


        WordAdapter wordAdapter = new WordAdapter(getActivity(), numbersList, R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.wordListContainer);
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
                    mMediaPlayer = MediaPlayer.create(getActivity(),
                            word.getSoundResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
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
            Toast.makeText(getActivity(), "Released MediaPlayer",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
