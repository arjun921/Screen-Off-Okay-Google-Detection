package com.omegadevs.sookaygoogle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Button btnOn,btnOff;

    //TODO run Broadcast reciever in terminateable background service
    //Create broadcast object
    BroadcastReceiver mybroadcast = new BroadcastReceiver() {
        //When Event is published, onReceive method is called
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Log.i("[BroadcastReceiver]", "MyReceiver");

            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("[BroadcastReceiver]", "Screen ON");
                //code that runs after screen turns on
                try {
                    Process su = Runtime.getRuntime().exec("su");
                    DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                    outputStream.writeBytes("dumpsys battery reset\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                //screen on code ends
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("[BroadcastReceiver]", "Screen OFF");
                //Code that runs after screen turns off
                try {
                    Process su = Runtime.getRuntime().exec("su");
                    DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                    outputStream.writeBytes("dumpsys battery set ac 1\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                //screen off code ends
            }

        }
    };
    //END OF BROADCAST RECIEVER CODE TO BE RUN IN SERVICE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOn = (Button)findViewById(R.id.on);
        btnOff = (Button) findViewById(R.id.off);
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));
        try {
            Process su = Runtime.getRuntime().exec("su");
            Toast.makeText(getApplicationContext(), "Root access Granted Sucessfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Root access not granted", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "App Requires root permissions, please root device and try again", Toast.LENGTH_LONG).show();
        }

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 26/05/17 Start background service on "On" button
                Toast.makeText(getApplicationContext(), "Executed", Toast.LENGTH_SHORT).show();
                try {
                    Process su = Runtime.getRuntime().exec("su");
                    DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                    outputStream.writeBytes("dumpsys battery set ac 1\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                    Log.i("[Charge Status]", "Manually Enabled via Switch");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 26/05/17 Stop Executing Service on "Off" button
                Toast.makeText(getApplicationContext(), "Executed", Toast.LENGTH_SHORT).show();
                try {
                    Process su = Runtime.getRuntime().exec("su");
                    DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                    outputStream.writeBytes("dumpsys battery reset\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                    Log.i("[Charge Status]", "Manually Disabled via Switch");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
