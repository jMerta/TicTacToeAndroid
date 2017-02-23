package pl.jmerta.demoapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 = yellow, 1 = red
    boolean isActive = true;
    int activePlayer = 0;
    // 2  = empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningConditions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6} , {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && isActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-500f);
        if(activePlayer == 0 ) {

            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        }else
        {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }

        }
        counter.animate().translationY(-15f).setDuration(300);

        for (int[] winningCondition : winningConditions){
            if (gameState[winningCondition[0]] == gameState[winningCondition[1]] &&
                    gameState[winningCondition[1]] == gameState[winningCondition[2]] && gameState[winningCondition[0]] != 2){
                String winner = "Red";
                if (gameState[winningCondition[0]] == 0) {
                     winner = "Yellow";
                }
                isActive=false;
                TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner + " has won");
                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                GridLayout playScene = (GridLayout)findViewById(R.id.playScene);

                playScene.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);
            } else {
                boolean isOver =true;
                for (int counterState : gameState){
                    if (counterState == 2) isOver= false;
                }
                if (isOver){
                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText("It's a draw");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    GridLayout playScene = (GridLayout)findViewById(R.id.playScene);

                    playScene.setVisibility(View.INVISIBLE);
                    layout.setVisibility(View.VISIBLE);
                }
            }

        }
    }
    public void playAgain(View view){

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        GridLayout playScene = (GridLayout)findViewById(R.id.playScene);
        layout.setVisibility(View.INVISIBLE);
        isActive=true;
        activePlayer = 0;
        for (int i =0; i <gameState.length; i++) {
            gameState[i] = 2;
        }

        for (int i =0; i<playScene.getChildCount(); i++){
            ((ImageView) playScene.getChildAt(i)).setImageResource(0);
        }
        playScene.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
