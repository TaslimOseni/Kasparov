package com.dabinu.apps.chesstimer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class StartActivity extends AppCompatActivity {

    Button five, ten, fifteen, twenty, thirty, other;
    RelativeLayout maam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        five = (Button) findViewById(R.id.five);
        ten = (Button) findViewById(R.id.ten);
        fifteen = (Button) findViewById(R.id.fif);
        twenty = (Button) findViewById(R.id.twen);
        thirty = (Button) findViewById(R.id.thirt);
        other = (Button) findViewById(R.id.other);
        maam = (RelativeLayout) findViewById(R.id.maam);


        //Intent to got to the main activity: Named it surgical activity.
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

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(getApplicationContext());
                editText.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText.setBackgroundColor(Color.WHITE);
                //todo: set a layout under that'd hold two spinners and one image view
                maam.addView(editText);
            }
        });

    }


}
