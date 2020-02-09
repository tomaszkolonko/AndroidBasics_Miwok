package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_container);

        ArrayList<Word> numbersList = new ArrayList<>();
        numbersList.add(new Word("one", "lutti"));
        numbersList.add(new Word("two", "otiiko"));
        numbersList.add(new Word("three", "tolookosu"));
        numbersList.add(new Word("four", "oyyisa"));
        numbersList.add(new Word("five", "massokka"));
        numbersList.add(new Word("six", "temmokka"));
        numbersList.add(new Word("seven", "kenekaku"));
        numbersList.add(new Word("eigth", "kawinta"));
        numbersList.add(new Word("nine", "wo'e"));
        numbersList.add(new Word("ten", "na'aacha"));


        WordAdapter wordAdapter = new WordAdapter(this, numbersList);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);
    }
}
