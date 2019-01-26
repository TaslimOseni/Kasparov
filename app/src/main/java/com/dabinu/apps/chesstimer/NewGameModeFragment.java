package com.dabinu.apps.chesstimer;


import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;




public class NewGameModeFragment extends android.app.Fragment implements View.OnClickListener{


    TextView save;
    Spinner delay, duration;
    EditText name;
    ArrayList<String> delay_units, duration_units;

    public NewGameModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_new_game_mode, container, false);
        init(view);


        return view;
    }


    public void init(View view){
        initDelayUnits();

        name = view.findViewById(R.id.name);

        delay = view.findViewById(R.id.delay);
        ArrayAdapter delay_adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinnerheader, delay_units);
        delay_adapter.setDropDownViewResource(R.layout.drop_down);
        delay.setAdapter(delay_adapter);

        duration = view.findViewById(R.id.duration);
        ArrayAdapter duration_adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinnerheader, duration_units);
        duration_adapter.setDropDownViewResource(R.layout.drop_down);
        duration.setAdapter(duration_adapter);

        save = view.findViewById(R.id.save);
        save.setOnClickListener(this);
    }


    public void initDelayUnits(){
        delay_units = new ArrayList<>();
        duration_units = new ArrayList<>();

        delay_units.add("No delay");
        delay_units.add("1 second");

        duration_units.add("1 minute");

        for(int i = 2; i <= 60; i++){
            duration_units.add(String.format("%s minutes", Integer.toString(i)));
            if(i <= 15){
                delay_units.add(String.format("%s seconds", Integer.toString(i)));
            }
        }
    }

    @Override
    public void onClick(View view){
        if(name.getText().toString().trim().equals("")){
            name.setError("Input a valid name..");
        }
        else{
            GameMode gameMode = new GameMode(name.getText().toString().trim(), ((String) delay.getSelectedItem()).equals("No delay") ? "0" : ((String) delay.getSelectedItem()).split(" ")[0], ((String) duration.getSelectedItem()).split(" ")[0]);

            Random random = new Random();
            int code = random.nextInt(9000);
            try{
                FileOutputStream fos = getActivity().openFileOutput(Integer.toString(code), Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(gameMode);
                oos.close();
                fos.close();
                Toast.makeText(getActivity().getApplicationContext(), "Saved successfully", Toast.LENGTH_LONG).show();
            }
            catch(Exception e){
                Toast.makeText(getActivity().getApplicationContext(), "Failed, try again later", Toast.LENGTH_LONG).show();
            }

            FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new MenuFragment());
            fragmentTransaction.commit();

        }
    }
}