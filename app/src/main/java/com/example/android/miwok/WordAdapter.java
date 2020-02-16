package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColor;
    final private MediaPlayer mediaPlayer = new MediaPlayer();

    public WordAdapter(Context context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        this.mColor = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Word word = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.single_word_view_element, parent, false);
        }

        // Lookup view for data population
        TextView defaultWord = (TextView) convertView.findViewById(R.id.default_word);
        TextView miwokWord = (TextView) convertView.findViewById(R.id.miwok_word);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.word_image);

        configureAndPopulateView(word, defaultWord, miwokWord, imageView);
        setColorOfTextView(convertView);

        // Return the completed view to render on screen
        return convertView;
    }

    /**
     * This method configures and populates the Views
     *
     * @param word
     * @param defaultWord
     * @param miwokWord
     * @param imageView
     */
    private void configureAndPopulateView(Word word, TextView defaultWord, TextView miwokWord,
                                          ImageView imageView) {
        // Populate the data into the template view using the data object
        defaultWord.setText(word.getDefaultTranslation());
        miwokWord.setText(word.getMiwokTranslation());

        if(word.hasImage()) {
            imageView.setImageResource(word.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    /**
     * This sets the background color of both textViews
     *
     * @param convertView
     */
    private void setColorOfTextView(View convertView) {
        LinearLayout test = (LinearLayout) convertView.findViewById(R.id.textContainer);
        int color = ContextCompat.getColor(getContext(), mColor);
        test.setBackgroundColor(color);
    }
}
