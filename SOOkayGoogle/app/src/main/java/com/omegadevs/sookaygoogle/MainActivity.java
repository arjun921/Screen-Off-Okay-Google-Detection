package com.omegadevs.sookaygoogle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import com.omegadevs.sookaygoogle.ShellExecuter;


public class MainActivity extends AppCompatActivity {

    Button btnOn,btnOff;
    String command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOn = (Button)findViewById(R.id.on);
//        btnOff = (Button)findViewById(R.id.off);
        final ShellExecuter exe = new ShellExecuter();
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command = "su";
                exe.Executer(command);
                command = "dumpsys battery set ac 1";
                exe.Executer(command);
                command = "";
            }
        });
//        btnOff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                command = "dumpsys battery reset";
//                exe.Executer(command);
//                command = "";
//            }
//        });
    }
}
