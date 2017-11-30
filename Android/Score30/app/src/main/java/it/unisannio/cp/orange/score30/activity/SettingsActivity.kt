package it.unisannio.cp.orange.score30.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import it.unisannio.cp.orange.score30.R
import kotlinx.android.synthetic.main.activity_info.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val sp = getSharedPreferences("settings", Context.MODE_PRIVATE)

        if(sp.getBoolean("pro", false))
            pro.setText(R.string.settings_pro)
        else
            pro.setText(R.string.settings_became_pro)

        pro.setOnLongClickListener({ _ ->
            val editor = sp.edit()
            val b = sp.getBoolean("pro", false)
            editor.putBoolean("pro", !b)
            editor.apply()
            if(b)
                pro.setText(R.string.settings_became_pro)
            else
                pro.setText(R.string.settings_pro)
            true
        })

        github.setText(R.string.settings_github)
        github.setOnClickListener({ _ ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.settings_github_uri)))
            startActivity(intent)
        })
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
