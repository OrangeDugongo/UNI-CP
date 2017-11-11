package it.unisannio.cp.orange.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(list==null)
            list = new HashMap<>();
        for (String s: SERIE_TV)
            list.put(s, new Float(0));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case INSERT_CODE:
                if(resultCode==RESULT_OK)
                    list = (HashMap<String, Float>) data.getSerializableExtra(SERIE_TV_ARRAY);
                break;
            case  SCORE_CODE:
                if(resultCode==RESULT_OK)
                    list = (HashMap<String, Float>) data.getSerializableExtra(SERIE_TV_ARRAY);
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
        leaderboardIntent.putExtra(SERIE_TV_ARRAY, list);
        startActivity(leaderboardIntent);
    }

    public static final String SERIE_TV_ARRAY = "it.unisannio.cp.orange.score.ARRAY";
    private static final int INSERT_CODE = 100;
    private static final int SCORE_CODE = 101;
    private static final String[] SERIE_TV= new String[]{"Breaking Bad", "Rick and Morty", "Game of Thrones",
            "Stranger Things", "Halt and Catch Fire", "Big Mouth", "BoJack Horseman", "Silicon Valley", "New Girl"};

    private HashMap<String, Float> list;
}
