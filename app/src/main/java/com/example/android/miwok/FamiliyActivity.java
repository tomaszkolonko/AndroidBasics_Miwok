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
        familyList.add(new Word("father", "әpә", R.drawable.family_father));
        familyList.add(new Word("mother", "әṭa", R.drawable.family_mother));
        familyList.add(new Word("son", "angsi", R.drawable.family_son));
        familyList.add(new Word("daughter", "tune", R.drawable.family_daughter));
        familyList.add(new Word("older brother", "taachi", R.drawable.family_older_brother));
        familyList.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother));
        familyList.add(new Word("older sister", "teṭe", R.drawable.family_older_sister));
        familyList.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister));
        familyList.add(new Word("grandmother", "ama", R.drawable.family_grandmother));
        familyList.add(new Word("grandfather", "paapa", R.drawable.family_grandfather));

        WordAdapter wordAdapter = new WordAdapter(this, familyList, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.wordListContainer);
        listView.setAdapter(wordAdapter);
    }
}
