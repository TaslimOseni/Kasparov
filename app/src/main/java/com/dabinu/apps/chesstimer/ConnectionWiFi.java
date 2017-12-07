package com.dabinu.apps.chesstimer;


import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ConnectionWiFi extends Activity{

    TextView create, join;
    RelativeLayout con;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_wi_fi);


        final WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        create = findViewById(R.id.create);
        join = findViewById(R.id.join);
        con = findViewById(R.id.con);


        final Context theBloodyContextThatHasBeenGivingMeWahala = this;

        join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                AlertDialog.Builder alert1 = new AlertDialog.Builder(theBloodyContextThatHasBeenGivingMeWahala);
                alert1.setMessage("Please wait");
                alert1.show();
                try{
                    Thread.sleep(5000);
                }
                catch(Exception e){

                }
                if(wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED){
//                    con.performClick();
//                    AlertDialog.Builder alert2 = new AlertDialog.Builder(theBloodyContextThatHasBeenGivingMeWahala);
//                    alert2.setMessage("Turning WiFi on");
//                    alert2.show();
//                    wifiManager.setWifiEnabled(true);
//                    if(wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED){
//                        con.performClick();
//                    }

//                    ProgressDialog progressDialog = new ProgressDialog(theBloodyContextThatHasBeenGivingMeWahala);

                }


                AlertDialog.Builder alert3 = new AlertDialog.Builder(theBloodyContextThatHasBeenGivingMeWahala);
                alert3.setMessage("Searching for nearby connection");
                alert3.show();
                try{
                   Thread.sleep(5000);
                }
                catch(Exception e){

                }
            }
        });
    }

}
