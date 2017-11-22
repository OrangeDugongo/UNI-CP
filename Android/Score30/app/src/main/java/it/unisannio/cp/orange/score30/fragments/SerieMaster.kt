package it.unisannio.cp.orange.score20.fragment

import android.app.ListFragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import it.unisannio.cp.orange.score30.R
import it.unisannio.cp.orange.score30.Serie
import it.unisannio.cp.orange.score30.SerieAdapter
import it.unisannio.cp.orange.score30.activity.MainActivity
import java.util.*

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 18/11/17
 *
 */


class SerieMaster : ListFragment() {
    private val map : TreeMap<String, Serie> = TreeMap()
    private var onClickListener : OnClickListener? = null

    interface OnClickListener{
        fun onClickListener(item: Serie)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnClickListener)
            onClickListener = context
        else
            Log.e(MainActivity.ERROR, "unmatching type")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val array = resources.getStringArray(R.array.serie)
        for(str in array)
            map.put(str, Serie(str, 0f))
        listAdapter = SerieAdapter(map, context)
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        registerForContextMenu(listView)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        onClickListener?.onClickListener(listView.getItemAtPosition(position) as Serie)
    }

    fun refresh(item: Serie){
        map.put(item.nome, item)
        listAdapter = SerieAdapter(sortByValue(map), context)
    }

    fun delete(item: Serie): Boolean{
        map.remove(item.nome)
        listAdapter = SerieAdapter(sortByValue(map), context)
        return true
    }

    fun toZero(item: Serie): Boolean{
        if(item.score==0f) true
        item.score=0f
        map.put(item.nome, item)
        listAdapter = SerieAdapter(sortByValue(map), context)
        return true
    }

    private fun sortByValue(unsort: TreeMap<String, Serie>): TreeMap<String, Serie> {
        val sort = TreeMap<String, Serie>(Comparator<String> { o1, o2 ->
            val a1 = (unsort[o1] as Serie).score
            val a2 = (unsort[o2] as Serie).score
            var result = a2.compareTo(a1)
            if (result == 0)
                result = o1.compareTo(o2)
            result
        })
        sort.putAll(unsort)
        return sort
    }
}
