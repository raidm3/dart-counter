package mycompany.dartcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class VictoryActivity extends AppCompatActivity {

    String playerNameWinner;
    String[] playerNames;
    String[] playerRounds;
    String[] averageScores;
    String[] highscores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        // get text views
        TextView[] playerNameViews = {
                (TextView) findViewById(R.id.playerName_p1),
                (TextView) findViewById(R.id.playerName_p2),
                (TextView) findViewById(R.id.playerName_p3),
                (TextView) findViewById(R.id.playerName_p4)
        };

        TextView[] playerRoundsViews = {
                (TextView) findViewById(R.id.darts_thrown_p1),
                (TextView) findViewById(R.id.darts_thrown_p2),
                (TextView) findViewById(R.id.darts_thrown_p3),
                (TextView) findViewById(R.id.darts_thrown_p4)
        };

        TextView[] averageScoreViews = {
                (TextView) findViewById(R.id.average_score_p1),
                (TextView) findViewById(R.id.average_score_p2),
                (TextView) findViewById(R.id.average_score_p3),
                (TextView) findViewById(R.id.average_score_p4)
        };

        TextView[] highscoreViews = {
                (TextView) findViewById(R.id.highscore_p1),
                (TextView) findViewById(R.id.highscore_p2),
                (TextView) findViewById(R.id.highscore_p3),
                (TextView) findViewById(R.id.highscore_p4)
        };


        // get intent which started this activity
        Intent i = getIntent();

        // set retrieved parameters
        playerNameWinner = i.getStringExtra("playerName");
        playerNames = i.getStringArrayExtra("playerNames");
        playerRounds = i.getStringArrayExtra("playerRounds");
        averageScores = i.getStringArrayExtra("averageScores");
        highscores = i.getStringArrayExtra("highscores");

        // set heading view
        TextView victoryHeading = (TextView) findViewById(R.id.victoryHeading);
        victoryHeading.setText(playerNameWinner + " won the game!");

        // set player stats
        for (int l = 0; l < playerNames.length; l++) {
            playerNameViews[l].setText(playerNames[l]);
            playerRoundsViews[l].setText(playerRounds[l]);
            averageScoreViews[l].setText(averageScores[l]);
            highscoreViews[l].setText(highscores[l]);
        }
    }


    public void returnToHome(View v) {
        finish();
    }
}
