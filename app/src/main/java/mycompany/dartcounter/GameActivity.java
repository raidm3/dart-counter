package mycompany.dartcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView scoreOneBarView;
    private TextView scoreTwoBarView;
    private TextView scoreThreeBarView;
    private TextView scoreOneView;
    private TextView scoreTwoView;
    private TextView scoreThreeView;
    private TextView totalScoreView;

    private int scoreOne;
    private int scoreTwo;
    private int scoreThree;
    private int scoreOneMultiplier = 1;
    private int scoreTwoMultiplier = 1;
    private int scoreThreeMultiplier = 1;
    private int currentSum;
    private int remainingPoints;

    private Button bull;
    private Button doubleBtn;
    private Button tripleBtn;

    private SeekBar scoreOneBar;
    private SeekBar scoreTwoBar;
    private SeekBar scoreThreeBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_new);

        // get intent which started this activity
        Intent i = getIntent();

        // get retrieved parameters
        playerCount = Integer.parseInt(i.getStringExtra("players"));
        game = Integer.parseInt(i.getStringExtra("game"));

        // add player text views
        playerFields[0] = (TextView) findViewById(R.id.playerOne);
        playerFields[1] = (TextView) findViewById(R.id.playerTwo);
        playerFields[2] = (TextView) findViewById(R.id.playerThree);
        playerFields[3] = (TextView) findViewById(R.id.playerFour);

        // create player models based on parameters
        for (int j = 1; j <= playerCount; j++) {
            Player player = new Player("Player" + j, game, playerFields[j-1]);

            // add to player array list
            players.add(player);
        }

        // show players
        for (int l = 0; l < players.size(); l++) {
            playerFields[l].setText(players.get(l).toString());
            playerFields[l].setVisibility(View.VISIBLE);
        }

        // set current player
        currentPlayer = 0;
        players.get(currentPlayer).setSelected(true);

        // set remaining points to game mode
        this.remainingPoints = game;
        remainingPointsView = (TextView) findViewById(R.id.remainingScore);
        remainingPointsView.setText(String.valueOf(this.remainingPoints));

        // set SeekBars
        scoreOneBar = (SeekBar) findViewById(R.id.seekBarShotOne);
        scoreTwoBar = (SeekBar) findViewById(R.id.seekBarShotTwo);
        scoreThreeBar = (SeekBar) findViewById(R.id.seekBarShotThree);

        // set score variables
        scoreOneBarView = (TextView) findViewById(R.id.shot_one_count);
        scoreTwoBarView = (TextView) findViewById(R.id.shot_two_count);
        scoreThreeBarView = (TextView) findViewById(R.id.shot_three_count);
        scoreOneView = (TextView) findViewById(R.id.shot_one_score);
        scoreTwoView = (TextView) findViewById(R.id.shot_two_score);
        scoreThreeView = (TextView) findViewById(R.id.shot_three_score);
        totalScoreView = (TextView) findViewById(R.id.total_score);

        // set event handler for SeekBars
        initializeSeekBarEventListener(scoreOneBar, scoreOneBarView, scoreOneView);
        initializeSeekBarEventListener(scoreTwoBar, scoreTwoBarView, scoreTwoView);
        initializeSeekBarEventListener(scoreThreeBar, scoreThreeBarView, scoreThreeView);

        // add point buttons to array list
        doubleBtn = (Button) findViewById(R.id.points_double);
        tripleBtn = (Button) findViewById(R.id.points_triple);
        bull = (Button)findViewById(R.id.points_bull);

        // set scores to default values
        resetScoreValues();
    }


    /**
     * Resets all score values to zero.
     */
    private void resetScoreValues() {
        // Reset progress on SeekBars
        scoreOneBar.setProgress(0);
        scoreTwoBar.setProgress(0);
        scoreThreeBar.setProgress(0);

        // Set score counters to progress
        scoreOneBarView.setText(String.format(Locale.GERMAN, "%d", scoreOneBar.getProgress()));
        scoreTwoBarView.setText(String.format(Locale.GERMAN, "%d", scoreTwoBar.getProgress()));
        scoreThreeBarView.setText(String.format(Locale.GERMAN, "%d", scoreThreeBar.getProgress()));

        // Set scores to progress
        scoreOneView.setText(String.format(Locale.GERMAN, "%d", scoreOneBar.getProgress()));
        scoreTwoView.setText(String.format(Locale.GERMAN, "%d", scoreTwoBar.getProgress()));
        scoreThreeView.setText(String.format(Locale.GERMAN, "%d", scoreThreeBar.getProgress()));
        totalScoreView.setText("0");
    }


    /**
     * Initialize event handler for SeekBars. Fires whenever the progress value changes.
     */
    private void initializeSeekBarEventListener(SeekBar seekbar, final TextView scoreBarView, final TextView scoreView) {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // When Progress value changed.
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                // Update the score to the progress value of the corresponding SeekBar
                switch (seekBar.getId()) {
                    case R.id.seekBarShotOne:
                        scoreOne = progressValue * scoreOneMultiplier;
                        scoreView.setText(String.format(Locale.GERMAN, "%d", scoreOne));
                        break;
                    case R.id.seekBarShotTwo:
                        scoreTwo = progressValue * scoreTwoMultiplier;
                        scoreView.setText(String.format(Locale.GERMAN, "%d", scoreTwo));
                        break;
                    case R.id.seekBarShotThree:
                        scoreThree = progressValue * scoreThreeMultiplier;
                        scoreView.setText(String.format(Locale.GERMAN, "%d", scoreThree));
                        break;
                }

                // Update the score of the score bar view
                scoreBarView.setText(String.format(Locale.GERMAN, "%d", progressValue));
            }

            // Notification that the user has started a touch gesture.
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            // Notification that the user has finished a touch gesture
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }


    /**
     * On Click Event Handler for radio buttons (double, triple. bull, bulls eye)
     *
     * @param v - View of Activity.
     */
    public void specialRadioButtonClick(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.shot_one_double:
                if (checked) {
                    int currentProgress = scoreOneBar.getProgress();

                    // set the multiplier, make sure the SeekBar is enabled, update score
                    scoreOneMultiplier = 2;
                    scoreOneBar.setEnabled(true);
                    scoreOneBar.setProgress(0);
                    scoreOneBar.setProgress(currentProgress);
                }
                break;
            case R.id.shot_one_triple:
                if (checked) {
                    int currentProgress = scoreOneBar.getProgress();

                    // set the multiplier, make sure the SeekBar is enabled, update score
                    scoreOneMultiplier = 3;
                    scoreOneBar.setEnabled(true);
                    scoreOneBar.setProgress(0);
                    scoreOneBar.setProgress(currentProgress);
                }
                break;
            case R.id.shot_one_bull:
                if (checked) {
                    scoreOne = 25;
                    scoreOneBar.setEnabled(false);
                    scoreOneView.setText(String.format(Locale.GERMAN, "%d", scoreOne));
                }
                break;
            case R.id.shot_one_bulls_eye:
                if (checked) {
                    scoreOne = 50;
                    scoreOneBar.setEnabled(false);
                    scoreOneView.setText(String.format(Locale.GERMAN, "%d", scoreOne));
                }
                break;
        }
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
        multiplier = doubleBtn.isSelected() ? 2 : (tripleBtn.isSelected() ? 3 : 1);

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
                if (multiplier == 2)
                    setPoints(50);
                else
                    setPoints(25);
                break;
        }

        // reset special button_primary backgrounds
        doubleBtn.setBackgroundColor(getResources().getColor(R.color.colorSpecial));
        tripleBtn.setBackgroundColor(getResources().getColor(R.color.colorSpecial));

        // reset special button_primary selections
        doubleBtn.setSelected(false);
        tripleBtn.setSelected(false);

        // reset button_primary modifications
        bull.setText("BULL");
        bull.setVisibility(View.VISIBLE);
    }


    /**
     * On Click Eventhandler for special button_primary: DOUBLE.
     *
     * @param v - View of Activity.
     */
    public void onPointsDoubleClicked(View v) {
        // check if button_primary is already selected
        if (doubleBtn.isSelected()) {
            doubleBtn.setBackgroundColor(getResources().getColor(R.color.colorSpecial));
            doubleBtn.setSelected(false);
            bull.setText("BULL");

            return;
        }

        // set background to selected
        doubleBtn.setBackgroundColor(getResources().getColor(R.color.colorSpecialSelected));

        // select button_primary
        doubleBtn.setSelected(true);

        // modify BULL button_primary
        bull.setText("BULL's EYE");
        bull.setVisibility(View.VISIBLE);

        // reset selection of other special button_primary
        tripleBtn.setSelected(false);
        tripleBtn.setBackgroundColor(getResources().getColor(R.color.colorSpecial));
    }


    /**
     * On Click Eventhandler for special button_primary: TRIPLE.
     * .
     * @param v - View of Activity.
     */
    public void onPointsTripleClicked(View v) {
        // check if button_primary is already selected
        if (tripleBtn.isSelected()) {
            tripleBtn.setBackgroundColor(getResources().getColor(R.color.colorSpecial));
            tripleBtn.setSelected(false);
            bull.setVisibility(View.VISIBLE);

            return;
        }

        // set background to selected
        tripleBtn.setBackgroundColor(getResources().getColor(R.color.colorSpecialSelected));

        // select button_primary
        tripleBtn.setSelected(true);

        // modify BULL button_primary
        bull.setText("BULL");
        bull.setVisibility(View.GONE);

        // reset selection of other special button_primary
        doubleBtn.setSelected(false);
        doubleBtn.setBackgroundColor(getResources().getColor(R.color.colorSpecial));
    }


    /**
     * On Click Eventhandler for button_primary: Accept.
     * Resets the scores and subtracts it from players points.
     *
     * @param v - View of Activity.
     */
    public void onCheckScoreClicked(View v) {
        Player player = players.get(currentPlayer);
        this.pointsClicked = 0;

        // set score values to 0 again
        resetScoreValues();

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

            finishGame();
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

        // reset current sum
        this.currentSum = 0;

        // set remaining points to players current points
        remainingPoints = player.getPoints();
        remainingPointsView.setText(String.valueOf(this.remainingPoints));
    }


    /**
     * On Click Eventhandler for button_primary: Undo.
     * Resets the last score to zero.
     *
     * @param v - View of Activity.
     */
    public void onUndoScoreClicked(View v) {

        /* ******** NEW! ********* */
        RadioGroup rGrpOne = (RadioGroup) findViewById(R.id.shot_one_radio_group);
        int currentProgress = scoreOneBar.getProgress();
        scoreOneBar.setEnabled(true);
        scoreOneBar.setProgress(0);
        rGrpOne.clearCheck();
        /* ******** NEW! ********* */

        if (pointsClicked == 0) {
            return;
        }

        switch (--pointsClicked) {
            case 0:
                // update remaining points
                this.remainingPoints += Integer.parseInt(scoreOneBarView.getText().toString());
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreOneBarView.setText(String.valueOf(0));

                break;
            case 1:
                // update remaining points
                this.remainingPoints += Integer.parseInt(scoreTwoBarView.getText().toString());
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreTwoBarView.setText(String.valueOf(0));

                break;
            case 2:
                // update remaining points
                this.remainingPoints += Integer.parseInt(scoreThreeBarView.getText().toString());
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreThreeBarView.setText(String.valueOf(0));

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
                scoreOneBarView.setText(String.valueOf(points));

                // update points clicked
                this.pointsClicked++;


                break;
            case 1:
                // update remaining points score
                remainingPoints -= points;
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreTwoBarView.setText(String.valueOf(points));

                // update points clicked
                this.pointsClicked++;

                break;
            case 2:
                // update remaining points score
                remainingPoints -= points;
                remainingPointsView.setText(String.valueOf(this.remainingPoints));

                // update score
                scoreThreeBarView.setText(String.valueOf(points));

                // update points clicked
                this.pointsClicked++;

                break;
        }

        // update sum score
        updateSumScore();
    }


    /**
     * Calculates and updates the total sum of all current scores.
     */
    private void updateSumScore() {
        int scoreOne = Integer.parseInt(this.scoreOneBarView.getText().toString());
        int scoreTow = Integer.parseInt(this.scoreTwoBarView.getText().toString());
        int scoreThree = Integer.parseInt(this.scoreThreeBarView.getText().toString());

        // update sum
        currentSum = scoreOne + scoreTow + scoreThree;
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

}
