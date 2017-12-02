package com.dabinu.apps.chesstimer;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class GameType extends AppCompatActivity {


    TextView one, two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_type);

        overridePendingTransition(0, 0);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);

        final Intent intent = new Intent(this, ChooseTimeActivity.class);
        final Intent intent2 = new Intent(this, ConnectionWiFi.class);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
    }
}
