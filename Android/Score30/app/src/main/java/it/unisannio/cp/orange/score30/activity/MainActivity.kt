package it.unisannio.cp.orange.score30.activity

import android.app.*
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.*
import it.unisannio.cp.orange.score30.R
import it.unisannio.cp.orange.score30.Serie
import it.unisannio.cp.orange.score30.fragments.SerieDetail
import it.unisannio.cp.orange.score30.fragments.SerieMaster

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SerieMaster.OnClickListener, SerieDetail.OnRatingChange  {

    private val detail = SerieDetail()
    private val master = SerieMaster()
    private var detailFrame : FrameLayout? = null
    private var is2Pane = false

    companion object {
        const val ERROR = "ERROR"
        const val EXTRA_ITEM = "it.unisannio.cp.orange.score20.activity.ITEM"
        const val CODE_DETAIL = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if(savedInstanceState == null)
            fragmentManager.transaction { add(R.id.master, master) }
        else
            fragmentManager.transaction { replace(R.id.master, master) }
        is2Pane = determineLayout()
        if(is2Pane)
            fragmentManager.addOnBackStackChangedListener {
                detailFrame?.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0f)
            }
        fab.setOnClickListener({ _ ->
            addDialog().show()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode){
            CODE_DETAIL -> {
                if(resultCode == Activity.RESULT_OK)
                    master.refresh(data?.getSerializableExtra(EXTRA_ITEM) as Serie)
                else
                    Log.e(ERROR, "no result")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_info ->{
                val infoIntent = Intent(this, SettingsActivity::class.java)
                startActivity(infoIntent)
                true
            }
            R.id.menu_bug_report -> {
                val bugReportIntent = Intent(this, BugReportActivity::class.java)
                startActivity(bugReportIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.list_con_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val pos = (item?.menuInfo as AdapterView.AdapterContextMenuInfo).position
        val serieItem = master.listAdapter.getItem(pos) as Serie
        return when (item.itemId){
            R.id.list_con_delete -> {
                master.delete(serieItem)
                val snack = Snackbar.make(findViewById(R.id.main_layout), R.string.on_delete, Snackbar.LENGTH_SHORT)
                snack.setAction(R.string.undo, { _ -> master.refresh(serieItem) }).show()
                return true
            }
            R.id.list_con_azzera -> master.toZero(serieItem)
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onClickListener(item: Serie) {
        if(is2Pane) {  //Landscape mode
            if (!detail.isAdded) {
                fragmentManager.transaction { replace(R.id.detail, detail)
                    addToBackStack(null)
                }
                fragmentManager.executePendingTransactions()
                detailFrame = findViewById(R.id.detail)
                detailFrame?.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
            }
            detail.showDetail(item)
        }else{
            val detailIntent = Intent(this, DetailActivity::class.java)
            detailIntent.putExtra(EXTRA_ITEM, item)
            startActivityForResult(detailIntent, CODE_DETAIL)
        }
    }

    override fun onRatingChange(item: Serie) {
        master.refresh(item)
    }

    fun determineLayout() = findViewById<FrameLayout>(R.id.detail) != null

    fun addDialog(): AlertDialog{
        val view = layoutInflater.inflate(R.layout.add_dialog, null)
        val etAdd = view.findViewById<EditText>(R.id.add_editText)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.add_dialog_title)
        builder.setView(view)
        builder.setPositiveButton(R.string.add, { _, _ ->
            if(!master.add(etAdd.text.toString()))
                Snackbar.make(findViewById(R.id.main_layout), R.string.duplicate, Snackbar.LENGTH_SHORT).show()
        })
        builder.setNegativeButton(R.string.undo, null)
        return builder.create()
    }

    fun FragmentManager.transaction(func: FragmentTransaction.()-> FragmentTransaction)=fragmentManager.beginTransaction().func().commit()
}

