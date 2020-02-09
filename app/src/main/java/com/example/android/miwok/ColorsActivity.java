package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_container);

        ArrayList<Word> colorsList = new ArrayList<>();
        colorsList.add(new Word("red", "weṭeṭṭi"));
        colorsList.add(new Word("green", "chokokki"));
        colorsList.add(new Word("brown", "ṭakaakki"));
        colorsList.add(new Word("gray", "ṭopoppi"));
        colorsList.add(new Word("black", "kululli"));
        colorsList.add(new Word("white", "kelelli"));
        colorsList.add(new Word("dusty yello", "ṭopiisә"));
        colorsList.add(new Word("mustard yellow", "chiwiiṭә"));

        WordAdapter wordAdapter = new WordAdapter(this, colorsList);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);
    }
}
