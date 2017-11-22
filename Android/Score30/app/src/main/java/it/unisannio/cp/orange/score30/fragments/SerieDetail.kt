package it.unisannio.cp.orange.score20.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
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

        ratingDetail.setOnRatingBarChangeListener({ratingBar: RatingBar, fl: Float, b: Boolean ->
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
