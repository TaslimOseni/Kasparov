package com.dabinu.apps.chesstimer;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;




public class MenuFragment extends android.app.Fragment implements Serializable, View.OnClickListener{


    Spinner time_spinner, mode_spinner;
    TextView start_game;
    ImageButton settings, about;
    ArrayList<GameMode> allModes;
    ArrayList<String> allModeNames, allTimeInMinutes;
    FragmentTransaction fragmentTransaction;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public MenuFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        getAllModes();
        getAllTimeInMinutes();
        init(view);

        return view;
    }


    public void init(View view){
        sharedPreferences = getActivity().getSharedPreferences("GAME", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        start_game = view.findViewById(R.id.start_game);
        start_game.setOnClickListener(this);

        settings = view.findViewById(R.id.settings);
        about = view.findViewById(R.id.about);

        settings.setOnClickListener(this);
        about.setOnClickListener(this);


        time_spinner = view.findViewById(R.id.time_spinner);
        ArrayAdapter time_adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinnerheader, allTimeInMinutes);
        time_adapter.setDropDownViewResource(R.layout.drop_down);
        time_spinner.setAdapter(time_adapter);
        time_spinner.setSelection(4);


        mode_spinner = view.findViewById(R.id.mode_spinner);
        ArrayAdapter mode_adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinnerheader, allModeNames);
        mode_adapter.setDropDownViewResource(R.layout.drop_down);
        mode_spinner.setAdapter(mode_adapter);
        mode_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                if(i == 0){
                    setTimerAdapter(true);
                }
                else if(i == allModes.size() - 1){
                    goToFragement(new NewGameModeFragment());
                }
                else{
                    setTimerAdapter(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });

    }


    public void getAllModes(){
        allModes = new ArrayList<>();
        allModeNames = new ArrayList<>();

        allModes.add(new GameMode("Normal", "0", "0"));
        allModeNames.add("Normal");


        File[] files = getActivity().getFilesDir().listFiles();

        for(File i: files){
            try{
                GameMode gameMode = (GameMode) (new ObjectInputStream(getActivity().openFileInput(i.getName())).readObject());
                allModes.add(gameMode);
                allModeNames.add(String.format("%s %s|%s", gameMode.getName(), gameMode.getDuration(), gameMode.getDelay()));
            }

            catch(Exception e){

            }
        }


        allModes.add(new GameMode("Fischer Blitz", "0", "5"));
        allModes.add(new GameMode("Delay Bullet", "2", "1"));
        allModes.add(new GameMode("Fischer", "5", "5"));
        allModes.add(new GameMode("Add new", "", ""));

        allModeNames.add("Fischer Blitz 5|0");
        allModeNames.add("Delay Bullet 1|2");
        allModeNames.add("Fischer 5|5");
        allModeNames.add("ADD NEW MODE");

    }


    public void getAllTimeInMinutes(){
        allTimeInMinutes = new ArrayList<>();

        for(int i = 1; i <= 60; i++){
            allTimeInMinutes.add(String.format("%d min", i));
        }
    }


    public void goToFragement(Fragment fragment){
        fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.settings){
            goToFragement(new SettingsFragment());
        }
        else if(view.getId() == R.id.about){
            goToFragement(new AboutFragment());
        }
        else if(view.getId() == R.id.start_game){
            if(mode_spinner.getSelectedItem().equals("Normal")){
                editor.putString("duration", ((String) time_spinner.getSelectedItem()).split(" ")[0]);
                editor.putString("delay", "0");
                editor.apply();
            }
            else{
                editor.putString("duration", allModes.get(allModeNames.indexOf((String) mode_spinner.getSelectedItem())).getDuration());
                editor.putString("delay", allModes.get(allModeNames.indexOf((String) mode_spinner.getSelectedItem())).getDelay());
                editor.apply();
            }
            goToFragement(new TimerFragment());
        }
    }


    public void setTimerAdapter(boolean isZero){
        if(isZero){
            ArrayAdapter time_adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinnerheader, allTimeInMinutes);
            time_adapter.setDropDownViewResource(R.layout.drop_down);
            time_spinner.setAdapter(time_adapter);
            time_spinner.setSelection(4);
        }
        else{
            ArrayAdapter time_adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.no_time, R.layout.spinnerheader);
            time_adapter.setDropDownViewResource(R.layout.drop_down);
            time_spinner.setAdapter(time_adapter);
        }
    }

}