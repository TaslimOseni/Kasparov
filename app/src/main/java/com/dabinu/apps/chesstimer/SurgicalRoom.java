package com.dabinu.apps.chesstimer;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SurgicalRoom extends AppCompatActivity {


    TextView topText, bottomText;
    CountDownTimer countDownTimer;
    Vibrator vibrator;
    ImageButton playpause, reset, stop;
    LinearLayout noMansLand;
    String minutes;
    boolean isStarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_surgical_room);

        isStarted = false;


        noMansLand = (LinearLayout) findViewById(R.id.noMansLand);

        minutes = getIntent().getStringExtra("EXTRA");

        topText = (TextView) findViewById(R.id.top);
        bottomText = (TextView) findViewById(R.id.bottom);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        playpause = (ImageButton) findViewById(R.id.playPauseToggle);
        stop = (ImageButton) findViewById(R.id.stop);
        reset = (ImageButton) findViewById(R.id.reset);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStarted){
                    Toast.makeText(getApplicationContext(), R.string.startUninitialized, Toast.LENGTH_LONG).show();
                }
            }
        });


        playpause.setImageDrawable(getDrawable(R.drawable.tas_play));


        topText.setText(convertLongToString(Long.parseLong(minutes) / 1000));
        bottomText.setText(convertLongToString(Long.parseLong(minutes) / 1000));
        topText.setBackgroundColor(Color.YELLOW);
        bottomText.setBackgroundColor(Color.YELLOW);


        topText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theTextViewToggler(topText);
                playpause.setImageDrawable(getDrawable(R.drawable.tas_pause));
                stop.setImageDrawable(getDrawable(R.drawable.tas_stop));
                reset.setImageDrawable(getDrawable(R.drawable.tas_reset));
            }
        });

        bottomText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theTextViewToggler(bottomText);
                playpause.setImageDrawable(getDrawable(R.drawable.tas_pause));
                stop.setImageDrawable(getDrawable(R.drawable.tas_stop));
                reset.setImageDrawable(getDrawable(R.drawable.tas_reset));
            }
        });


    }


    public void theTextViewToggler(final TextView textView) {

        final TextView nextView;

        if (textView.getId() == R.id.top) {
            nextView = bottomText;
            }
        else{
            nextView = topText;
        }


        countDownTimer = new CountDownTimer(convertStringToLong(nextView.getText().toString()) * 1000, 1000) {
            @Override
            public void onTick(long l) {
                nextView.setText(convertLongToString(l / 1000));
                nextView.setBackgroundColor(Color.GREEN);
                textView.setBackgroundColor(Color.RED);
                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onStopPressed();
                    }
                });
                nextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countDownTimer.cancel();
                        theTextViewToggler(nextView);
                    }
                });

            }

            @Override
            public void onFinish(){
                nextView.setText(R.string.timeUp);
                long pattern[] = {50,10};
                vibrator.vibrate(pattern, 1);
                //todo: Add my voice shouting time up!
                nextView.setBackgroundColor(Color.GRAY);
                textView.setBackgroundColor(Color.WHITE);
                playpause.setImageDrawable(getDrawable(R.drawable.tas_reset));
                playpause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), SurgicalRoom.class);
                        intent.putExtra("EXTRA", minutes);
                        startActivity(intent);
                    }
                });
                stop.setImageDrawable(null);
                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                reset.setImageDrawable(null);
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                nextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        }.start();
    }


    public String convertLongToString(long number) {

        String timeString = null;

        if (number >= 600) {
            timeString = Long.toString(number / 60);
        } else if (number < 60) {
            timeString = "00";
        } else if ((number >= 60) && (number < 600)) {
            timeString = "0".concat(Long.toString(number / 60));
        }

        String secondHalfOfTime;

        if ((number % 60) < 10) {
            secondHalfOfTime = "0".concat(Long.toString(number % 60));
        } else {
            secondHalfOfTime = Long.toString(number % 60);
        }

        timeString += (String.format(":%s", secondHalfOfTime));

        return timeString;
    }


    public static long convertStringToLong(String time){

        char[] all = time.toCharArray();

        long number = ((Long.parseLong(Character.toString(all[0])) * 10) + (Long.parseLong(Character.toString(all[1])))) * 60;
        number += ((Long.parseLong(Character.toString(all[3])) * 10) + (Long.parseLong(Character.toString(all[4]))));

        return number;
    }


    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage(R.string.exit_query)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SurgicalRoom.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    public void onStopPressed(){
        new AlertDialog.Builder(this)
                .setMessage(R.string.stop_query)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SurgicalRoom.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}