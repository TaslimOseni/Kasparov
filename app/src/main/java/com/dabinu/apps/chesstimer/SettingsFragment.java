package com.dabinu.apps.chesstimer;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;



public class SettingsFragment extends android.app.Fragment implements View.OnClickListener{


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FragmentTransaction fragmentTransaction;
    CheckBox should_vibrate, should_ring;
    Spinner active_color, inactive_color, default_color, background_color;
    TextView save_text;


    public SettingsFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        init(view);

        setSavedArrangement();

        save_text.setOnClickListener(this);

        return view;

    }


    public void init(View view){

        sharedPreferences = getActivity().getSharedPreferences("DETAILS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();

        should_vibrate = view.findViewById(R.id.should_vibrate);
        should_ring = view.findViewById(R.id.should_ring);

        active_color = view.findViewById(R.id.spinner_active_color);
        ArrayAdapter active_color_adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.colors, R.layout.spinnerheader);
        active_color_adapter.setDropDownViewResource(R.layout.drop_down);
        active_color.setAdapter(active_color_adapter);

        inactive_color = view.findViewById(R.id.spinner_inactive_color);
        ArrayAdapter inactive_color_adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.colors, R.layout.spinnerheader);
        inactive_color_adapter.setDropDownViewResource(R.layout.drop_down);
        inactive_color.setAdapter(inactive_color_adapter);

        default_color = view.findViewById(R.id.spinner_default_color);
        ArrayAdapter def_color_adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.colors, R.layout.spinnerheader);
        def_color_adapter.setDropDownViewResource(R.layout.drop_down);
        default_color.setAdapter(def_color_adapter);

        background_color = view.findViewById(R.id.spinner_background_color);
        ArrayAdapter bkd_color_adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.bkd_colors  , R.layout.spinnerheader);
        bkd_color_adapter.setDropDownViewResource(R.layout.drop_down);
        background_color.setAdapter(bkd_color_adapter);

        save_text = view.findViewById(R.id.save_text_view);

    }


    public void setSavedArrangement(){
        should_vibrate.setChecked(sharedPreferences.getString("vibrate", "true").equals("true"));
        should_ring.setChecked(sharedPreferences.getString("ring", "true").equals("true"));
        active_color.setSelection(Arrays.asList(getResources().getStringArray(R.array.colors)).indexOf(sharedPreferences.getString("active_color", "Green")));
        inactive_color.setSelection(Arrays.asList(getResources().getStringArray(R.array.colors)).indexOf(sharedPreferences.getString("inactive_color", "Red")));
        default_color.setSelection(Arrays.asList(getResources().getStringArray(R.array.colors)).indexOf(sharedPreferences.getString("default_color", "Gray")));
        background_color.setSelection(Arrays.asList(getResources().getStringArray(R.array.bkd_colors)).indexOf(sharedPreferences.getString("background_color", "Deep-Gray")));
    }


    @Override
    public void onClick(View view){

        editor.putString("vibrate", should_vibrate.isChecked() ? "true" : "false");
        editor.putString("ring", should_ring.isChecked() ? "true" : "false");
        editor.putString("active_color", (String) active_color.getSelectedItem());
        editor.putString("inactive_color", (String) inactive_color.getSelectedItem());
        editor.putString("default_color", (String) default_color.getSelectedItem());
        editor.putString("background_color", (String) background_color.getSelectedItem());
        editor.apply();

        Toast.makeText(getActivity().getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

        goToHomeFragment();
    }


    public void goToHomeFragment(){
        fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new MenuFragment());
        fragmentTransaction.commit();
    }


}