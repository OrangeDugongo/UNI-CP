package it.unisannio.cp.orange.score20.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import it.unisannio.cp.orange.score20.R
import it.unisannio.cp.orange.score20.Serie
import it.unisannio.cp.orange.score20.activity.MainActivity

class SerieDetail : Fragment() {
    private var nome: TextView? = null
    private var score: RatingBar? = null
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
        return inflater.inflate(R.layout.fragment_second_fragments, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        nome = activity.findViewById(R.id.nomeDetail)
        score = activity.findViewById(R.id.rating)
        score?.setOnRatingBarChangeListener({ratingBar: RatingBar, fl: Float, b: Boolean ->
            item?.score=fl
            onRatingChange?.onRatingChange(item as Serie)
        })
    }

    fun showDetail(item: Serie){
        this.item=item
        nome?.text = item.nome
        score?.rating=item.score
    }
}
