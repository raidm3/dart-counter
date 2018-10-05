package mycompany.dartcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private String numberOfPlayers = "1";
    private String game = "301";
    private String[] playerNames = new String[4];
    private String finishSetting = "singleOut";

    private EditText playerOneNameInput;
    private EditText playerTwoNameInput;
    private EditText playerThreeNameInput;
    private EditText playerFourNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set onclick listener on start button_primary
        Button startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(this);


        // initialize player name text inputs
        playerOneNameInput = (EditText) findViewById(R.id.playerNameOne);
        playerTwoNameInput = (EditText) findViewById(R.id.playerNameTwo);
        playerThreeNameInput = (EditText) findViewById(R.id.playerNameThree);
        playerFourNameInput = (EditText) findViewById(R.id.playerNameFour);

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

    @Override
    public void onClick(View v) {
        // Get all player names
        playerNames[0] = playerOneNameInput.getText().toString().equals("") ? "Player1" : playerOneNameInput.getText().toString();
        playerNames[1] = playerTwoNameInput.getText().toString().equals("") ? "Player2" : playerTwoNameInput.getText().toString();
        playerNames[2] = playerThreeNameInput.getText().toString().equals("") ? "Player3" : playerThreeNameInput.getText().toString();
        playerNames[3] = playerFourNameInput.getText().toString().equals("") ? "Player4" : playerFourNameInput.getText().toString();

        // Prepare and start new Intent
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra("players", numberOfPlayers);
        gameIntent.putExtra("playerNames", playerNames);
        gameIntent.putExtra("game", game);
        gameIntent.putExtra("finishSetting", finishSetting);
        startActivity(gameIntent);
    }


    /**
     * Click event handler for the finish settings radio buttons.
     * @param v
     */
    public void onFinishSettingClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.singleOutBtn:
                if (checked)
                    finishSetting = "singleOut";
                break;
            case R.id.doubleOutBtn:
                if (checked)
                    finishSetting = "doubleOut";
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
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback
    }
}
