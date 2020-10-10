package com.ishitadhoot.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [] [] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {2,4,6}, {0,4,8}};
    boolean gameisActive = true;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        if(gameState[Integer.parseInt(counter.getTag().toString())]==2 && gameisActive){
            if (count == 0) {
                counter.setImageResource(R.drawable.red);
                counter.animate().alpha(1f).setDuration(1000);
                gameState[Integer.parseInt(counter.getTag().toString())]=0;
                count = 1;
                if(victory()){
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerText);
                    winnerMessage.setText("Red has won!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    layout.animate().alpha(1f);
                }

            } else {
                counter.setImageResource(R.drawable.yellow);
                counter.animate().alpha(1f).setDuration(1000);
                gameState[Integer.parseInt(counter.getTag().toString())]=1;
                count = 0;
                if(victory()){
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerText);
                    winnerMessage.setText("Yellow has won!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    layout.animate().alpha(1f);
                }
            }
        }
    }

    public void playAgain(View view){
        gameisActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.animate().alpha(0f);
        layout.setVisibility(View.GONE);
        count = 0;
        for(int i = 0; i<gameState.length; i++){
            gameState[i]=2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        for(int i = 0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    public boolean victory(){
        for(int [] winningPosition: this.winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                    gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
            gameState[winningPosition[0]]!=2){
                gameisActive = false;
                return true;
            }
            else {
                gameisActive = false;
                for(int counter: gameState){
                    if(counter == 2){
                        gameisActive = true;
                    }
                }
                if(!gameisActive){
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerText);
                    winnerMessage.setText("It's a draw!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    layout.animate().alpha(1f);
                }
            }

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.GONE);
    }
}
