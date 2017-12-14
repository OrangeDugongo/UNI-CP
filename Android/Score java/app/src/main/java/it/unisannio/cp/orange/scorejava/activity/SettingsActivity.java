package it.unisannio.cp.orange.scorejava.activity;


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 28/11/17
 *
 */


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import it.unisannio.cp.orange.scorejava.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);

        final TextView pro = findViewById(R.id.pro);
        if(sp.getBoolean("pro", false))             //determina se l'utente ha comprato la versione premium
            pro.setText(R.string.settings_pro);
        else
            pro.setText(R.string.settings_became_pro);

        pro.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {                    //metodo di test per vedere il cambio tra pro e non-pro
                SharedPreferences.Editor editor = sp.edit();
                boolean not = !sp.getBoolean("pro", false);
                editor.putBoolean("pro", not);
                editor.apply();
                if(not)
                    pro.setText(R.string.settings_pro);
                else
                    pro.setText(R.string.settings_became_pro);
                return false;
            }
        });

        TextView github = findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           //crea un intent per visualizzare un sito web
                Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.settings_github_uri)));
                startActivity(web);
            }
        });

        Switch order = findViewById(R.id.order);
        order.setChecked(sp.getInt("order", 1)==1);         //imposta lo switch nella corretta posizione
        order.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {    //salva sulle sp la nuova preferenza dell'ordine
                SharedPreferences.Editor editor = sp.edit();
                if(isChecked)
                    editor.putInt("order", 1);
                else
                    editor.putInt("order", -1);
                editor.apply();
            }
        });

    }
}
