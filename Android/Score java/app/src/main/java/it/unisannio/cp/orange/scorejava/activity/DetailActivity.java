package it.unisannio.cp.orange.scorejava.activity;


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 28/11/17
 *
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import it.unisannio.cp.orange.scorejava.R;
import it.unisannio.cp.orange.scorejava.Serie;

public class DetailActivity extends AppCompatActivity{

    private Serie item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        item = (Serie) getIntent().getSerializableExtra(MainActivity.EXTRA_ITEM);

        TextView nomeDetail = findViewById(R.id.nomeDetail);
        nomeDetail.setText(item.getNome());

        RatingBar ratingDetail = findViewById(R.id.ratingDetail);
        ratingDetail.setRating(item.getScore());
        ratingDetail.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                item.setScore(rating);
            }
        });
    }

    @Override
    public void onBackPressed() {                   //rispedisce indietro all'activity chiamante il valore di ritorno
        Intent result = new Intent();
        result.putExtra(MainActivity.EXTRA_ITEM, item);
        setResult(RESULT_OK, result);
        finish();
    }
}
