package com.dabinu.apps.chesstimer;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.dabinu.apps.chesstimer.TimerFragment.convertStringToLong;


public class SurgicalRoom extends AppCompatActivity {


    TextView topText, bottomText;
    CountDownTimer countDownTimer;
    Vibrator vibrator;
    ImageButton playpause, reset, stop;
    RelativeLayout noMansLand;
    String minutes;
    boolean isStarted = false, startFromTop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_surgical_room);
//
//        minutes = getIntent().getStringExtra("EXTRA");
//
//        noMansLand = findViewById(R.id.noMansLand);
//        topText = findViewById(R.id.top);
//        bottomText = findViewById(R.id.bottom);
//        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        playpause = findViewById(R.id.playPauseToggle);
//        stop = findViewById(R.id.stop);
//        reset = findViewById(R.id.reset);
//
//
//        playpause.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                if(!isStarted){
//                    Toast.makeText(getApplicationContext(), R.string.startUninitialized, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//
//        playpause.setImageDrawable(getResources().getDrawable(R.drawable.tas_play));
//
//
//        topText.setText(convertLongToString(Long.parseLong(minutes) / 1000));
//        bottomText.setText(convertLongToString(Long.parseLong(minutes) / 1000));
//        topText.setBackgroundColor(Color.YELLOW);
//        bottomText.setBackgroundColor(Color.YELLOW);
//
//
//        topText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                toggler(topText);
//            }
//        });
//
//        bottomText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggler(bottomText);
//            }
//        });


    }


