package com.dabinu.apps.chesstimer;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */

public class TimerFragment extends android.app.Fragment implements View.OnClickListener{


    CardView playerA, playerB;
    TextView timerA, timerB, movesA, movesB;
    ImageButton pause, settings, reset;
    SharedPreferences gameDetails, appDetails;
    boolean shouldRing, shouldVibrate;
    int active_color, inactive_color, background_color, default_color;
    long duration, delay;
    FragmentTransaction fragmentTransaction;
    RelativeLayout background;
    CountDownTimer countDownTimer;
    Vibrator vibrator;


    public TimerFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        loadGameDetails();
        loadAllSettings();
        init(view);


        return view;
    }


    public void init(View view){

        background = view.findViewById(R.id.background);
        background.setBackgroundColor(getResources().getColor(background_color));

        playerA = view.findViewById(R.id.playerA);
        playerA.setCardBackgroundColor(getResources().getColor(default_color));
        playerA.setOnClickListener(this);

        playerB = view.findViewById(R.id.playerB);
        playerB.setCardBackgroundColor(getResources().getColor(default_color));
        playerB.setOnClickListener(this);

        timerA = view.findViewById(R.id.timerA);
        timerA.setText(convertLongToString(duration / 1000));

        timerB = view.findViewById(R.id.timerB);
        timerB.setText(convertLongToString(duration / 1000));

        movesA = view.findViewById(R.id.movesA);
        movesB = view.findViewById(R.id.movesB);

        pause = view.findViewById(R.id.pauseBut);
        pause.setOnClickListener(this);

        reset = view.findViewById(R.id.resetBut);
        reset.setOnClickListener(this);

        settings = view.findViewById(R.id.settingsBut);
        settings.setOnClickListener(this);

        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

    }


    public void loadAllSettings(){
        appDetails = getActivity().getSharedPreferences("DETAILS", Context.MODE_PRIVATE);

        shouldRing = appDetails.getString("ring", "true").equals("true");
        shouldVibrate = appDetails.getString("vibrate", "true").equals("true");

        active_color = getColorFromString(appDetails.getString("active_color", "Green"));
        inactive_color = getColorFromString(appDetails.getString("inactive_color", "Red"));
        default_color = getColorFromString(appDetails.getString("default_color", "Gray"));
        background_color = getColorFromString(appDetails.getString("background_color", "Deep-Gray"));

    }



    public void loadGameDetails(){
        gameDetails = getActivity().getSharedPreferences("GAME", Context.MODE_PRIVATE);

        duration = Long.parseLong(gameDetails.getString("duration", "5")) * 60 * 1000;
        delay = Long.parseLong(gameDetails.getString("delay", "0")) * 1000;
    }



    public int getColorFromString(String colorName){
        switch(colorName){
            case "Red":
                return R.color.red;
            case "Pink":
                return R.color.pink;
            case "Orange":
                return R.color.orange;
            case "Purple":
                return R.color.purple;
            case "Yellow":
                return R.color.yellow;
            case "Gray":
                return R.color.gray;
            case "Green":
                return R.color.green;
            case "Black":
                return R.color.black;
            case "Blue":
                return R.color.blue;
            case "Deep-Gray":
                return R.color.deep_gray;
        }

        return android.R.color.holo_purple;
    }


    @Override
    public void onClick(View view){
        if(view.getId() == R.id.settingsBut){
            goToFragement(new SettingsFragment());
        }

        else if(view.getId() == R.id.resetBut){
            goToFragement(new TimerFragment());
        }

        else if(view.getId() == R.id.playerA){
            if(delay == 0){
                playerToggler(playerA);
            }
            else{
                initialToggler(playerA);
            }
        }

        else if(view.getId() == R.id.playerB){
            if(delay == 0){
                playerToggler(playerB);
            }
            else{
                initialToggler(playerB);
            }
        }


        else if(view.getId() == R.id.pauseBut){

            if(countDownTimer != null){
                countDownTimer.cancel();
            }

            Toast.makeText(getActivity().getApplicationContext(), "Paused", Toast.LENGTH_SHORT).show();

            playerA.setCardBackgroundColor(getResources().getColor(default_color));
            playerB.setCardBackgroundColor(getResources().getColor(default_color));

            settings.setVisibility(View.VISIBLE);
            reset.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
        }
    }


    public void goToFragement(android.app.Fragment fragment){
        fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


    public void playerToggler(final CardView player){
        final CardView nextPlayer = ((player.getId() == R.id.playerA) ? playerB : playerA);
        final TextView nextTextView = (nextPlayer.getId() == R.id.playerA) ? timerA : timerB;
        TextView thisMoveView = (nextPlayer.getId() == R.id.playerA) ? movesB : movesA;

        reset.setVisibility(View.GONE);
        settings.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);

        thisMoveView.setText(String.format("Moves: %d", (Integer.parseInt(thisMoveView.getText().toString().trim().split(" ")[1])) + 1));

        player.setCardBackgroundColor(getResources().getColor(inactive_color));
        nextPlayer.setCardBackgroundColor(getResources().getColor(active_color));

        countDownTimer = new CountDownTimer((convertStringToLong(nextTextView.getText().toString().trim()) * 1000), 1000){
            @Override
            public void onTick(long l){
                nextTextView.setText(convertLongToString(l / 1000));
                nextPlayer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countDownTimer.cancel();
                        if(delay == 0){
                            playerToggler(nextPlayer);
                        }
                        else{
                            initialToggler(nextPlayer);
                        }

                    }
                });
                player.setOnClickListener(null);

            }

            @Override
            public void onFinish(){
                nextTextView.setText("Time up!!!");

                reset.setVisibility(View.VISIBLE);
                settings.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);

                playerA.setCardBackgroundColor(getResources().getColor(default_color));
                playerB.setCardBackgroundColor(getResources().getColor(default_color));

                playerA.setOnClickListener(null);
                playerB.setOnClickListener(null);

                if(shouldVibrate){
                    vibrator.vibrate(2000);
                }
                if(shouldRing){
                    try{
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getActivity().getApplicationContext(), notification);
                        r.play();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }

        }.start();
    }





    public void initialToggler(final CardView player){
        final CardView nextPlayer = ((player.getId() == R.id.playerA) ? playerB : playerA);
        final TextView nextTextView = (nextPlayer.getId() == R.id.playerA) ? timerA : timerB;
        TextView thisMoveView = (nextPlayer.getId() == R.id.playerA) ? movesB : movesA;

        reset.setVisibility(View.GONE);
        settings.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);

        thisMoveView.setText(String.format("Moves: %d", (Integer.parseInt(thisMoveView.getText().toString().trim().split(" ")[1])) + 1));

        player.setCardBackgroundColor(getResources().getColor(inactive_color));
        nextPlayer.setCardBackgroundColor(getResources().getColor(active_color));

        countDownTimer = new CountDownTimer(delay, 1000){
            @Override
            public void onTick(long l){
                nextPlayer.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        countDownTimer.cancel();
                        if(delay == 0){
                            playerToggler(nextPlayer);
                        }
                        else{
                            initialToggler(nextPlayer);
                        }
                    }
                });
                player.setOnClickListener(null);
            }

            @Override
            public void onFinish(){
                playerToggler(player);
            }

        }.start();
    }






    public static long convertStringToLong(String time){

        char[] all = time.toCharArray();

        long number = ((Long.parseLong(Character.toString(all[0])) * 10) + (Long.parseLong(Character.toString(all[1])))) * 60;
        number += ((Long.parseLong(Character.toString(all[3])) * 10) + (Long.parseLong(Character.toString(all[4]))));

        return number;
    }



    public String convertLongToString(long number){

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


    @Override
    public void onStop(){
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
        super.onStop();
    }

    @Override
    public void onPause(){
        if(countDownTimer != null){
            countDownTimer.cancel();
        }

        super.onPause();
    }
}