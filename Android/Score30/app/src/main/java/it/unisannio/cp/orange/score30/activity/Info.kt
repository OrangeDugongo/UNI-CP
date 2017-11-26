package it.unisannio.cp.orange.score30.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import it.unisannio.cp.orange.score30.R
import kotlinx.android.synthetic.main.activity_info.*

class Info : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val sp = getSharedPreferences("settings", Context.MODE_PRIVATE)

        order.isChecked = sp.getInt("order", 1) == 1

        order.setOnCheckedChangeListener({ _, isChecked: Boolean ->
            val editor = sp.edit()
            if(isChecked)
                editor.putInt("order", 1)
            else
                editor.putInt("order", -1)
            editor.apply()
        })
    }
}