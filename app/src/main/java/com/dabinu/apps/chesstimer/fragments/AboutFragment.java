package com.dabinu.apps.chesstimer.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dabinu.apps.chesstimer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends android.app.Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        Button github = view.findViewById(R.id.github_link);

        github.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TaslimOseni/kasparov")));
            }
        });

        github.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                copyToClipboard(getActivity().getApplicationContext(), "https://github.com/TaslimOseni/kasparov");
                Toast.makeText(getActivity().getApplicationContext(), "Copied", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        return view;
    }



    private void copyToClipboard(Context context, String text){
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        }
        else{
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }

}