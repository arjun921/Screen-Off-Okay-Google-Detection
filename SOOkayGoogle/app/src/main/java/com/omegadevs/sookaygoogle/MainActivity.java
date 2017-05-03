package com.omegadevs.sookaygoogle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Button btnOn,btnOff;

    public void launchTestService() {
        boolean shouldContinue = true;
        // Construct our Intent specifying the Service
        Intent i = new Intent(this, MyTestService.class);
        // Add extras to the bundle
        i.putExtra("val", shouldContinue);
        // Start the service
        startService(i);
    }

    public void stopTestService() {
        finish();
        System.exit(0);
    }
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
//                TODO Call MyTestService Here
            }
        });
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Executed", Toast.LENGTH_SHORT).show();
//                TODO End MyTestService Here

            }
        });
    }
}
