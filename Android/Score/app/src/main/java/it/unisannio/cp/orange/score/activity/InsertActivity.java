package it.unisannio.cp.orange.score.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import it.unisannio.cp.orange.score.R;
import it.unisannio.cp.orange.score.Serie;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        list = (HashMap<String, Serie>) getIntent().getSerializableExtra(MainActivity.SERIE_TV_ARRAY);
        insert = findViewById(R.id.insert);
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra(MainActivity.SERIE_TV_ARRAY, list);
        setResult(RESULT_OK, result);
        finish();
    }


    public void addSerie(View v){
        if(!list.containsKey(insert.getText().toString())){
            list.put(insert.getText().toString(), new Serie(insert.getText().toString(),0));
            insert.setText("");
        }else
            Toast.makeText(this,R.string.java_giàInserita, Toast.LENGTH_LONG).show();
    }

    private HashMap<String, Serie> list;
    private EditText insert;
}
