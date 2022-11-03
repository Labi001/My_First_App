package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class TicTacToe_Activity extends AppCompatActivity {

    private LinearLayout winner_layout;
    private FloatingActionButton fab_btn;
    private TextView winner_txt;
    int activePlayer = 1;
    ArrayList<Integer> player1 = new ArrayList();
    ArrayList<Integer> player2 = new ArrayList();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        winner_layout = findViewById(R.id.winner_layout);
        fab_btn = findViewById(R.id.fab_btn);
        winner_txt = findViewById(R.id.check_winner);

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });

    }

    public void OnClick(View view) {

        Button buttonselected = (Button) view;
        int cellId = 0;

        switch ((buttonselected.getId())){

            case R.id.btn_0:
                 cellId =0;
                break;

            case R.id.btn_1:
                cellId =1;
                break;

            case R.id.btn_2:
                cellId =2;
                break;

            case R.id.btn_3:
                cellId =3;
                break;

            case R.id.btn_4:
                cellId =4;
                break;

            case R.id.btn_5:
                cellId =5;
                break;

            case R.id.btn_6:
                cellId =6;
                break;

            case R.id.btn_7:
                cellId =7;
                break;

            case R.id.btn_8:
                cellId =8;
                break;


        }
        playGame(cellId,buttonselected);
    }

    private void playGame(int cellId,Button btn) {

        if(activePlayer==1){

            btn.setText("X");
            btn.setBackgroundColor(getResources().getColor(R.color.player_one_color));
            player1.add(cellId);
            activePlayer = 2;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                   autoplay();
                }
            },200);

        }else if(activePlayer == 2){

            btn.setText("O");
            btn.setBackgroundColor(getResources().getColor(R.color.player_two_color));
            player2.add(cellId);
            activePlayer = 1;

        }

        btn.setEnabled(false);
        CheckWinner();
    }

    private void CheckWinner() {

        int winner = -1;

        //row1
        if(player1.contains(0) && player1.contains(1) && player1.contains(2)){

            winner = 1;
        }

        if(player2.contains(0) && player2.contains(1) && player2.contains(2)){

            winner = 2;
        }

        //row2
        if(player1.contains(3) && player1.contains(4) && player1.contains(5)){

            winner = 1;
        }

        if(player2.contains(3) && player2.contains(4) && player2.contains(5)){

            winner = 2;
        }

        //row3
        if(player1.contains(6) && player1.contains(7) && player1.contains(8)){

            winner = 1;
        }

        if(player2.contains(6) && player2.contains(7) && player2.contains(8)){

            winner = 2;
        }

        //diagonal1
        if(player1.contains(0) && player1.contains(4) && player1.contains(8)){

            winner = 1;
        }

        if(player2.contains(0) && player2.contains(4) && player2.contains(8)){

            winner = 2;
        }

        //diagonal2
        if(player1.contains(2) && player1.contains(4) && player1.contains(6)){

            winner = 1;
        }

        if(player2.contains(2) && player2.contains(4) && player2.contains(6)){

            winner = 2;
        }

        //colon1
        if(player1.contains(0) && player1.contains(3) && player1.contains(6)){

            winner = 1;
        }

        if(player2.contains(0) && player2.contains(3) && player2.contains(6)){

            winner = 2;
        }

        //colon2
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){

            winner = 1;
        }

        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){

            winner = 2;
        }

        //colon3
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){

            winner = 1;
        }

        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){

            winner = 2;
        }

        if(winner != -1){

            if(winner==1){
                winner_txt.setText("Fitoi Lojtari i Pare !");
            } else if(winner==2){
                winner_txt.setText("Fitoi Lojtari i Dyte !");
            }

            disableallButtons();
            winner_layout.setVisibility(View.VISIBLE);

        }else{
            winner_txt.setText("Loja mbeti Barazim !");

            if(player1.size() >=5 && player2.size() >= 4){

                winner_layout.setVisibility(View.VISIBLE);

            }


        }






    }


    private void autoplay(){

        ArrayList<Integer> EmptyCells = new ArrayList<>();

        for(int i = 0; i<9;i++){

            if(!(player1.contains(i) || player2.contains(i)))
                EmptyCells.add(i);
        }

        Random myRandom = new Random();

        int ranIndex = myRandom.nextInt(EmptyCells.size() - 0) + 0;
        int CellID = EmptyCells.get(ranIndex);

        Button btnSelected;
        switch (CellID) {
            case 0:
                btnSelected = findViewById(R.id.btn_0);
                break;
            case 1:
                btnSelected = findViewById(R.id.btn_1);
                break;
            case 2:
                btnSelected = findViewById(R.id.btn_2);
                break;
            case 3:
                btnSelected = findViewById(R.id.btn_3);
                break;
            case 4:
                btnSelected = findViewById(R.id.btn_4);
                break;
            case 5:
                btnSelected = findViewById(R.id.btn_5);
                break;
            case 6:
                btnSelected = findViewById(R.id.btn_6);
                break;
            case 7:
                btnSelected = findViewById(R.id.btn_7);
                break;
            case 8:
                btnSelected = findViewById(R.id.btn_8);
                break;
            default:
                btnSelected = findViewById(R.id.btn_1);
                break;
        }

        playGame(CellID,btnSelected);

    }

    private void disableallButtons() {

        findViewById(R.id.btn_0).setEnabled(false);
        findViewById(R.id.btn_1).setEnabled(false);
        findViewById(R.id.btn_2).setEnabled(false);
        findViewById(R.id.btn_3).setEnabled(false);
        findViewById(R.id.btn_4).setEnabled(false);
        findViewById(R.id.btn_5).setEnabled(false);
        findViewById(R.id.btn_6).setEnabled(false);
        findViewById(R.id.btn_7).setEnabled(false);
        findViewById(R.id.btn_8).setEnabled(false);

    }
}