//    public void toggler(final TextView textView){
//        final TextView nextView = ((textView.getId() == R.id.top) ? bottomText : topText);
//
//        countDownTimer = new CountDownTimer(convertStringToLong(nextView.getText().toString()) * 1000, 1000) {
//            @Override
//            public void onTick(long l){
//                playpause.setImageDrawable(getResources().getDrawable(R.drawable.tas_pause));
//                stop.setImageDrawable(getResources().getDrawable(R.drawable.tas_stop));
//                reset.setImageDrawable(getResources().getDrawable(R.drawable.tas_reset));
//                isStarted = true;
//
//                nextView.setTextColor(Color.BLACK);
//                textView.setTextColor(Color.DKGRAY);
//                nextView.setText(convertLongToString(l / 1000));
//                nextView.setBackgroundColor(Color.GREEN);
//                textView.setBackgroundColor(Color.RED);
//
//
//                textView.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View view){
//                        //Do nothing!
//                    }
//                });
//                nextView.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View view){
//                        countDownTimer.cancel();
//                        toggler(nextView);
//                    }
//                });
//
//
//                playpause.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View view){
//                        if(playpause.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tas_pause).getConstantState() && isStarted){
//
//                            if(topText.getCurrentTextColor() == Color.BLACK){
//                                startFromTop = true;
//                            }
//
//                            countDownTimer.cancel();
//                            playpause.setImageResource(R.drawable.tas_play);
//                            Toast.makeText(getApplicationContext(), "Paused", Toast.LENGTH_SHORT).show();
//
//                            topText.setOnClickListener(new View.OnClickListener(){
//                                @Override
//                                public void onClick(View v){
//                                    Toast.makeText(getApplicationContext(), "Click the play button to continue", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            bottomText.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v){
//                                    Toast.makeText(getApplicationContext(), "Click the play button to continue", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//
//
//
//                        else{
//                            Toast.makeText(getApplicationContext(), "Continued", Toast.LENGTH_SHORT).show();
//                            if(startFromTop){
//                                playpause.setImageResource(R.drawable.tas_pause);
//                                startFromTop = false;
//                                toggler(bottomText);
//                            }
//                            else{
//                                playpause.setImageResource(R.drawable.tas_pause);
//                                toggler(topText);
//                            }
//                        }
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFinish(){
//                nextView.setText(R.string.timeUp);
//                vibrator.vibrate(2000);
//                nextView.setBackgroundColor(Color.GRAY);
//                textView.setBackgroundColor(Color.WHITE);
//                playpause.setImageDrawable(getResources().getDrawable(R.drawable.tas_reset));
//                playpause.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(getApplicationContext(), SurgicalRoom.class).putExtra("EXTRA", minutes));
//                    }
//                });
//                stop.setImageDrawable(null);
//                stop.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//                reset.setImageDrawable(null);
//                reset.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//                nextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//            }
//        }.start();
//    }


//    public String convertLongToString(long number){
//
//        String timeString = null;
//
//        if (number >= 600) {
//            timeString = Long.toString(number / 60);
//        } else if (number < 60) {
//            timeString = "00";
//        } else if ((number >= 60) && (number < 600)) {
//            timeString = "0".concat(Long.toString(number / 60));
//        }
//
//        String secondHalfOfTime;
//
//        if ((number % 60) < 10) {
//            secondHalfOfTime = "0".concat(Long.toString(number % 60));
//        } else {
//            secondHalfOfTime = Long.toString(number % 60);
//        }
//
//        timeString += (String.format(":%s", secondHalfOfTime));
//
//        return timeString;
//    }
//
//
//    public static long convertStringToLong(String time){
//
//        char[] all = time.toCharArray();
//
//        long number = ((Long.parseLong(Character.toString(all[0])) * 10) + (Long.parseLong(Character.toString(all[1])))) * 60;
//        number += ((Long.parseLong(Character.toString(all[3])) * 10) + (Long.parseLong(Character.toString(all[4]))));
//
//        return number;
//    }
//
//
//    @Override
//    public void onBackPressed(){
//        if(!isStarted){
//            Intent intent = new Intent(getApplicationContext(), ChooseTimeActivity.class);
//            startActivity(intent);
//        }
//        else{
//            if(playpause.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.tas_pause).getConstantState() && isStarted){
//                new AlertDialog.Builder(this)
//                        .setMessage(R.string.exit_query)
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//                            public void onClick(DialogInterface dialog, int id){
//                                countDownTimer.cancel();
//                                Intent intent = new Intent(getApplicationContext(), ChooseTimeActivity.class);
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton("No", null)
//                        .show();
//            }
//            else{
//                playpause.performClick();
//                new AlertDialog.Builder(this)
//                        .setMessage(R.string.exit_query)
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//                            public void onClick(DialogInterface dialog, int id){
//                                countDownTimer.cancel();
//                                Intent intent = new Intent(getApplicationContext(), ChooseTimeActivity.class);
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                playpause.performClick();
//                            }
//                        })
//                        .show();
//            }
//
//        }
//    }
//
//
//    public void onStopPressed(){
//        if((playpause.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.tas_pause).getConstantState()) && isStarted){
//            new AlertDialog.Builder(this)
//                    .setMessage(R.string.stop_query)
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id){
//                            countDownTimer.cancel();
//                            SurgicalRoom.super.onBackPressed();
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//        }
//        else{
//            playpause.performClick();
//            new AlertDialog.Builder(this)
//                    .setMessage(R.string.stop_query)
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id){
//                            countDownTimer.cancel();
//                            SurgicalRoom.super.onBackPressed();
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            playpause.performClick();
//                        }
//                    })
//                    .show();
//        }
//
//    }
//
//
//    public void onResetPressed(){
//        if(playpause.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.tas_pause).getConstantState() && isStarted){
//            new AlertDialog.Builder(this)
//                    .setMessage(R.string.reset_query)
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//                        public void onClick(DialogInterface dialog, int id){
//                            countDownTimer.cancel();
//                            Intent intent = new Intent(getApplicationContext(), SurgicalRoom.class);
//                            intent.putExtra("EXTRA", minutes);
//                            startActivity(intent);
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//            }
//        else{
//            playpause.performClick();
//            new AlertDialog.Builder(this)
//                    .setMessage(R.string.reset_query)
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//                        public void onClick(DialogInterface dialog, int id){
//                            countDownTimer.cancel();
//                            Intent intent = new Intent(getApplicationContext(), SurgicalRoom.class);
//                            intent.putExtra("EXTRA", minutes);
//                            startActivity(intent);
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            playpause.performClick();
//                        }
//                    })
//                    .show();
//        }
//
//    }
//
//
//    @Override
//    protected void onPause(){
//        onStopPressed();
//        super.onPause();
//    }
//
//
//    @Override
//    protected void onStop() {
//        onStopPressed();
//        super.onStop();
//    }
}