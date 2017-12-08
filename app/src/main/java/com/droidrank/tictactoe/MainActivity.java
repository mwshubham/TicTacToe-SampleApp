package com.droidrank.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;

    boolean isNewGame = true;
    boolean player1Turn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);


        block1.setOnClickListener(this);
        block2.setOnClickListener(this);
        block3.setOnClickListener(this);
        block4.setOnClickListener(this);
        block5.setOnClickListener(this);
        block6.setOnClickListener(this);
        block7.setOnClickListener(this);
        block8.setOnClickListener(this);
        block9.setOnClickListener(this);

        /**
         * Restarts the game
         */
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isNewGame) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Tic-Tac-Toe");
                    builder.setMessage("Do you want restart the game?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    resetBlocks();
                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();
                }


            }
        });

    }

    private void resetBlocks() {
        isNewGame = true;
        player1Turn = true;
        restart.setText(getString(R.string.restart_button_text_initially));
        result.setText("");
        block1.setText("");
        block2.setText("");
        block3.setText("");
        block4.setText("");
        block5.setText("");
        block6.setText("");
        block7.setText("");
        block8.setText("");
        block9.setText("");
    }

    @Override
    public void onClick(View view) {
        if (checkIfPlayerWins("0") || checkIfPlayerWins("X")) {
            return;
        }
        switch (view.getId()) {
            case R.id.bt_block1:
                updateButtonText(view);
                break;

            case R.id.bt_block2:
                updateButtonText(view);
                break;


            case R.id.bt_block3:
                updateButtonText(view);
                break;

            case R.id.bt_block4:
                updateButtonText(view);
                break;

            case R.id.bt_block5:
                updateButtonText(view);
                break;

            case R.id.bt_block6:
                updateButtonText(view);
                break;
            case R.id.bt_block7:
                updateButtonText(view);
                break;
            case R.id.bt_block8:
                updateButtonText(view);
                break;

            case R.id.bt_block9:
                updateButtonText(view);
                break;
        }

        displayResult();
    }

    /*0 -> player 1 , X -> player 2*/
    private void displayResult() {
        if (checkIfPlayerWins("0")) {
            result.setText(getString(R.string.player_1_wins));
        } else if (checkIfPlayerWins("X")) {
            result.setText(getString(R.string.player_2_wins));
        } else if (checkIfTie()) {
            result.setText(R.string.message_tie);
        } else {
            result.setText("");
        }
    }

    private boolean checkIfTie() {
        return !getText(block1).isEmpty() && !getText(block2).isEmpty() && !getText(block3).isEmpty() && !getText(block4).isEmpty() && !getText(block5).isEmpty()
                && !getText(block6).isEmpty() && !getText(block7).isEmpty() && !getText(block8).isEmpty() && !getText(block9).isEmpty();
    }

    private String getText(View view) {
        if (view instanceof Button) {
            return ((Button) view).getText().toString();
        }
        return "";
    }


    private boolean checkIfPlayerWins(String playerText) {
        return playerText.equalsIgnoreCase(getText(block1)) && playerText.equalsIgnoreCase(getText(block2)) && playerText.equalsIgnoreCase(getText(block3))
                || playerText.equalsIgnoreCase(getText(block4)) && playerText.equalsIgnoreCase(getText(block5)) && playerText.equalsIgnoreCase(getText(block6))
                || playerText.equalsIgnoreCase(getText(block7)) && playerText.equalsIgnoreCase(getText(block8)) && playerText.equalsIgnoreCase(getText(block9))
                || playerText.equalsIgnoreCase(getText(block1)) && playerText.equalsIgnoreCase(getText(block4)) && playerText.equalsIgnoreCase(getText(block7))
                || playerText.equalsIgnoreCase(getText(block2)) && playerText.equalsIgnoreCase(getText(block5)) && playerText.equalsIgnoreCase(getText(block8))
                || playerText.equalsIgnoreCase(getText(block3)) && playerText.equalsIgnoreCase(getText(block6)) && playerText.equalsIgnoreCase(getText(block9))
                || playerText.equalsIgnoreCase(getText(block1)) && playerText.equalsIgnoreCase(getText(block5)) && playerText.equalsIgnoreCase(getText(block9))
                || playerText.equalsIgnoreCase(getText(block3)) && playerText.equalsIgnoreCase(getText(block5)) && playerText.equalsIgnoreCase(getText(block7));


    }

    private void updateButtonText(View view) {

        isNewGame = false;
        restart.setText(getString(R.string.restart_button_text_in_middle_of_game));
        Button button = (Button) view;
        if (button.getText().toString().isEmpty()) {

            if (player1Turn) {
                button.setText("0");
            } else {
                button.setText("X");
            }
            player1Turn = !player1Turn;
        }
    }
}
