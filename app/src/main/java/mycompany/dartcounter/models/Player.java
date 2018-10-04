package mycompany.dartcounter.models;


import android.graphics.Color;
import android.widget.TextView;

public class Player {

    private String name;
    private int points;
    private TextView playerView;
    private int dartsThrown;
    private int highscore;


    public Player (String name, int points, TextView playerView) {
        this.name = name;
        this.points = points;
        this.playerView = playerView;
        this.dartsThrown = 0;
        this.highscore = 0;

        // set view text
        this.playerView.setText(this.toString());
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getPoints() {
        return this.points;
    }


    public void setPoints(int points) {
        this.points = points;
    }


    public int getDartsThrown() {
        return this.dartsThrown;
    }


    public void addDartsThrown(int darts) {
        this.dartsThrown += darts;
    }


    public void subtractDartsThrown() {
        this.dartsThrown = this.dartsThrown - 1;
    }


    public int getHighscore() {
        return this.highscore;
    }


    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }


    public void setSelected(boolean isSelected) {
        if (isSelected)
            this.playerView.setTextColor(Color.RED);
        else
            this.playerView.setTextColor(Color.BLACK);
    }


    public void subtractPoints(int sum) {
        this.points = this.points - sum;
        this.playerView.setText(this.toString());
    }


    public String toString() {
        return this.name + "\n" + this.points;
    }
}
