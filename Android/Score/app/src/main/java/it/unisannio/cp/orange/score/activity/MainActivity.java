package it.unisannio.cp.orange.score.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import it.unisannio.cp.orange.score.R;
import it.unisannio.cp.orange.score.Serie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(list==null){
            list = new HashMap<>();
            for (String s: SERIE_TV)
                list.put(s, new Serie(s,0));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case INSERT_CODE:
                if(resultCode==RESULT_OK)
                    list = (HashMap<String, Serie>) data.getSerializableExtra(SERIE_TV_ARRAY);
                break;
            case  SCORE_CODE:
                if(resultCode==RESULT_OK)
                    list = (HashMap<String, Serie>) data.getSerializableExtra(SERIE_TV_ARRAY);
                break;
        }
    }

    public void launchInsertActivity(View v){
        Intent insertIntent = new Intent(this, InsertActivity.class);
        insertIntent.putExtra(SERIE_TV_ARRAY, list);
        startActivityForResult(insertIntent, INSERT_CODE);
    }

    public void launchScoreActivity(View v){
        if(!list.isEmpty()) {
            Intent scoreIntent = new Intent(this, ScoreActivity.class);
            scoreIntent.putExtra(SERIE_TV_ARRAY, list);
            startActivityForResult(scoreIntent, SCORE_CODE);
        }else
            Toast.makeText(this, R.string.leaderboard_text, Toast.LENGTH_LONG).show();
    }

    public void launchLeaderboardActivity(View v){
        if(!list.isEmpty()){
            Intent leaderboardIntent = new Intent(this, LeaderboardListActivity.class);
            leaderboardIntent.putExtra(SERIE_TV_ARRAY, list);
            startActivity(leaderboardIntent);
        }else
            Toast.makeText(this, R.string.leaderboard_text, Toast.LENGTH_LONG).show();
    }

    public static final String SERIE_TV_ARRAY = "it.unisannio.cp.orange.score.ARRAY";
    private static final int INSERT_CODE = 100;
    private static final int SCORE_CODE = 101;
    private static final String[] SERIE_TV= new String[]{"Breaking Bad", "Rick and Morty", "Game of Thrones",
            "Stranger Things", "Halt and Catch Fire", "Big Mouth", "BoJack Horseman", "Silicon Valley", "New Girl", "Grey's Anatomy"};

    private HashMap<String, Serie> list;
}
