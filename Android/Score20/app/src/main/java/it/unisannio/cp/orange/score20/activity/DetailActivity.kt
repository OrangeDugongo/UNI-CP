package it.unisannio.cp.orange.score20.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RatingBar
import it.unisannio.cp.orange.score20.R
import it.unisannio.cp.orange.score20.Serie
import kotlinx.android.synthetic.main.detail_layout.*

class DetailActivity : AppCompatActivity() {
    var item: Serie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)
        item = intent.getSerializableExtra(MainActivity.EXTRA_ITEM) as Serie?
        nomeDetail.text=item?.nome
        ratingDetail.rating=item?.score?:0f
        ratingDetail.setOnRatingBarChangeListener({ratingBar: RatingBar, fl: Float, b: Boolean ->
            item?.score=fl
        })
    }

    override fun onBackPressed() {
        val result = Intent()
        result.putExtra(MainActivity.EXTRA_ITEM, item)
        setResult(Activity.RESULT_OK, result)
        finish()
    }
}
