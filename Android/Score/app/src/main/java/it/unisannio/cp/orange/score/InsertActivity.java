package it.unisannio.cp.orange.score;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        HashMap<String, Float> list = (HashMap<String, Float>) getIntent().getSerializableExtra(MainActivity.SERIE_TV_ARRAY);

        EditText insert = findViewById(R.id.insert);
        Button add = findViewById(R.id.add_button);
        add.setOnClickListener(new AddListener(list, insert));
    }

    private class AddListener implements View.OnClickListener {
        private HashMap<String, Float> list;
        private EditText insert;

        public AddListener(HashMap<String, Float> list, EditText insert) {
            this.list = list;
            this.insert = insert;
        }

        @Override
        public void onClick(View v) {
            list.put(insert.getText().toString(), new Float(0));

            Intent result = new Intent();
            result.putExtra(MainActivity.SERIE_TV_ARRAY, list);

            setResult(Activity.RESULT_OK, result);
            finish();
        }
    }
}
