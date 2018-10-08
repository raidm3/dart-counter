package mycompany.dartcounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import mycompany.dartcounter.models.Player;

public class GameWorldActivity extends AppCompatActivity {

    private int numberOfPlayers;
    private int pointsClicked = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayer;
    private TextView[] playerFields = new TextView[4];
    private LinearLayout[] playerGameFields = new LinearLayout[4];
    private boolean isActivityUsed = false;
    private String[] playerNames;
    private String gameSetting;

    // Check for game finish variables
    private Boolean[] hasPlayerFinished;


    // Player buttons
    private Button playerOneBtn;
    private Button playerTwoBtn;
    private Button playerThreeBtn;
    private Button playerFourBtn;

    // Player scores
    private int scoreOne = 1;
    private int scoreTwo = 1;
    private int scoreThree = 1;
    private int scoreFour = 1;

    // Player hints
    private TextView playerOneHint1;
    private TextView playerOneHint2;
    private TextView playerOneHint3;
    private TextView playerTwoHint1;
    private TextView playerTwoHint2;
    private TextView playerTwoHint3;
    private TextView playerThreeHint1;
    private TextView playerThreeHint2;
    private TextView playerThreeHint3;
    private TextView playerFourHint1;
    private TextView playerFourHint2;
    private TextView playerFourHint3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_world);

        // Get intent which started this activity
        Intent i = getIntent();

        // Get retrieved parameters
        numberOfPlayers = Integer.parseInt(i.getStringExtra("players"));
        playerNames = i.getStringArrayExtra("playerNames");
        gameSetting = i.getStringExtra("gameSetting");

        Log.d("test", Integer.toString(playerNames.length));
        Log.d("test", Integer.toString(numberOfPlayers));

        // Add player text views
        playerFields[0] = (TextView) findViewById(R.id.playerOne);
        playerFields[1] = (TextView) findViewById(R.id.playerTwo);
        playerFields[2] = (TextView) findViewById(R.id.playerThree);
        playerFields[3] = (TextView) findViewById(R.id.playerFour);

        // Add player game fields
        playerGameFields[0] = (LinearLayout) findViewById(R.id.playerOneField);
        playerGameFields[1] = (LinearLayout) findViewById(R.id.playerTwoField);
        playerGameFields[2] = (LinearLayout) findViewById(R.id.playerThreeField);
        playerGameFields[3] = (LinearLayout) findViewById(R.id.playerFourField);

        // Initialize player hint views.
        playerOneHint1 = findViewById(R.id.playerOneHint1);
        playerOneHint2 = findViewById(R.id.playerOneHint2);
        playerOneHint3 = findViewById(R.id.playerOneHint3);
        playerTwoHint1 = findViewById(R.id.playerTwoHint1);
        playerTwoHint2 = findViewById(R.id.playerTwoHint2);
        playerTwoHint3 = findViewById(R.id.playerTwoHint3);
        playerThreeHint1 = findViewById(R.id.playerThreeHint1);
        playerThreeHint2 = findViewById(R.id.playerThreeHint2);
        playerThreeHint3 = findViewById(R.id.playerThreeHint3);
        playerFourHint1 = findViewById(R.id.playerFourHint1);
        playerFourHint2 = findViewById(R.id.playerFourHint2);
        playerFourHint3 = findViewById(R.id.playerFourHint3);

        // Initialize player win variable.
        hasPlayerFinished = new Boolean[numberOfPlayers];

        // Create player models based on parameters
        for (int j = 1; j <= numberOfPlayers; j++) {
            Player player = new Player(this, playerNames[j-1], 0, playerFields[j-1]);

            // add to player array list
            players.add(player);
            hasPlayerFinished[j-1] = false;
        }

        // Show players
        for (int l = 0; l < numberOfPlayers; l++) {
            playerFields[l].setText(players.get(l).getName());
            playerGameFields[l].setVisibility(View.VISIBLE);
        }

        // Initialize player buttons
        playerOneBtn = (Button) findViewById(R.id.playerOneBtn);
        playerTwoBtn = (Button) findViewById(R.id.playerTwoBtn);
        playerThreeBtn = (Button) findViewById(R.id.playerThreeBtn);
        playerFourBtn = (Button) findViewById(R.id.playerFourBtn);

        // Set current player
        currentPlayer = 0;
        players.get(currentPlayer).setSelected(true);
    }


    /**
     * Click event handler for the point buttons.
     * @param v view
     */
    public void onPointCounterClicked(View v) {
        // Update variables
        pointsClicked++;

        // Update that Activity has been used already
        isActivityUsed = true;

        // Check which button has been clicked
        switch (v.getId()) {
            case R.id.playerOneBtn:
                scoreOne++;

                // Update button status
                if (scoreOne <= 20)
                    playerOneBtn.setText(String.format(Locale.GERMAN, "%d", scoreOne));
                else
                    playerOneBtn.setText("BULL / BULL'S EYE");
                if (pointsClicked == 3 || scoreOne == 22)
                    playerOneBtn.setEnabled(false);

                // Check if game is won
                if (scoreOne > 21) {
                    hasPlayerFinished[0] = true;
                    playerOneBtn.setText(getResources().getString(R.string.finished));
                }

                // Set hint text
                if (pointsClicked == 1) {
                    if ((scoreOne -1) == 21)
                        playerOneHint1.setText("BULL");
                    else
                        playerOneHint1.setText(String.format(Locale.GERMAN, "%d", (scoreOne - 1)));
                }
                else if (pointsClicked == 2) {
                    if ((scoreOne -1) == 21)
                        playerOneHint2.setText("BULL");
                    else
                        playerOneHint2.setText(String.format(Locale.GERMAN, "%d", (scoreOne - 1)));
                }
                else if (pointsClicked == 3) {
                    if ((scoreOne -1) == 21)
                        playerOneHint3.setText("BULL");
                    else
                        playerOneHint3.setText(String.format(Locale.GERMAN, "%d", (scoreOne - 1)));
                }

                break;
            case R.id.playerTwoBtn:
                scoreTwo++;

                // Update button status
                if (scoreTwo <= 20)
                    playerTwoBtn.setText(String.format(Locale.GERMAN, "%d", scoreTwo));
                else
                    playerTwoBtn.setText("BULL / BULL'S EYE");
                if (pointsClicked == 3 || scoreTwo == 22)
                    playerTwoBtn.setEnabled(false);

                // Check if game is won
                if (scoreTwo > 21) {
                    hasPlayerFinished[1] = true;
                    playerTwoBtn.setText(getResources().getString(R.string.finished));
                }

                // Set hint text
                if (pointsClicked == 1) {
                    if ((scoreTwo -1) == 21)
                        playerTwoHint1.setText("BULL");
                    else
                        playerTwoHint1.setText(String.format(Locale.GERMAN, "%d", (scoreTwo - 1)));
                }
                else if (pointsClicked == 2) {
                    if ((scoreTwo -1) == 21)
                        playerTwoHint2.setText("BULL");
                    else
                        playerTwoHint2.setText(String.format(Locale.GERMAN, "%d", (scoreTwo - 1)));
                }
                else if (pointsClicked == 3) {
                    if ((scoreTwo -1) == 21)
                        playerTwoHint3.setText("BULL");
                    else
                        playerTwoHint3.setText(String.format(Locale.GERMAN, "%d", (scoreTwo - 1)));
                }

                break;
            case R.id.playerThreeBtn:
                scoreThree++;

                // Update button status
                if (scoreThree <= 20)
                    playerThreeBtn.setText(String.format(Locale.GERMAN, "%d", scoreThree));
                else
                    playerThreeBtn.setText("BULL / BULL'S EYE");
                if (pointsClicked == 3 || scoreThree == 22)
                    playerThreeBtn.setEnabled(false);

                // Check if game is won
                if (scoreThree > 21) {
                    hasPlayerFinished[2] = true;
                    playerThreeBtn.setText(getResources().getString(R.string.finished));
                }

                // Set hint text
                if (pointsClicked == 1) {
                    if ((scoreThree -1) == 21)
                        playerThreeHint1.setText("BULL");
                    else
                        playerThreeHint1.setText(String.format(Locale.GERMAN, "%d", (scoreThree - 1)));
                }
                else if (pointsClicked == 2) {
                    if ((scoreThree -1) == 21)
                        playerThreeHint2.setText("BULL");
                    else
                        playerThreeHint2.setText(String.format(Locale.GERMAN, "%d", (scoreThree - 1)));
                }
                else if (pointsClicked == 3) {
                    if ((scoreThree -1) == 21)
                        playerThreeHint3.setText("BULL");
                    else
                        playerThreeHint3.setText(String.format(Locale.GERMAN, "%d", (scoreThree - 1)));
                }

                break;
            case R.id.playerFourBtn:
                scoreFour++;

                // Update button status
                if (scoreFour <= 20)
                    playerFourBtn.setText(String.format(Locale.GERMAN, "%d", scoreFour));
                else
                    playerFourBtn.setText("BULL / BULL'S EYE");
                if (pointsClicked == 3 || scoreFour == 22)
                    playerFourBtn.setEnabled(false);

                // Check if game is won
                if (scoreFour > 21) {
                    hasPlayerFinished[3] = true;
                    playerFourBtn.setText(getResources().getString(R.string.finished));
                }

                // Set hint text
                if (pointsClicked == 1) {
                    if ((scoreFour -1) == 21)
                        playerFourHint1.setText("BULL");
                    else
                        playerFourHint1.setText(String.format(Locale.GERMAN, "%d", (scoreFour - 1)));
                }
                else if (pointsClicked == 2) {
                    if ((scoreFour -1) == 21)
                        playerFourHint2.setText("BULL");
                    else
                        playerFourHint2.setText(String.format(Locale.GERMAN, "%d", (scoreFour - 1)));
                }
                else if (pointsClicked == 3) {
                    if ((scoreFour -1) == 21)
                        playerFourHint3.setText("BULL");
                    else
                        playerFourHint3.setText(String.format(Locale.GERMAN, "%d", (scoreFour - 1)));
                }

                break;
        }
    }


    /**
     * Click event handler for the undo button.
     * @param v view
     */
    public void onUndoWorldScoreClicked(View v) {
        if (pointsClicked == 0) {
            return;
        }

        // Reduce points clicked
        pointsClicked -= 1;

        // Reduce score and button value
        switch (currentPlayer) {
            case 0:
                scoreOne -= 1;
                playerOneBtn.setEnabled(true);

                // Set player button text
                if (scoreOne == 21)
                    playerOneBtn.setText("BULL / BULL'S EYE");
                else
                    playerOneBtn.setText(String.format(Locale.GERMAN, "%d", scoreOne));

                // Remove last hint
                if ((pointsClicked + 1) == 1)
                    playerOneHint1.setText("-");
                else if ((pointsClicked + 1) == 2)
                    playerOneHint2.setText("-");
                else if ((pointsClicked + 1) == 3)
                    playerOneHint3.setText("-");

                break;
            case 1:
                scoreTwo -= 1;
                playerTwoBtn.setEnabled(true);

                // Set player button text
                if (scoreTwo == 21)
                    playerTwoBtn.setText("BULL / BULL'S EYE");
                else
                    playerTwoBtn.setText(String.format(Locale.GERMAN, "%d", scoreTwo));

                // Remove last hint
                if ((pointsClicked + 1) == 1)
                    playerTwoHint1.setText("-");
                else if ((pointsClicked + 1) == 2)
                    playerTwoHint2.setText("-");
                else if ((pointsClicked + 1) == 3)
                    playerTwoHint3.setText("-");
                break;
            case 2:
                scoreThree -= 1;
                playerThreeBtn.setEnabled(true);

                // Set player button text
                if (scoreThree == 21)
                    playerThreeBtn.setText("BULL / BULL'S EYE");
                else
                    playerThreeBtn.setText(String.format(Locale.GERMAN, "%d", scoreThree));

                // Remove last hint
                if ((pointsClicked + 1) == 1)
                    playerThreeHint1.setText("-");
                else if ((pointsClicked + 1) == 2)
                    playerThreeHint2.setText("-");
                else if ((pointsClicked + 1) == 3)
                    playerThreeHint3.setText("-");

                break;
            case 3:
                scoreFour -= 1;
                playerFourBtn.setEnabled(true);

                // Set player button text
                if (scoreFour == 21)
                    playerFourBtn.setText("BULL / BULL'S EYE");
                else
                    playerFourBtn.setText(String.format(Locale.GERMAN, "%d", scoreFour));

                // Remove last hint
                if ((pointsClicked + 1) == 1)
                    playerFourHint1.setText("-");
                else if ((pointsClicked + 1) == 2)
                    playerFourHint2.setText("-");
                else if ((pointsClicked + 1) == 3)
                    playerFourHint3.setText("-");

                break;
        }
    }

    /**
     * Click event handler for the accept button on Round the World.
     * @param v view
     */
    public void onAcceptWorldClicked(View v) {
        Player player = players.get(currentPlayer);

        // Update darts thrown counter
        players.get(currentPlayer).addDartsThrown(3);


        // Update that Activity has been used already
        isActivityUsed = true;

        // Check if game is won
        if (!Arrays.asList(hasPlayerFinished).contains(false)) {
            finishGame();
            return;
        }

        // disable current player button
        if (currentPlayer == 0)
            playerOneBtn.setEnabled(false);
        else if (currentPlayer == 1)
            playerTwoBtn.setEnabled(false);
        else if (currentPlayer == 2)
            playerThreeBtn.setEnabled(false);
        else if (currentPlayer == 3)
            playerFourBtn.setEnabled(false);

        // shift current player
        if (currentPlayer < numberOfPlayers -1) {
            currentPlayer++;
        } else {
            currentPlayer = 0;
        }

        // Loop through players until one that has not finished yet has been found.
        for (int i = currentPlayer; i < numberOfPlayers; i++) {

            if (hasPlayerFinished[i]) {
                if (i == numberOfPlayers-1)
                    i = -1;
                continue;
            }

            // Set current player
            currentPlayer = i;
            break;
        }

        // enabled new player button
        if (currentPlayer == 0) {
            playerOneBtn.setEnabled(true);
            playerOneHint1.setText("-");
            playerOneHint2.setText("-");
            playerOneHint3.setText("-");
        } else if (currentPlayer == 1) {
            playerTwoBtn.setEnabled(true);
            playerTwoHint1.setText("-");
            playerTwoHint2.setText("-");
            playerTwoHint3.setText("-");
        } else if (currentPlayer == 2) {
            playerThreeBtn.setEnabled(true);
            playerThreeHint1.setText("-");
            playerThreeHint2.setText("-");
            playerThreeHint3.setText("-");
        } else if (currentPlayer == 3) {
            playerFourBtn.setEnabled(true);
            playerFourHint1.setText("-");
            playerFourHint2.setText("-");
            playerFourHint3.setText("-");
        }

        // select new player
        player.setSelected(false);
        player = players.get(currentPlayer);
        player.setSelected(true);

        // Reset points clicked
        pointsClicked = 0;
    }


    /**
     * Finishes the game.
     */
    private void finishGame() {
        String[] playerNames = new String[numberOfPlayers];
        String[] playerRounds = new String[numberOfPlayers];

        // Set stats
        for (int i = 0; i < playerNames.length; i++) {
            Player player = players.get(i);

            playerNames[i] = player.getName();
            playerRounds[i] = Integer.toString(player.getDartsThrown() / 3);
        }

        // Create new Intent for Victory Activity
        Intent victoryIntent = new Intent(this, VictoryWorldActivity.class);

        // Set params
        victoryIntent.putExtra("winnerName", players.get(currentPlayer).getName());
        victoryIntent.putExtra("playerNames", playerNames);
        victoryIntent.putExtra("playerRounds", playerRounds);

        // Start new Activity
        startActivity(victoryIntent);
        finish();
    }


    /**
     * Warning when exiting the GameActivity by pressing the Back Button.
     */
    @Override
    public void onBackPressed() {
        if (isActivityUsed) {
            new AlertDialog.Builder(this, R.style.quit_game_dialog)
                    .setTitle("Quit Game?")
                    .setMessage("Are you sure you want to quit the game?")
                    .setNegativeButton(R.string.no, null)
                    .setPositiveButton(R.string.quit_game, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            GameWorldActivity.super.onBackPressed();
                        }
                    }).create().show();
        } else {
            GameWorldActivity.super.onBackPressed();
        }
    }
}
