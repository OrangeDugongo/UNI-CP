package it.unisannio.cp.orange.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String SERIE_TV_ARRAY = "it.unisannio.cp.orange.score.ARRAY";
    public static final int INSERT_CODE = 100;
    public static final int SCORE_CODE = 101;

    private HashMap<String, Float> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new HashMap<>();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case INSERT_CODE:
                list = (HashMap<String, Float>) data.getSerializableExtra(SERIE_TV_ARRAY);
                for (String s: list.keySet())
                    Log.v("test", s);
                break;
        }
    }

    public void launchInsertActivity(View v){
        Intent insertIntent = new Intent(this, InsertActivity.class);
        insertIntent.putExtra(SERIE_TV_ARRAY, list);
        startActivityForResult(insertIntent, INSERT_CODE);
    }

    public void launchScoreActivity(View v){
        Intent scoreIntent = new Intent(this, ScoreActivity.class);
        scoreIntent.putExtra(SERIE_TV_ARRAY, list);
        startActivityForResult(scoreIntent, SCORE_CODE);
    }

    public void launchLeaderboardActivity(View v){
        Intent leaderboardIntent = new Intent(this, LeaderboardActivity.class);
    }
}
