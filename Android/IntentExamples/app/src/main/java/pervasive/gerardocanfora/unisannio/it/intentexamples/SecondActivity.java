package pervasive.gerardocanfora.unisannio.it.intentexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activities);
        Intent fromCaller=getIntent();
        int param=fromCaller.getIntExtra(getResources().getString(R.string.key), -1);
        if (param>-1){
            TextView tv=(TextView) findViewById(R.id.textView);
            tv.setText(String.valueOf(param));
        }
    }

}
