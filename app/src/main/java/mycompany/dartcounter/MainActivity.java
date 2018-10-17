package mycompany.dartcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String numberOfPlayers = "1";
    private String game = "301";
    private String[] playerNames = new String[4];
    private String gameSetting = "singleOut";

    private EditText playerOneNameInput;
    private EditText playerTwoNameInput;
    private EditText playerThreeNameInput;
    private EditText playerFourNameInput;

    private RadioButton singleOutBtn;
    private RadioButton doubleOutBtn;
    private RadioButton classicWorldBtn;
    private RadioButton scoreWorldBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize player name text inputs
        playerOneNameInput = (EditText) findViewById(R.id.playerNameOne);
        playerTwoNameInput = (EditText) findViewById(R.id.playerNameTwo);
        playerThreeNameInput = (EditText) findViewById(R.id.playerNameThree);
        playerFourNameInput = (EditText) findViewById(R.id.playerNameFour);

        // initialize game setting buttons
        singleOutBtn = (RadioButton) findViewById(R.id.singleOutBtn);
        doubleOutBtn = (RadioButton) findViewById(R.id.doubleOutBtn);
        classicWorldBtn = (RadioButton) findViewById(R.id.classicWorldBtn);
        scoreWorldBtn = (RadioButton) findViewById(R.id.scoreWorldBtn);

        // populate player spinner
        Spinner playerSpinner = (Spinner) findViewById(R.id.playerSpinner);
        ArrayAdapter<CharSequence> playerAdapter = ArrayAdapter.createFromResource(this,
                R.array.players_number, R.layout.custom_spinner_item);
        playerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        playerSpinner.setAdapter(playerAdapter);
        playerSpinner.setOnItemSelectedListener(this);

        // populate game spinner
        Spinner gameSpinner = (Spinner) findViewById(R.id.gameSpinner);
        ArrayAdapter<CharSequence> gameAdapter = ArrayAdapter.createFromResource(this,
                R.array.games, R.layout.custom_spinner_item);
        gameAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        gameSpinner.setAdapter(gameAdapter);
        gameSpinner.setOnItemSelectedListener(this);
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


    /**
     * Click event handler for the start button.
     * @param v view
     */
    public void onStartClicked(View v) {
        // Get all player names
        playerNames[0] = playerOneNameInput.getText().toString().equals("") ? "Player1" : playerOneNameInput.getText().toString();
        playerNames[1] = playerTwoNameInput.getText().toString().equals("") ? "Player2" : playerTwoNameInput.getText().toString();
        playerNames[2] = playerThreeNameInput.getText().toString().equals("") ? "Player3" : playerThreeNameInput.getText().toString();
        playerNames[3] = playerFourNameInput.getText().toString().equals("") ? "Player4" : playerFourNameInput.getText().toString();

        // Prepare and start new Intent
        if (game.equals("301") || game.equals("501") || game.equals("701")) {
            Intent gameIntent = new Intent(this, GameActivity.class);

            // Set parameters for next Activity
            gameIntent.putExtra("players", numberOfPlayers);
            gameIntent.putExtra("playerNames", playerNames);
            gameIntent.putExtra("game", game);
            gameIntent.putExtra("gameSetting", gameSetting);

            // Start new Activity
            startActivity(gameIntent);
        } else if (game.equals("Round the World")) {
            Intent gameIntent = new Intent(this, GameWorldActivity.class);

            // Set parameters for next Activity
            gameIntent.putExtra("players", numberOfPlayers);
            gameIntent.putExtra("playerNames", playerNames);
            gameIntent.putExtra("gameSetting", gameSetting);

            // Start new Activity
            startActivity(gameIntent);
        }
    }


    /**
     * Click event handler for the finish settings radio buttons.
     * @param v view
     */
    public void onGameSettingClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.singleOutBtn:
                if (checked)
                    gameSetting = "singleOut";
                break;
            case R.id.doubleOutBtn:
                if (checked)
                    gameSetting = "doubleOut";
                break;
            case R.id.classicWorldBtn:
                if (checked)
                    gameSetting = "classic";
                break;
            case R.id.scoreWorldBtn:
                if (checked)
                    gameSetting = "score";
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.playerSpinner) {
            numberOfPlayers = (String) adapterView.getItemAtPosition(i);

            // Hide/show name input fields depending on number of players selected.
            switch (numberOfPlayers) {
                case "1":
                    playerOneNameInput.setVisibility(View.VISIBLE);
                    playerTwoNameInput.setVisibility(View.INVISIBLE);
                    playerThreeNameInput.setVisibility(View.INVISIBLE);
                    playerFourNameInput.setVisibility(View.INVISIBLE);
                    break;
                case "2":
                    playerOneNameInput.setVisibility(View.VISIBLE);
                    playerTwoNameInput.setVisibility(View.VISIBLE);
                    playerThreeNameInput.setVisibility(View.INVISIBLE);
                    playerFourNameInput.setVisibility(View.INVISIBLE);
                    break;
                case "3":
                    playerOneNameInput.setVisibility(View.VISIBLE);
                    playerTwoNameInput.setVisibility(View.VISIBLE);
                    playerThreeNameInput.setVisibility(View.VISIBLE);
                    playerFourNameInput.setVisibility(View.INVISIBLE);
                    break;
                case "4":
                    playerOneNameInput.setVisibility(View.VISIBLE);
                    playerTwoNameInput.setVisibility(View.VISIBLE);
                    playerThreeNameInput.setVisibility(View.VISIBLE);
                    playerFourNameInput.setVisibility(View.VISIBLE);
                    break;
            }
        } else if (adapterView.getId() == R.id.gameSpinner) {
            game = (String) adapterView.getItemAtPosition(i);

            // Hide/show game setting buttons depending on which game has been selected.
            switch (game) {
                case "301":
                case "501":
                case "701":
                    singleOutBtn.setVisibility(View.VISIBLE);
                    doubleOutBtn.setVisibility(View.VISIBLE);
                    classicWorldBtn.setVisibility(View.GONE);
                    scoreWorldBtn.setVisibility(View.GONE);

                    // Reset button to default value
                    singleOutBtn.performClick();
                    break;
                case "Round the World":
                    singleOutBtn.setVisibility(View.GONE);
                    doubleOutBtn.setVisibility(View.GONE);
                    classicWorldBtn.setVisibility(View.VISIBLE);
                    scoreWorldBtn.setVisibility(View.VISIBLE);

                    // Reset button to default value
                    classicWorldBtn.performClick();
                    break;
            }

            Log.d("test", gameSetting);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback
    }
}
