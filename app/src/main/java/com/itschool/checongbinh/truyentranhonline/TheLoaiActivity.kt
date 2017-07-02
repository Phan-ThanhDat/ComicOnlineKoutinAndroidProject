package com.itschool.checongbinh.truyentranhonline

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.itschool.checongbinh.truyentranhonline.CreateView.ViewTheLoaiActivity
import com.itschool.checongbinh.truyentranhonline.CustomAdapter.AdapterHienThiTheLoai
import com.itschool.checongbinh.truyentranhonline.InterfaceView.LoadMore
import com.itschool.checongbinh.truyentranhonline.Model.ChapterModel
import com.itschool.checongbinh.truyentranhonline.Model.TruyenModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

/**
 * Created by Binh on 6/2/17.
 */
class TheLoaiActivity:AppCompatActivity(),LoadMore{


    val listTruyenHot = mutableListOf<TruyenModel>()
    var recyclerTheLoai:RecyclerView? = null
    var adapter:AdapterHienThiTheLoai? = null
    var linktruyen = ""
    var maxpage = 1
    var oldpage = 1
    var isLoading = true
    var newlinkpage = ""
    var progress:ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewTheLoaiActivity().setContentView(this)

        linktruyen = intent.getStringExtra("linktruyen")

        recyclerTheLoai = find(R.id.recyclerTheLoai)
        adapter = AdapterHienThiTheLoai(listTruyenHot,this)

        progress = find(R.id.progressBar)

        getDanhSachTruyen(linktruyen)

    }

    override fun LoadMore() {
        if(oldpage <= maxpage){
            oldpage++

            getDanhSachTruyen("$newlinkpage$oldpage")
        }
    }

    fun getDanhSachTruyen(link:String){
        progress?.visibility = View.VISIBLE
        doAsync {
            isLoading = true
            val doc = Jsoup.connect(link).get()
            val truyenhot = doc.select("div[class=media mainpage-manga]")
            val pagination = doc.select("div[class=pagination] a")
            val linkpage = pagination.attr("href")
            if(linkpage.trim() != ""){
                newlinkpage = linkpage.substring(0,linkpage.length - 1)
                for (valuePage in pagination){
                    maxpage = valuePage.text().toInt()

                }
            }

            for (value in truyenhot){
                val linkhinhtruyen = value.select("img").attr("src")
                val tentruyen = value.select("img").attr("alt")
                val linktruyen = value.select("a").attr("href")
                val chapter = value.select("div[class=media-body] a").text()
                val linkchap = value.select("div[class=media-body] a").attr("href")

                val chapterModel = ChapterModel(chapter,linkchap)
                val truyenmodel = TruyenModel(tentruyen,0f,0.0,"","","","",linktruyen,linkhinhtruyen,chapterModel)
                listTruyenHot.add(truyenmodel)
            }

            uiThread {
                progress?.visibility = View.GONE
                recyclerTheLoai?.addOnScrollListener(InfiniteScrollListener(this@TheLoaiActivity,recyclerTheLoai?.layoutManager))
                recyclerTheLoai?.adapter = adapter
                adapter?.notifyDataSetChanged()
                isLoading = false
            }


        }
    }
}


class InfiniteScrollListener(var loadmore:LoadMore,val layoutManager: RecyclerView.LayoutManager?): RecyclerView.OnScrollListener(){

    val threshHold = 6

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val gridlayoutmanager = layoutManager as GridLayoutManager
        val itemCount = gridlayoutmanager.itemCount
        val visibilityItem = gridlayoutmanager.findLastCompletelyVisibleItemPosition()

        if(itemCount <= (visibilityItem + threshHold)){
            loadmore.LoadMore()
        }

    }
}


