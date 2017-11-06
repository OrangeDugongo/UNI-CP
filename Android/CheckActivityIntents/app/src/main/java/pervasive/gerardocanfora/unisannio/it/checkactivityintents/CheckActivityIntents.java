package pervasive.gerardocanfora.unisannio.it.checkactivityintents;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CheckActivityIntents extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_intents);

        final EditText intentTextView = (EditText) findViewById(R.id.editText1);
        final TextView resultsTextView = (TextView) findViewById(R.id.textView2);
        final Button checkButton = (Button) findViewById(R.id.CheckIntentButton);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> acts = CheckActivityIntents.getActivitiesForAction(
                        CheckActivityIntents.this, intentTextView.getText().toString());
                String tmp = new String();
                for (String comp : acts) {
                    tmp += comp + "\n";
                }
                resultsTextView.setText(tmp);
            }
        });
    }

    public static List<String> getActivitiesForAction(Context context,
                                                      String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action.trim());
        final List<ResolveInfo> list = packageManager.queryIntentActivities(
                intent, 0);
        final List<String> acts = new ArrayList<String>();
        for (ResolveInfo ri : list) {
            acts.add(ri.activityInfo.name);
        }
        return acts;

    }
}


