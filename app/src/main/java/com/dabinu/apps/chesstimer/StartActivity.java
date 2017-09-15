package com.dabinu.apps.chesstimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button five, ten, fifteen, twenty, thirty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        five = (Button) findViewById(R.id.five);
        ten = (Button) findViewById(R.id.ten);
        fifteen = (Button) findViewById(R.id.fif);
        twenty = (Button) findViewById(R.id.twen);
        thirty = (Button) findViewById(R.id.thirt);

        //Intent to got o the main activity: Named it surgical activity.
        final Intent intent = new Intent(this, SurgicalRoom.class);


        //I set onClick listeners for EACH of the buttons (inefficient method, I know); passed a String through the intent.
        five.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String passed = Long.toString(1000 * 60 * 5);
                intent.putExtra("EXTRA", passed);
                startActivity(intent);
            }
        });

        ten.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String passed = Long.toString(1000 * 60 * 10);
                intent.putExtra("EXTRA", passed);
                startActivity(intent);
            }
        });

        fifteen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String passed = Long.toString(1000 * 60 * 15);
                intent.putExtra("EXTRA", passed);
                startActivity(intent);
            }
        });

        twenty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String passed = Long.toString(1000 * 60 * 20);
                intent.putExtra("EXTRA", passed);
                startActivity(intent);
            }
        });

        thirty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String passed = Long.toString(1000 * 60 * 30);
                intent.putExtra("EXTRA", passed);
                startActivity(intent);
            }
        });

    }


}
