package it.unisannio.cp.orange.serie_rest.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import it.unisannio.cp.orange.serie_rest.R;
import it.unisannio.cp.orange.serie_rest.util.List;
import it.unisannio.cp.orange.serie_rest.util.SerieAdapter;
import it.unisannio.cp.orange.serie_rest.util.rest.GetList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("settings", MODE_PRIVATE);
        List.instance().init(this);

        if(settings.getBoolean("first lauch", true)){
            new GetList().execute("http://192.168.1.241:8182/series");
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("first lauch", false);
            editor.apply();
        }else
            List.instance().load();

        ListView list = findViewById(R.id.list);

        list.setAdapter(List.instance().getAdapter());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
       // }

        return super.onOptionsItemSelected(item);
    }

}
