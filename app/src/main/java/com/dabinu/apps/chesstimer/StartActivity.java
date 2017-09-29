package com.dabinu.apps.chesstimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class StartActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final Intent toTheSurgicalrRoom = new Intent(this, SurgicalRoom.class);

        list = (ListView) findViewById(R.id.listOfAll);
        adapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                String passed = null;
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
