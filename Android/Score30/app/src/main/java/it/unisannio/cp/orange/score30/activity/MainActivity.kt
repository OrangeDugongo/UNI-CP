package it.unisannio.cp.orange.score30.activity

import android.app.Activity
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.LinearLayout
import it.unisannio.cp.orange.score20.fragment.SerieDetail
import it.unisannio.cp.orange.score20.fragment.SerieMaster
import it.unisannio.cp.orange.score30.R
import it.unisannio.cp.orange.score30.Serie

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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode){
            CODE_DETAIL -> {
                if(resultCode == Activity.RESULT_OK)
                    master.refresh(data?.getSerializableExtra(EXTRA_ITEM) as Serie)
                else
                    Log.v(ERROR, "no result")
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
                val infoIntent = Intent(this, Info::class.java)
                startActivity(infoIntent)
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
        return when (item.itemId){
            R.id.list_con_delete -> master.delete(master.listAdapter.getItem(pos) as Serie)
            R.id.list_con_azzera -> master.toZero(master.listAdapter.getItem(pos) as Serie)
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

    fun FragmentManager.transaction(func: FragmentTransaction.()-> FragmentTransaction)=fragmentManager.beginTransaction().func().commit()
}
