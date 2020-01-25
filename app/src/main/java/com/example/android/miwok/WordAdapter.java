package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Word word = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list,
                    parent, false);
        }

        // Lookup view for data population
        TextView defaultWord = (TextView) convertView.findViewById(R.id.defaultWord);
        TextView miwokWord = (TextView) convertView.findViewById(R.id.miwokWord);

        // Populate the data into the templat view using the data object
        defaultWord.setText(word.getDefaultTranslation());
        miwokWord.setText(word.getMiwokTranslation());

        // Return the completed view to render on screen
        return convertView;
    }
}
