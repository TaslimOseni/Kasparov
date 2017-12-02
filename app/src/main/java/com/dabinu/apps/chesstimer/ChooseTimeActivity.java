package com.dabinu.apps.chesstimer;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class ChooseTimeActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<CharSequence> adapter;
    Spinner min;
    ArrayAdapter<CharSequence> minAdapter;
    LinearLayout other;
    ImageButton go, aboutUs;
    boolean isOtherOnClick = false;
    long minuteCalc;
    String passed;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_time);

        overridePendingTransition(0, 0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        min = findViewById(R.id.min);
        go = findViewById(R.id.goToNext);
        other = findViewById(R.id.other);
        aboutUs = findViewById(R.id.aboutUs);
        minAdapter = ArrayAdapter.createFromResource(this, R.array.mn, R.layout.spinnerheader);
        minAdapter.setDropDownViewResource(R.layout.spinnercontent);
        min.setAdapter(minAdapter);

        final Intent toAbout = new Intent(this, AboutUs.class);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toAbout);
            }
        });

        final Intent toTheSurgicalrRoom = new Intent(this, SurgicalRoom.class);

        list = findViewById(R.id.listOfAll);
        adapter = ArrayAdapter.createFromResource(this, R.array.time, R.layout.forthelistview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
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
                    case 5:
                        if(isOtherOnClick){
                            other.setVisibility(View.INVISIBLE);
                            isOtherOnClick = false;
                        }
                        else{
                            other.setVisibility(View.VISIBLE);
                            isOtherOnClick = true;
                        }

                        break;
                }
            }
        });



        min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                minuteCalc = adapterView.getItemIdAtPosition(i) - 1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(minuteCalc == -1){
                    Toast.makeText(getApplicationContext(), "Select a valid time", Toast.LENGTH_LONG).show();
                }
                else{
                    passed = Long.toString(1000 * 60 * (minuteCalc + 1));
                    toTheSurgicalrRoom.putExtra("EXTRA", passed);
                    startActivity(toTheSurgicalrRoom);
                }
            }
        });



    }



    @Override
    public void onBackPressed() {
        Intent inte = new Intent(this, GameType.class);
        startActivity(inte);
    }
}
