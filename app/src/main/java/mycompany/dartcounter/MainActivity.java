package mycompany.dartcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] games = {"301", "501"};
    private String[] players = {"1", "2", "3", "4"};
    private int currentGame = 0;
    private int currentPlayerCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set onclick listener on start button_primary
        Button startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(this);

        // set button_primary values to default
        Button gameButton = (Button) findViewById(R.id.gameButton);
        Button playerButton = (Button) findViewById(R.id.playerButton);
        gameButton.setText(games[0]);
        playerButton.setText(players[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button_primary, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra("players", players[currentPlayerCount]);
        gameIntent.putExtra("game", games[currentGame]);
        startActivity(gameIntent);
    }

    public void changeGame(View view) {
        Button gameButton = (Button) findViewById(R.id.gameButton);

        currentGame++;
        if (currentGame < games.length) {
            gameButton.setText(games[currentGame]);
        } else {
            currentGame = 0;
            gameButton.setText(games[currentGame]);
        }
    }

    public void changePlayer(View view) {
        Button playerButton = (Button) findViewById(R.id.playerButton);

        currentPlayerCount++;
        if (currentPlayerCount < players.length) {
            playerButton.setText(players[currentPlayerCount]);
        } else {
            currentPlayerCount = 0;
            playerButton.setText(players[currentPlayerCount]);
        }
    }
}
