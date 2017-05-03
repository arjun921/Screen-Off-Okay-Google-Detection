package com.omegadevs.sookaygoogle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Button btnOn,btnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOn = (Button)findViewById(R.id.on);
        btnOff = (Button) findViewById(R.id.off);
        try {
            Process su = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Executed", Toast.LENGTH_SHORT).show();
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
            }
        });
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Executed", Toast.LENGTH_SHORT).show();
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
            }
        });
    }
}
