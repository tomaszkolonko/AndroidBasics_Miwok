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
        numbersList.add(new Word("one", "lutti", R.drawable.number_one));
        numbersList.add(new Word("two", "otiiko", R.drawable.number_two));
        numbersList.add(new Word("three", "tolookosu", R.drawable.number_three));
        numbersList.add(new Word("four", "oyyisa", R.drawable.number_four));
        numbersList.add(new Word("five", "massokka", R.drawable.number_five));
        numbersList.add(new Word("six", "temmokka", R.drawable.number_six));
        numbersList.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        numbersList.add(new Word("eigth", "kawinta", R.drawable.number_eight));
        numbersList.add(new Word("nine", "wo'e", R.drawable.number_nine));
        numbersList.add(new Word("ten", "na'aacha", R.drawable.number_ten));


        WordAdapter wordAdapter = new WordAdapter(this, numbersList);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);
    }
}
