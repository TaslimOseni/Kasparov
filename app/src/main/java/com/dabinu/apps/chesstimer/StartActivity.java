package com.dabinu.apps.chesstimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


public class StartActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<CharSequence> adapter;
    Spinner hr, min;
    ArrayAdapter<CharSequence> hrAdapter, minAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        hr = (Spinner) findViewById(R.id.hr);
        min = (Spinner) findViewById(R.id.min);
        hrAdapter = ArrayAdapter.createFromResource(this, R.array.hrs, R.layout.timespinner);
        minAdapter = ArrayAdapter.createFromResource(this, R.array.mn, R.layout.timespinner);
        hr.setAdapter(hrAdapter);
        min.setAdapter(minAdapter);


        final Intent toTheSurgicalrRoom = new Intent(this, SurgicalRoom.class);

        list = (ListView) findViewById(R.id.listOfAll);
        adapter = ArrayAdapter.createFromResource(this, R.array.time, R.layout.listitemmock);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                String passed;
                switch(position){
                    case 0:
                        passed = Long.toString(1000 * 60 * 5);
                        toTheSurgicalrRoom.putExtra("EXTRA", passed);
                        startActivity(toTheSurgicalrRoom);
                        break;
                    case 1:
                        passed = Long.toString(1000 * 60 * 10);
                        toTheSurgicalrRoom.putExtra("EXTRA", passed);
                        startActivity(toTheSurgicalrRoom);
                        break;
                    case 2:
                        passed = Long.toString(1000 * 60 * 15);
                        toTheSurgicalrRoom.putExtra("EXTRA", passed);
                        startActivity(toTheSurgicalrRoom);
                        break;
                    case 3:
                        passed = Long.toString(1000 * 60 * 20);
                        toTheSurgicalrRoom.putExtra("EXTRA", passed);
                        startActivity(toTheSurgicalrRoom);
                        break;
                    case 4:
                        passed = Long.toString(1000 * 60 * 30);
                        toTheSurgicalrRoom.putExtra("EXTRA", passed);
                        startActivity(toTheSurgicalrRoom);
                        break;
                }
            }
        });


    }


}
