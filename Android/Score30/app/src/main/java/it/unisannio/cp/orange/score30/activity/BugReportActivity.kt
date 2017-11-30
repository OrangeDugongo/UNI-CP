package it.unisannio.cp.orange.score30.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import it.unisannio.cp.orange.score30.R
import kotlinx.android.synthetic.main.activity_bug_report.*
import java.util.*

class BugReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug_report)

        val date = getDate()

        pickData.setOnClickListener({ _ ->
            DatePickerDialog(this, { _, y: Int, m: Int, d: Int -> editData.setText("$y-$m-$d")},
                    date[0], date[1], date[2]).show()
        })

        pickTime.setOnClickListener({ _ ->
            TimePickerDialog(this, { _, h: Int, m: Int -> editTime.setText("$h:$m") },
                    date[3], date[4], true).show()
        })

        send.setOnClickListener({ _ ->
            val mail = Intent(Intent.ACTION_SENDTO)
            mail.setData(Uri.parse("mailto:"))
            mail.putExtra(Intent.EXTRA_EMAIL, Array(1){"spamcarneinscatola@gmail.com"})
            mail.putExtra(Intent.EXTRA_SUBJECT, "bug on ${editData.text} ${editTime.text}")
            mail.putExtra(Intent.EXTRA_TEXT, editText.text)
            if(mail.resolveActivity(packageManager) != null)
                startActivity(mail)
        })
    }

    fun getDate(): IntArray{
        val date = IntArray(5)
        val c = Calendar.getInstance()
        date[0] = c.get(Calendar.YEAR)
        date[1] = c.get(Calendar.MONTH)
        date[2] = c.get(Calendar.DAY_OF_MONTH)
        date[3] = c.get(Calendar.HOUR_OF_DAY)
        date[4] = c.get(Calendar.MINUTE)
        return date
    }
}
