package mycompany.dartcounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

import mycompany.dartcounter.models.Player;

public class GameActivity extends AppCompatActivity {

    private int playerCount;
    private int game;
    private TextView remainingPointsView;
    private int pointsClicked = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayer;
    private TextView[] playerFields = new TextView[4];
    private boolean isActivityUsed = false;
    private String[] playerNames;
    private String finishSetting;

    // Score Views
    private TextView scoreOneView;
    private TextView scoreTwoView;
    private TextView scoreThreeView;
    private TextView totalScoreView;

    // Score values
    private int scoreOne;
    private int scoreTwo;
    private int scoreThree;
    private int scoreOneMultiplier = 1;
    private int scoreTwoMultiplier = 1;
    private int scoreThreeMultiplier = 1;
    private int currentSum;
    private int remainingPoints;

    // Special Buttons
    private ToggleButton doubleBtn;
    private ToggleButton tripleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get intent which started this activity
        Intent i = getIntent();

        // Get retrieved parameters
        playerCount = Integer.parseInt(i.getStringExtra("players"));
        game = Integer.parseInt(i.getStringExtra("game"));
        playerNames = i.getStringArrayExtra("playerNames");
        finishSetting = i.getStringExtra("finishSetting");

        // Add player text views
        playerFields[0] = (TextView) findViewById(R.id.playerOne);
        playerFields[1] = (TextView) findViewById(R.id.playerTwo);
        playerFields[2] = (TextView) findViewById(R.id.playerThree);
        playerFields[3] = (TextView) findViewById(R.id.playerFour);

        // Create player models based on parameters
        for (int j = 1; j <= playerCount; j++) {
            Player player = new Player(playerNames[j-1], game, playerFields[j-1]);

            // add to player array list
            players.add(player);
        }

        // Show players
        for (int l = 0; l < players.size(); l++) {
            playerFields[l].setText(players.get(l).toString());
            playerFields[l].setVisibility(View.VISIBLE);
        }

        // Set current player
        currentPlayer = 0;
        players.get(currentPlayer).setSelected(true);

        // Set remaining points to game mode
        this.remainingPoints = game;
        remainingPointsView = (TextView) findViewById(R.id.remainingScore);
        remainingPointsView.setText(String.valueOf(this.remainingPoints));

        // Score Views
        scoreOneView = (TextView) findViewById(R.id.scoreOne);
        scoreTwoView = (TextView) findViewById(R.id.scoreTwo);
        scoreThreeView = (TextView) findViewById(R.id.scoreThree);
        totalScoreView = (TextView) findViewById(R.id.totalScore);

