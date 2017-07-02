package com.itschool.checongbinh.truyentranhonline.ManageFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.itschool.checongbinh.truyentranhonline.CreateView.ViewFragmentTheLoai
import com.itschool.checongbinh.truyentranhonline.CustomAdapter.AdapterTheoLoai
import com.itschool.checongbinh.truyentranhonline.Model.ChapterModel
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

/**
 * Created by Binh on 6/2/17.
 */

class FragmentTheLoai:Fragment(){
    var recyclerTheoLoai:RecyclerView? = null
    var adapter:AdapterTheoLoai? = null
    val listTheLoai = mutableListOf<ChapterModel>()
    var progress:ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = ViewFragmentTheLoai().createView(AnkoContext.Companion.create(context,container,false))

        recyclerTheoLoai = view.find(R.id.recyclerTheLoai)
        progress = view.find<ProgressBar>(R.id.progressBar)

        adapter = AdapterTheoLoai(listTheLoai,context)
        recyclerTheoLoai?.adapter = adapter
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getTheoLoai()
    }

    override fun onStart() {
        super.onStart()
        if(listTheLoai.size > 0){
            progress?.visibility = View.GONE
        }
    }


    fun getTheoLoai(){

        doAsync {
            val doc = Jsoup.connect("http://truyentranh.net/").get()
            val ultheloai = doc.select("div[class=yamm-content] ul")

            for(valueul in ultheloai){
                val theli = valueul.select("li")

                for(valueli in theli){
                    val link = valueli.select("a").attr("href")
                    val ten = valueli.select("a").attr("title")

                    val chapter = ChapterModel(ten,link)
                    listTheLoai.add(chapter)

                }

            }

            uiThread {
                adapter?.notifyDataSetChanged()

                progress?.visibility = View.GONE
            }
        }

    }

}