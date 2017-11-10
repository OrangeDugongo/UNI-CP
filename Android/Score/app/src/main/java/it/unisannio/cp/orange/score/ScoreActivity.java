package it.unisannio.cp.orange.score;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import java.util.HashMap;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        HashMap<String, Float> list = (HashMap<String, Float>) getIntent().getSerializableExtra(MainActivity.SERIE_TV_ARRAY);

        MultiAutoCompleteTextView search = findViewById(R.id.search);
        String[] stringList = (String[]) list.keySet().toArray();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.element, stringList);

        search.setAdapter(adapter);

    }
}
