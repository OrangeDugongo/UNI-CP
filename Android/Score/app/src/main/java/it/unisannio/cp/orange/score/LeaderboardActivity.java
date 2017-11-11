package it.unisannio.cp.orange.score;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        list = (HashMap<String, Float>) getIntent().getSerializableExtra(MainActivity.SERIE_TV_ARRAY);
        TreeMap<String, Float> sortList = sortByValue(list);
        TextView leaderboard = findViewById(R.id.leaderboard);
        String leaderboardStr = "";

        for(String s: sortList.keySet())
            leaderboardStr+= s + " " + sortList.get(s).toString() + "\n\n";

        leaderboard.setText(leaderboardStr);
    }

    private TreeMap<String, Float> sortByValue(final HashMap<String, Float> unsort){
        TreeMap<String, Float> sort = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Float a1 = unsort.get(o1);
                Float a2 = unsort.get(o2);
                int result = a2.compareTo(a1);
                if(result==0)
                    result = o1.compareTo(o2);
                return result;
            }
        });
        sort.putAll(unsort);
        return sort;
    }

    private HashMap<String, Float> list;
}
