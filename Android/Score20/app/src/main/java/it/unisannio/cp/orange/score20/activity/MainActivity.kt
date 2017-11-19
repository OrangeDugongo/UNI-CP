package it.unisannio.cp.orange.score20.activity

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
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

class MainActivity : AppCompatActivity(), SerieMaster.OnClickListener, SerieDetail.OnRatingChange {

    private val detail = SerieDetail()
    private val master = SerieMaster()
    private var masterFrame : FrameLayout? = null
    private var detailFrame : FrameLayout? = null

    companion object {
        const val ERROR = "ERROR"
        const val EXTRA_ITEM = "it.unisannio.cp.orange.score20.activity.ITEM"
        const val CODE_DETAIL = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager.add(R.id.frame, master)
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
        if(findViewById<FrameLayout>(R.id.frame2)!=null) {  //Landscape mode
            Log.d("landscape", "null")
            if (!detail.isAdded) {
                fragmentManager.add(R.id.frame2, detail)
                fragmentManager.executePendingTransactions()
                detailFrame = findViewById(R.id.frame2)
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

    fun FragmentManager.add(id:Int, frame:Fragment){
        this.beginTransaction().add(id, frame).commit()
    }
}
