package it.unisannio.cp.orange.score.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.HashMap;

import it.unisannio.cp.orange.score.R;
import it.unisannio.cp.orange.score.Serie;

public class ScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        list = (HashMap<String, Serie>) getIntent().getSerializableExtra(MainActivity.SERIE_TV_ARRAY);
        star = findViewById(R.id.star);
        search = findViewById(R.id.search);

        String[] stringList = new String[list.size()];
        list.keySet().toArray(stringList);
        adapter = new ArrayAdapter<>(this, R.layout.element, stringList);
        search.setAdapter(adapter);


        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                star.setRating(list.get(parent.getAdapter().getItem(position)).getScore());
            }
        });


        star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(!list.containsKey(search.getText().toString()))
                    Toast.makeText(ScoreActivity.this, R.string.score_toast, Toast.LENGTH_LONG).show();
                else if(rating!=list.get(search.getText().toString()).getScore()){
                    list.put(search.getText().toString(),new Serie(search.getText().toString(),rating));
                    search.setText("");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra(MainActivity.SERIE_TV_ARRAY, list);
        setResult(RESULT_OK, result);
        finish();
    }


    private HashMap<String, Serie> list;
    private RatingBar star;
    private AutoCompleteTextView search;
    private ArrayAdapter<String> adapter;
}
