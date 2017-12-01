/*
 *      Copyright (c) 2017 Raffaele Mignone
 *
 *      This file is part of  Score30
 *
 *      Score30 is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 2 of the License, or
 *      (at your option) any later version.
 *
 *      Score30 is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with Score30.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.unisannio.cp.orange.score30.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import it.unisannio.cp.orange.score30.R
import it.unisannio.cp.orange.score30.Serie
import kotlinx.android.synthetic.main.detail_layout.*

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 16/11/17
 *
 */

class DetailActivity : AppCompatActivity() {
    var item: Serie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)
        item = intent.getSerializableExtra(MainActivity.EXTRA_ITEM) as Serie?
        nomeDetail.text=item?.nome
        ratingDetail.rating=item?.score?:0f
        ratingDetail.setOnRatingBarChangeListener({ _, fl: Float, _ -> item?.score=fl })
    }

    override fun onBackPressed() {  //restituisce l'elemento con le eventuali modifiche
        val result = Intent()
        result.putExtra(MainActivity.EXTRA_ITEM, item)
        setResult(Activity.RESULT_OK, result)
        finish()
    }
}