        // Special Buttons
        doubleBtn = (ToggleButton) findViewById(R.id.points_double);
        tripleBtn = (ToggleButton) findViewById(R.id.points_triple);
    }


    /**
     * Resets all score values to zero.
     */
    private void resetScores() {
        // Reset all scores
        scoreOne = 0;
        scoreTwo = 0;
        scoreThree = 0;
        currentSum = 0;

        // Set scores to progress
        scoreOneView.setText(String.format(Locale.GERMAN, "%d", scoreOne));
        scoreTwoView.setText(String.format(Locale.GERMAN, "%d", scoreTwo));
        scoreThreeView.setText(String.format(Locale.GERMAN, "%d", scoreThree));
        totalScoreView.setText(String.format(Locale.GERMAN, "%d", currentSum));
    }


    /**
     * On Click Eventhandler for point buttons.
     * Writes clicked points in the score layout.
     *
     * @param v - View of Activity.
     */
    public void onPointClicked(View v) {
        final int id = v.getId();
        int multiplier;

        // set multiplier if double/triple is selected
        multiplier = doubleBtn.isChecked() ? 2 : (tripleBtn.isChecked() ? 3 : 1);

        // Set if throw was a multiplier
        if (pointsClicked == 0) {
            scoreOneMultiplier = multiplier;
        }
        if (pointsClicked == 1) {
            scoreTwoMultiplier = multiplier;
        }
        if (pointsClicked == 2) {
            scoreThreeMultiplier = multiplier;
        }

        // Set points
        switch (id) {
            case R.id.points_1:
                setPoints(multiplier);
                break;
            case R.id.points_2:
                setPoints(2 * multiplier);
                break;
            case R.id.points_3:
                setPoints(3 * multiplier);
                break;
            case R.id.points_4:
                setPoints(4 * multiplier);
                break;
            case R.id.points_5:
                setPoints(5 * multiplier);
                break;
            case R.id.points_6:
                setPoints(6 * multiplier);
                break;
            case R.id.points_7:
                setPoints(7 * multiplier);
                break;
            case R.id.points_8:
                setPoints(8 * multiplier);
                break;
            case R.id.points_9:
                setPoints(9 * multiplier);
                break;
            case R.id.points_10:
                setPoints(10 * multiplier);
                break;
            case R.id.points_11:
                setPoints(11 * multiplier);
                break;
            case R.id.points_12:
                setPoints(12 * multiplier);
                break;
            case R.id.points_13:
                setPoints(13 * multiplier);
                break;
            case R.id.points_14:
                setPoints(14 * multiplier);
                break;
            case R.id.points_15:
                setPoints(15 * multiplier);
                break;
            case R.id.points_16:
                setPoints(16 * multiplier);
                break;
            case R.id.points_17:
                setPoints(17 * multiplier);
                break;
            case R.id.points_18:
                setPoints(18 * multiplier);
                break;
            case R.id.points_19:
                setPoints(19 * multiplier);
                break;
            case R.id.points_20:
                setPoints(20 * multiplier);
                break;
            case R.id.points_bull:

                setPoints(25);
                break;
            case R.id.points_bulls_eye:
                // Set if throw was a multiplier
                if (pointsClicked == 0) {
                    scoreOneMultiplier = 2;
                }
                if (pointsClicked == 1) {
                    scoreTwoMultiplier = 2;
                }
                if (pointsClicked == 2) {
                    scoreThreeMultiplier = 2;
                }

                setPoints(50);
                break;
            case R.id.points_classic:
                setPoints(1, 20, 5);
                break;
        }

        // reset special button_primary selections
        doubleBtn.setChecked(false);
        tripleBtn.setChecked(false);

        // set used boolean to make sure activity has been used already
        isActivityUsed = true;
    }


    /**
     * On Click Eventhandler for both special buttons: DOUBLE, TRIPLE.
     *
     * @param v - View of Activity.
     */
    public void onSpecialButtonClicked(View v) {
        ToggleButton otherButton;

        // check which button has been clicked
        if (v.getId() == R.id.points_double) {
            otherButton = tripleBtn;
        } else {
            otherButton = doubleBtn;
        }

        // reset selection of other special button_primary
        otherButton.setChecked(false);
    }


    /**
     * On Click Eventhandler for button_primary: Accept.
     * Resets the scores and subtracts it from players points.
     *
     * @param v - View of Activity.
     */
    public void onAcceptClicked(View v) {
        Player player = players.get(currentPlayer);

        // check points of current player
        if ((player.getPoints() - currentSum) < 0) {
            Toast.makeText(getApplicationContext(), "Throw over!", Toast.LENGTH_SHORT).show();
            player.setSelected(false);

            // update darts thrown
            this.players.get(currentPlayer).addDartsThrown(3);

            // update highscore
            if (currentSum > this.players.get(currentPlayer).getHighscore()) {
                this.players.get(currentPlayer).setHighscore(currentSum);
            }
        }
        else if ((player.getPoints() - currentSum) == 0) {
            // update darts thrown
            this.players.get(currentPlayer).addDartsThrown(3);

            // update highscore
            if (currentSum > this.players.get(currentPlayer).getHighscore()) {
                this.players.get(currentPlayer).setHighscore(currentSum);
            }

            // Check for finish setting and use the given option.
            if (finishSetting.equals("doubleOut")) {
                if (pointsClicked == 1) {
                    if (scoreOneMultiplier == 2) {
                        finishGame();
                        return;
                    }
                } else if (pointsClicked == 2) {
                    if (scoreTwoMultiplier == 2) {
                        finishGame();
                        return;
                    }
                } else if (pointsClicked == 3) {
                    if (scoreThreeMultiplier == 2) {
                        finishGame();
                        return;
                    }
                }

                // Show Toast that it was no Double Out
                Toast.makeText(getApplicationContext(), "No Double Out!", Toast.LENGTH_SHORT).show();
                player.setSelected(false);

            } else if (finishSetting.equals("singleOut")) {
                finishGame();
            }
        }
        else {
            // update current player
            player.subtractPoints(currentSum);
            player.setSelected(false);

            // update darts thrown
            this.players.get(currentPlayer).addDartsThrown(3);

            // update highscore
            if (currentSum > this.players.get(currentPlayer).getHighscore()) {
                this.players.get(currentPlayer).setHighscore(currentSum);
            }
        }

        // shift current player
        if (currentPlayer < playerCount-1) {
            currentPlayer++;
        } else {
            currentPlayer = 0;
        }

        // select new player
        player = players.get(currentPlayer);
        player.setSelected(true);

        // reset all scores
        resetScores();

        // reset points clicked
        this.pointsClicked = 0;

        // set remaining points to players current points
        remainingPoints = player.getPoints();
        remainingPointsView.setText(String.valueOf(this.remainingPoints));

        // set used boolean to make sure activity has been used already
        isActivityUsed = true;
    }


    /**
     * On Click Eventhandler for button_primary: Undo.
     * Resets the last score to zero.
     *
     * @param v - View of Activity.
     */
    public void onUndoScoreClicked(View v) {
        if (pointsClicked == 0) {
            return;
        }

        switch (--pointsClicked) {
            case 0:
                // update remaining points
                this.remainingPoints += Integer.parseInt(scoreOneView.getText().toString());
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreOne = 0;
                scoreOneView.setText(String.valueOf(0));

                break;
            case 1:
                // update remaining points
                this.remainingPoints += Integer.parseInt(scoreTwoView.getText().toString());
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreTwo = 0;
                scoreTwoView.setText(String.valueOf(0));

                break;
            case 2:
                // update remaining points
                this.remainingPoints += Integer.parseInt(scoreThreeView.getText().toString());
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreThree = 0;
                scoreThreeView.setText(String.valueOf(0));

                break;
        }

        // update sum score
        updateSumScore();
    }


    /**
     * Sets the current score text to the parameter points.
     *
     * @param points : points which get subtracted by the player total points.
     */
    private void setPoints(int points) {
        switch (pointsClicked) {
            case 0:
                // update remaining points score
                remainingPoints -= points;
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreOne = points;
                scoreOneView.setText(String.valueOf(scoreOne));

                // update points clicked
                this.pointsClicked++;


                break;
            case 1:
                // update remaining points score
                remainingPoints -= points;
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreTwo = points;
                scoreTwoView.setText(String.valueOf(scoreTwo));

                // update points clicked
                this.pointsClicked++;

                break;
            case 2:
                // update remaining points score
                remainingPoints -= points;
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreThree = points;
                scoreThreeView.setText(String.valueOf(scoreThree));

                // update points clicked
                this.pointsClicked++;

                break;
        }

        // update sum score
        updateSumScore();
    }


    /**
     * Sets the current score text to the parameter points.
     *
     * @param pointsOne : points which get subtracted by the player total points.
     * @param pointsTwo : points which get subtracted by the player total points.
     * @param pointsThree : points which get subtracted by the player total points.
     */
    private void setPoints(int pointsOne, int pointsTwo, int pointsThree) {
        // set scores
        scoreOne = pointsOne;
        scoreTwo = pointsTwo;
        scoreThree = pointsThree;

        // reduce remaining points and set points clicked
        remainingPoints = players.get(currentPlayer).getPoints();
        remainingPoints -= (pointsOne + pointsTwo + pointsThree);
        pointsClicked = 3;

        // set text views
        scoreOneView.setText(String.valueOf(scoreOne));
        scoreTwoView.setText(String.valueOf(scoreTwo));
        scoreThreeView.setText(String.valueOf(scoreThree));
        remainingPointsView.setText(String.valueOf(this.remainingPoints));


        // update the sum score
        updateSumScore();
    }



    /**
     * Calculates and updates the total sum of all current scores.
     */
    private void updateSumScore() {
        // update sum
        currentSum = scoreOne + scoreTwo + scoreThree;
        this.totalScoreView.setText(String.valueOf(currentSum));
    }


    /**
     * Finishes the game and goes back to home activity.
     */
    private void finishGame() {
        String[] playerNames = new String[playerCount];
        String[] playerRounds = new String[playerCount];
        String[] averageScores = new String[playerCount];
        String[] highscores = new String[playerCount];

        // set parameters
        for (int i = 0; i < playerNames.length; i++) {
            Player player = this.players.get(i);

            playerNames[i] = player.getName();
            playerRounds[i] = Integer.toString(player.getDartsThrown() / 3);
            averageScores[i] = Float.toString((game - player.getPoints()) / (player.getDartsThrown() / 3));
            highscores[i] = Integer.toString(player.getHighscore());
        }

        // send to victory activity
        Intent victoryIntent = new Intent(this, VictoryActivity.class);

        victoryIntent.putExtra("playerName", players.get(currentPlayer).getName());
        victoryIntent.putExtra("playerNames", playerNames);
        victoryIntent.putExtra("playerRounds", playerRounds);
        victoryIntent.putExtra("averageScores", averageScores);
        victoryIntent.putExtra("highscores", highscores);

        startActivity(victoryIntent);

        // go back to home
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
                            GameActivity.super.onBackPressed();
                        }
                    }).create().show();
        } else {
            GameActivity.super.onBackPressed();
        }
    }

}
