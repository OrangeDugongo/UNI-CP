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

import android.app.Activity
import android.app.AlertDialog
import android.app.ListFragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import it.unisannio.cp.orange.score30.R
import it.unisannio.cp.orange.score30.Serie
import it.unisannio.cp.orange.score30.SerieAdapter
import it.unisannio.cp.orange.score30.activity.MainActivity
import it.unisannio.cp.orange.score30.activity.SettingsActivity
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
    private var order = 1

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

        read()
        val sp = activity.getSharedPreferences("settings", Activity.MODE_PRIVATE)
        if(!sp.getBoolean("pro", false))    //aggiunge alla lista il banner pubblicitario
            map.put(getString(R.string.buy_pro), Serie("Buy Pro", 10f))
        listAdapter = SerieAdapter(sortByValue(map), context)
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        registerForContextMenu(listView)        //registra la lista per il context menu
    }

    override fun onStart() {
        super.onStart()
        val sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE)
        order = sp.getInt("order", 1)
        listAdapter = SerieAdapter(sortByValue(map), context)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        val item = listView.getItemAtPosition(position) as Serie
        if(item.nome == getString(R.string.buy_pro)) {  //se si è cliccato il banner
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.bitcoin_uri)))
            if(intent.resolveActivity(activity.packageManager) != null)     //controlla se il telefono può gestire pagamenti bitcoin
                startActivity(intent)
            else
                paymentDialog().show()      //mostra il qr code per il pagamento
        }else
            onClickListener?.onClickListener(listView.getItemAtPosition(position) as Serie)
    }

    fun refresh(item: Serie){
        map.put(item.nome, item)
        listAdapter = SerieAdapter(sortByValue(map), context)
        save(item)
    }

    fun add(name: String): Boolean{
        if(map.containsKey(name)) return false
        map.put(name, Serie(name, 0f))
        listAdapter = SerieAdapter(sortByValue(map), context)
        save(Serie(name, 0f))
        return true
    }

    fun delete(item: Serie): Boolean{
        map.remove(item.nome)
        listAdapter = SerieAdapter(sortByValue(map), context)
        remove(item.nome)
        return true
    }

    fun toZero(item: Serie): Boolean{
        if(item.score==0f) true
        item.score=0f
        map.put(item.nome, item)
        listAdapter = SerieAdapter(sortByValue(map), context)
        save(item)
        return true
    }

    private fun sortByValue(unsort: TreeMap<String, Serie>): TreeMap<String, Serie> {
        val sort = TreeMap<String, Serie>(Comparator<String> { o1, o2 ->
            val a1 = (unsort[o1] as Serie).score
            val a2 = (unsort[o2] as Serie).score
            var result = a2.compareTo(a1)*order
            if (result == 0)
                result = o1.compareTo(o2)
            result
        })
        sort.putAll(unsort)
        return sort
    }

    fun save(item: Serie){      //salva un elemento nelle SP
        Log.d("SAVE", "in")
        val sp = context.getSharedPreferences("list", Activity.MODE_PRIVATE)
        sp.editor { putFloat(item.nome, item.score) }
    }

    fun remove(nome: String){   //rimuove un elemento dalle SP
        Log.d("REMOVE", "in")
        val sp = context.getSharedPreferences("list", Activity.MODE_PRIVATE)
        sp.editor { remove(nome) }
    }

    fun read(){     //ripristina la lista dalla SP
        Log.d("READ", "in")
        val sp = context.getSharedPreferences("list", Activity.MODE_PRIVATE)
        sp.all.keys.forEach { map.put(it, Serie(it, sp.getFloat(it, 0f))) }
    }

    private fun paymentDialog(): AlertDialog{       //crea un dialog
        val view = activity.layoutInflater.inflate(R.layout.payment_dialog, null)
        val qr = view.findViewById<ImageView>(R.id.qr)
        val path = "android.resource://" + activity.packageName + "/" + R.raw.qr_code
        qr.setImageURI(Uri.parse(path))
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        builder.setTitle(getString(R.string.buy_pro))
        builder.setPositiveButton("ok", null)
        return builder.create()
    }
}
//shortcut per editare una SharedPreferences
fun SharedPreferences.editor(func: SharedPreferences.Editor.() -> SharedPreferences.Editor) = this.edit().func().apply()
