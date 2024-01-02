package com.example.tic_tac_toe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private int player1;
    private int player2;

    private int language = 0;

    private int p1;

    private String p1_name;
    private String p2_name;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button btn_end;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        btn_end = findViewById(R.id.btn_end);

        Intent intent = getIntent();
        Bundle bundle = this.getIntent().getExtras();
        p1 = bundle.getInt("p1");
        language = intent.getIntExtra("language",0);
        p1_name = bundle.getString("p1_name");
        p2_name = bundle.getString("p2_name");

        updatePointsText();

        if(language == 0) {
            btn_end.setText("結束遊戲");
        }
        else if(language  == 1) {
            btn_end.setText("Finish");
        }

        if (p1 == 1)
            player1Turn = true;
        else
            player1Turn = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }


        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putInt("玩家1",player1);
                b.putInt("玩家2",player2);
                i.putExtras(b);
                setResult(101,i);
                finish();
                endGame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                if (p1 == 1){
                    player1Wins();
                }
                else{
                    player2Wins();
                }
            } else {
                if (p1 == 0){
                    player1Wins();
                }
                else{
                    player2Wins();
                }
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        if (language == 0)
            Toast.makeText(this, p1_name + " 獲勝!", Toast.LENGTH_SHORT).show();
        else if (language == 1)
            Toast.makeText(this, p1_name + " Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        if (language == 0)
            Toast.makeText(this, p2_name + " 獲勝!", Toast.LENGTH_SHORT).show();
        else if (language == 1)
            Toast.makeText(this, p2_name + " Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        if (language == 0)
            Toast.makeText(this, "平手!", Toast.LENGTH_SHORT).show();
        else if (language == 1)
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText(p1_name + " : " + player1Points);
        textViewPlayer2.setText(p2_name + " : " + player2Points);
        player1 = player1Points;
        player2 = player2Points;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        if (p1 == 1)
            player1Turn = true;
        else
            player1Turn = false;
    }

    private void endGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
        if (language == 0)
            Toast.makeText(this, "遊戲結束!", Toast.LENGTH_SHORT).show();
        else if (language == 1)
            Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }

}