package it.unisannio.cp.orange.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by orange on 26/10/17.
 */

public class Hello extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Intent hello = getIntent();

        TextView text = (TextView) findViewById(R.id.textView);
        text.setText("Hello World, "+hello.getStringExtra(StartActivity.USER)+"!");
    }
}
