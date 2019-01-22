package com.dabinu.apps.chesstimer;


import android.content.Intent;
import android.os.CountDownTimer;
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
    Spinner min;
    ArrayAdapter<CharSequence> minAdapter;
    LinearLayout other;
    ImageButton go, aboutUs;
    boolean isOtherOnClick = false;
    long minuteCalc;
    int exitCount = 0;


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
//        minAdapter = ArrayAdapter.createFromResource(this, R.array.mn, R.layout.spinnerheader);
        minAdapter.setDropDownViewResource(R.layout.spinnercontent);
        min.setAdapter(minAdapter);

//        final Intent toAbout = new Intent(this, AboutUs.class);
//        aboutUs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(toAbout);
//            }
//        });

        final Intent toTheSurgicalRoom = new Intent(this, SurgicalRoom.class);

        list = findViewById(R.id.listOfAll);
        list.setAdapter(ArrayAdapter.createFromResource(this, R.array.time, R.layout.forthelistview));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){

                switch(position){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        startActivity(toTheSurgicalRoom.putExtra("EXTRA", Long.toString(1000 * 60 * ((position + 1) * 5))));
                        break;
                    case 5:
                        other.setVisibility((isOtherOnClick ? View.GONE : View.VISIBLE));
                        isOtherOnClick = !isOtherOnClick;
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
                    startActivity(toTheSurgicalRoom.putExtra("EXTRA", Long.toString(1000 * 60 * (minuteCalc + 1))));
                }
            }
        });

    }



    @Override
    public void onBackPressed(){
            exitCount++;

            switch(exitCount){
                case 1:
                    Toast.makeText(this, "Press back again to exit", Toast.LENGTH_LONG).show();
                    new CountDownTimer(3000, 1000){
                        @Override
                        public void onTick(long millisUntilFinished){
                        }

                        @Override
                        public void onFinish(){
                            exitCount = 0;
                        }
                    }.start();
                    break;
                case 2:
                    startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        }

}