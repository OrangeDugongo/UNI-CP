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
