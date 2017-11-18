package it.unisannio.cp.orange.score20.activity

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import it.unisannio.cp.orange.score20.R
import it.unisannio.cp.orange.score20.Serie
import it.unisannio.cp.orange.score20.fragment.SerieDetail
import it.unisannio.cp.orange.score20.fragment.SerieMaster

class MainActivity : AppCompatActivity(), SerieMaster.OnClickListener, SerieDetail.OnRatingChange {

    private val detail = SerieDetail()
    private val master = SerieMaster()

    companion object {
        const val ERROR = "ERROR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager.add(R.id.frame, master)
        fragmentManager.add(R.id.frame2, detail)
    }

    override fun onClickListener(item: Serie) {
        detail.showDetail(item)
    }

    override fun onRatingChange(item: Serie) {
        master.refresh(item)
    }

    fun FragmentManager.add(id:Int, frame:Fragment){
        this.beginTransaction().add(id, frame).commit()
    }
}
