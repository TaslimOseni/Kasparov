package com.dabinu.apps.chesstimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button five, ten, fif, twen, thirt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        five = (Button) findViewById(R.id.five);
        ten = (Button) findViewById(R.id.ten);
        fif = (Button) findViewById(R.id.fif);
        twen = (Button) findViewById(R.id.twen);
        thirt = (Button) findViewById(R.id.thirt);

        final Intent intent = new Intent(this, SurgicalRoom.class);


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

        fif.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String passed = Long.toString(1000 * 60 * 15);
                intent.putExtra("EXTRA", passed);
                startActivity(intent);
            }
        });

        twen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String passed = Long.toString(1000 * 60 * 20);
                intent.putExtra("EXTRA", passed);
                startActivity(intent);
            }
        });

        thirt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String passed = Long.toString(1000 * 60 * 30);
                intent.putExtra("EXTRA", passed);
                startActivity(intent);
            }
        });

    }


}
