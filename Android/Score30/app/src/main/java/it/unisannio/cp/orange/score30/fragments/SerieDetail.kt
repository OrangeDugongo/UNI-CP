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

package it.unisannio.cp.orange.score30.fragments

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.unisannio.cp.orange.score30.R
import it.unisannio.cp.orange.score30.Serie
import it.unisannio.cp.orange.score30.activity.MainActivity
import kotlinx.android.synthetic.main.detail_layout.*

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 16/11/17
 *
 */


class SerieDetail : Fragment() {
    private var onRatingChange: OnRatingChange? = null
    private var item: Serie? = null

    interface OnRatingChange{
        fun onRatingChange(item: Serie)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnRatingChange)
            onRatingChange=context
        else
            Log.e(MainActivity.ERROR, "unmatching type")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ratingDetail.setOnRatingBarChangeListener({ _, fl: Float, _ ->
            if(fl!=item?.score) {
                item?.score = fl
                onRatingChange?.onRatingChange(item as Serie)
            }
        })
    }

    fun showDetail(item: Serie){
        this.item=item
        nomeDetail.text=item.nome
        ratingDetail.rating=item.score
    }
}
