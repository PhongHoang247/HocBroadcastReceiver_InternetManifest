package com.phong.hocbroadcastreceiver_internetmanifest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtMsg =  findViewById(R.id.txtMsg);

        Intent intent = getIntent();
        if (intent != null){
            String status = intent.getStringExtra("STATUS");
            txtMsg.setText(status);
        }
    }
}
