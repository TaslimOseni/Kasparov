package com.dabinu.apps.chesstimer;



import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;

public class SurgicalRoom extends AppCompatActivity {


    TextView topText, bottomText;
    CountDownTimer countDownTimer;
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_surgical_room);


        String minutes = getIntent().getStringExtra("EXTRA");

        topText = (TextView) findViewById(R.id.top);
        bottomText = (TextView) findViewById(R.id.bottom);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        topText.setText(convertToTime(Long.parseLong(minutes) / 1000));
        bottomText.setText(convertToTime(Long.parseLong(minutes) / 1000));
        topText.setBackgroundColor(Color.YELLOW);
        bottomText.setBackgroundColor(Color.YELLOW);


        topText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisIsVeryAnnoyingBecauseIhaveBeenTryingToDeviseTheBestWayToDoThisForDays(topText);
            }
        });

        bottomText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisIsVeryAnnoyingBecauseIhaveBeenTryingToDeviseTheBestWayToDoThisForDays(bottomText);
            }
        });
    }


    public void thisIsVeryAnnoyingBecauseIhaveBeenTryingToDeviseTheBestWayToDoThisForDays(final TextView textView) {

        final TextView nextView;

        if (textView.getId() == R.id.top) {
            nextView = bottomText;
        } else {
            nextView = topText;
        }

        countDownTimer = null;

        countDownTimer = new CountDownTimer(convertToLong(nextView.getText().toString()) * 1000, 1000) {
            @Override
            public void onTick(long l) {
                nextView.setText(convertToTime(l / 1000));
                nextView.setBackgroundColor(Color.GREEN);
                textView.setBackgroundColor(Color.RED);
                nextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countDownTimer.cancel();
                        thisIsVeryAnnoyingBecauseIhaveBeenTryingToDeviseTheBestWayToDoThisForDays(nextView);
                    }
                });
            }

            @Override
            public void onFinish() {
                nextView.setText("TIme up!!!");
                vibrator.vibrate(3000);
                //todo: fix the onClick() when timeIsUp
            }
        }.start();
    }


    public String convertToTime(long number) {

        String time = null;

        if (number >= 600) {
            time = Long.toString(number / 60);
        } else if (number < 60) {
            time = "00";
        } else if ((number >= 60) && (number < 600)) {
            time = "0".concat(Long.toString(number / 60));
        }

        String varii = null;

        if ((number % 60) < 10) {
            varii = "0".concat(Long.toString(number % 60));
        } else {
            varii = Long.toString(number % 60);
        }

        time += (String.format(":%s", varii));

        return time;
    }


    public static long convertToLong(String time) {
        long number = 0;

        char[] all = time.toCharArray();

        number = ((Long.parseLong(Character.toString(all[0])) * 10) + (Long.parseLong(Character.toString(all[1])))) * 60;
        number += ((Long.parseLong(Character.toString(all[3])) * 10) + (Long.parseLong(Character.toString(all[4]))));

        return number;
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
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
