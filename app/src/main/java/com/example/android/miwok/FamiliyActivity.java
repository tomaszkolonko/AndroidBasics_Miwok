package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamiliyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_container);

        ArrayList<Word> familyList = new ArrayList<>();
        familyList.add(new Word("father", "әpә"));
        familyList.add(new Word("mother", "әṭa"));
        familyList.add(new Word("son", "angsi"));
        familyList.add(new Word("daughter", "tune"));
        familyList.add(new Word("older brother", "taachi"));
        familyList.add(new Word("younger brother", "chalitti"));
        familyList.add(new Word("older sister", "teṭe"));
        familyList.add(new Word("younger sister", "kolliti"));
        familyList.add(new Word("grandmother", "ama"));
        familyList.add(new Word("grandfather", "paapa"));

        WordAdapter wordAdapter = new WordAdapter(this, familyList);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);
    }
}
