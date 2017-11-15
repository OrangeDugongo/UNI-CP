package it.unisannio.cp.orange.score;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        list = (HashMap<String, Float>) getIntent().getSerializableExtra(MainActivity.SERIE_TV_ARRAY);
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
            list.put(insert.getText().toString(), new Float(0));
            insert.setText("");
        }else
            Toast.makeText(this,R.string.java_giàInserita, Toast.LENGTH_LONG).show();
    }

    private HashMap<String, Float> list;
    private EditText insert;
}
