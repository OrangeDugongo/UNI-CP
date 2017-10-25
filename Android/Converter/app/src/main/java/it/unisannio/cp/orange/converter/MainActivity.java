package it.unisannio.cp.orange.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "activitylife";
    private final double BTCtoEUR = 4708.3432;
    private final double EURtoBTC = 1/BTCtoEUR;
    private double RATE = BTCtoEUR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        Button swap = (Button) findViewById(R.id.swap);
        Button convert = (Button) findViewById(R.id.convert);
        Button clear = (Button) findViewById(R.id.clear);
        EditText in = (EditText) findViewById(R.id.in);
        EditText out = (EditText) findViewById(R.id.out);
        TextView text1 = (TextView) findViewById(R.id.inText);
        TextView text2 = (TextView) findViewById(R.id.outText);

        swap.setOnClickListener(new SwapListener(text1, text2));
        convert.setOnClickListener(new ConvertListener(in, out));
        clear.setOnClickListener(new ClearListener(in, out));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

    private class SwapListener implements View.OnClickListener {

        public SwapListener(TextView t1, TextView t2){
            this.t1 = t1;
            this.t2 = t2;
        }

        @Override
        public void onClick(View v) {
            CharSequence str = t1.getText();
            t1.setText(t2.getText());
            t2.setText(str);

            if(str.toString().equals("EUR"))
                RATE = BTCtoEUR;
            else
                RATE = EURtoBTC;

        }

        private TextView t1;
        private TextView t2;
    }

    private class ConvertListener implements View.OnClickListener {

        public ConvertListener(EditText in, EditText out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public void onClick(View v) {
            if(!in.getText().toString().equals("")){
                double amount = Double.parseDouble(in.getText().toString());
                Double result = new Double(RATE*amount);
                out.setText(result.toString());
            }
        }

        private EditText in;
        private EditText out;
    }

    private class ClearListener implements View.OnClickListener {

        public ClearListener(EditText in, EditText out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public void onClick(View v) {
            in.setText("");
            out.setText("");
        }

        private EditText in;
        private EditText out;
    }
}
