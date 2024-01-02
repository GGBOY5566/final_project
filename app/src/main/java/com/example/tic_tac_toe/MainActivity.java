package com.example.tic_tac_toe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private TextView textView;

    private Spinner spinner;

    private int language = 0;

    private int player1;
    private int player2;

    private EditText ed_p1,ed_p2;
    private RadioGroup rg1,rg2;
    private RadioButton rb1,rb2,rb3,rb4;

    private int p1 = 1; // 1 is X,0 is O
    private String p1_name;
    private String p2_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);

        ed_p1 = findViewById(R.id.ed_p1);
        ed_p2 = findViewById(R.id.ed_p2);

        rg1 = findViewById(R.id.radioGroup);
        rg2 = findViewById(R.id.radioGroup1);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton1:
                        rb4.setChecked(true);
                        p1 = 1;
                        break;
                    case R.id.radioButton2:
                        rb3.setChecked(true);
                        p1 = 0;
                        break;
                }
            }
        });


        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton3:
                        rb2.setChecked(true);
                        p1 = 0;
                        break;
                    case R.id.radioButton4:
                        rb1.setChecked(true);
                        p1 = 1;
                        break;

                }
            }
        });


        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position == 0) {
                    language = 0;
                    ed_p1.setHint("請輸入玩家名稱");
                    ed_p2.setHint("請輸入玩家名稱");
                    btn_start.setText("開始遊戲");
                    textView.setText("PK 結果");
                }
                else if(position == 1) {
                    language = 1;
                    ed_p1.setHint("Please enter Player name");
                    ed_p2.setHint("Please enter Player name");
                    btn_start.setText("Start Game");
                    textView.setText("PK Result");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1_name = ed_p1.getText().toString();
                p2_name = ed_p2.getText().toString();
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("p1", p1);
                bundle.putInt("language", language);
                bundle.putString("p1_name", p1_name);
                bundle.putString("p2_name", p2_name);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data ){
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null) return;

        if(requestCode == 1) {
            if (resultCode == 101) {
                Bundle b = data.getExtras();
                player1 = b.getInt("玩家1");
                player2 = b.getInt("玩家2");
                if (language == 0) {
                    ed_p1.setHint("請輸入玩家名稱");
                    ed_p2.setHint("請輸入玩家名稱");
                    textView.setText("PK結果\n" + p1_name + " 得分 : " + player1 + "\n" + p2_name + " 得分 : " + player2);
                }
                else if (language == 1) {
                    ed_p1.setHint("Please enter Player name");
                    ed_p2.setHint("Please enter Player name");
                    textView.setText("PK Result\n" + p1_name + " Score : " + player1 + "\n" + p2_name + " Score : " + player2);
                }
            }
        }
    }

}