package com.dabinu.apps.chesstimer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AboutUs extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<CharSequence> lipsGuyFillIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        listView = (ListView) findViewById(R.id.connectifyGuy);
//        lipsGuyFillIn = ArrayAdapter.createFromResource(this, R.array.conny, R.layout.forthelistview);
//        listView.setAdapter(lipsGuyFillIn);
//
//        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }
}
