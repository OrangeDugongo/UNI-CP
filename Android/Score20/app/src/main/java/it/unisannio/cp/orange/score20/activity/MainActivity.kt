package it.unisannio.cp.orange.score20.activity

import android.app.Activity
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import android.widget.LinearLayout
import it.unisannio.cp.orange.score20.R
import it.unisannio.cp.orange.score20.Serie
import it.unisannio.cp.orange.score20.fragment.SerieDetail
import it.unisannio.cp.orange.score20.fragment.SerieMaster

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 16/11/17
 *
 */

class MainActivity : AppCompatActivity(), SerieMaster.OnClickListener, SerieDetail.OnRatingChange {

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

    fun FragmentManager.transaction(func: FragmentTransaction.()->FragmentTransaction)=fragmentManager.beginTransaction().func().commit()
}
