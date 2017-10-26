package it.unisannio.cp.orange.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class StartActivity extends AppCompatActivity {

    public static final String USER= "utente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        EditText user = (EditText) findViewById(R.id.user);
        EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new LogInListener(user, password));
    }

    private class LogInListener implements View.OnClickListener {

        public LogInListener(EditText user, EditText password) {
            this.user = user;
            this.password = password;
        }

        @Override
        public void onClick(View v) {
            if(!user.getText().toString().equals(password.getText().toString())){
                Intent hello = new Intent(StartActivity.this, Hello.class);
                hello.putExtra(USER, user.getText().toString());
                startActivity(hello);
            }
        }

        private EditText user;
        private EditText password;
    }
}
