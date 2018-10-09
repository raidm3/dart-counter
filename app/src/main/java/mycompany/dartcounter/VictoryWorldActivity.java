package mycompany.dartcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class VictoryWorldActivity extends AppCompatActivity {

    String playerNameWinner;
    String[] playerNames;
    String[] playerRounds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_victory);

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


        // get intent which started this activity
        Intent i = getIntent();

        // set retrieved parameters
        playerNameWinner = i.getStringExtra("winnerName");
        playerNames = i.getStringArrayExtra("playerNames");
        playerRounds = i.getStringArrayExtra("playerRounds");

        // set heading view
        TextView victoryHeading = (TextView) findViewById(R.id.victoryHeading);
        victoryHeading.setText(playerNameWinner.equals("") ? "Nobody won the game!" : playerNameWinner + " won the Game!");

        // set player stats
        for (int l = 0; l < playerNames.length; l++) {
            playerNameViews[l].setText(playerNames[l]);
            playerRoundsViews[l].setText(playerRounds[l]);
        }
    }


    public void returnToHome(View v) {
        finish();
    }
}
