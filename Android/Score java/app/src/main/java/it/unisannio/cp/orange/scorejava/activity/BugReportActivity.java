package it.unisannio.cp.orange.scorejava.activity;


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 28/11/17
 *
 */


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import it.unisannio.cp.orange.scorejava.R;

public class BugReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);

        final int[] date = getDate();

        Button pickData = findViewById(R.id.pickData);
        pickData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(BugReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {           //cosa fare con i dati di ritorno
                        EditText edDate = findViewById(R.id.editData);
                        edDate.setText(String.format("%d-%d-%d", year, month, dayOfMonth));
                    }
                }, date[0], date[1], date[2]).show();           //giorno selezionato di default
            }
        });

        Button pickTime = findViewById(R.id.pickTime);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(BugReportActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {                 //cosa fare con i dati di ritorno
                        EditText edTime = findViewById(R.id.editTime);
                        edTime.setText(String.format("%d:%d", hourOfDay, minute));
                    }
                }, date[3], date[4], true).show();      //ora selezionata di default
            }
        });

        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edDate = findViewById(R.id.editData);
                EditText edTime = findViewById(R.id.editTime);
                Intent mail = new Intent(Intent.ACTION_SENDTO);
                mail.setData(Uri.parse("mailto:"));                 //viene creato un intent per inviare una mail
                mail.putExtra(Intent.EXTRA_EMAIL, new String[]{"spamcarneinscatola@gmail.com"});        //indirizzo a cui inviare la mail
                mail.putExtra(Intent.EXTRA_SUBJECT, "bug on "+edDate.getText()+" "+edTime.getText());
                EditText editText = findViewById(R.id.editText);
                mail.putExtra(Intent.EXTRA_TEXT, editText.getText());
                if(mail.resolveActivity(getPackageManager()) != null)
                    startActivity(mail);
            }
        });
    }

    public int[] getDate(){             //shortcut per determinare il giorno e l'ora
        int[] date = new int[5];
        Calendar c = Calendar.getInstance();
        date[0] = c.get(Calendar.YEAR);
        date[1] = c.get(Calendar.MONTH);
        date[2] = c.get(Calendar.DAY_OF_MONTH);
        date[3] = c.get(Calendar.HOUR_OF_DAY);
        date[4] = c.get(Calendar.MINUTE);
        return date;
    }
}
