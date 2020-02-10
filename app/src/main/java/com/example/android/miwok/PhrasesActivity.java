package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_container);

        ArrayList<Word> phrasesList = new ArrayList<>();
        phrasesList.add(new Word("Where are you going?", "minto wuksus"));
        phrasesList.add(new Word("What is your name?", "tinnә oyaase'nә"));
        phrasesList.add(new Word("My name is...", "My name is..."));
        phrasesList.add(new Word("How are you feeling?", "michәksәs?"));
        phrasesList.add(new Word("I’m feeling good.", "kuchi achit"));
        phrasesList.add(new Word("Are you coming?", "әәnәs'aa?"));
        phrasesList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm"));
        phrasesList.add(new Word("I’m coming.", "әәnәm"));
        phrasesList.add(new Word("Let's go.", "yoowutis"));
        phrasesList.add(new Word("Come here", "әnni'nem"));

        WordAdapter wordAdapter = new WordAdapter(this, phrasesList, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);
    }
}
