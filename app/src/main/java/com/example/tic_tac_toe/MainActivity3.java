package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private TextView tvWin;
    private Button btn_start_game;
    private String winName,winMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle bundle = this.getIntent().getExtras();
        winName = bundle.getString("winName");
        winMsg = bundle.getString("winMsg");
        btn_start_game = findViewById(R.id.btn_back_to_main);
        tvWin = findViewById(R.id.tvWin);
        tvWin.setText(winName +":"+winMsg);
        btn_start_game.setOnClickListener(v -> {
            finish();
        });
    }
}