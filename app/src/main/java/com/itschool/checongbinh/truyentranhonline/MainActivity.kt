package com.itschool.checongbinh.truyentranhonline

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import com.itschool.checongbinh.truyentranhonline.CreateView.ViewMainActivity
import com.itschool.checongbinh.truyentranhonline.CustomAdapter.AdapterHienThiTheLoai
import com.itschool.checongbinh.truyentranhonline.CustomAdapter.AdapterTruyenHot
import com.itschool.checongbinh.truyentranhonline.CustomAdapter.AdapterTruyenMoi
import com.itschool.checongbinh.truyentranhonline.ManageFragment.FragmentTheLoai
import com.itschool.checongbinh.truyentranhonline.Model.ChapterModel
import com.itschool.checongbinh.truyentranhonline.Model.TruyenModel
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity(),TextWatcher {

    var khungHotVaMoi:LinearLayout? = null

    var recyclerTruyenHot:RecyclerView? = null
    var recyclerTruyenMoi:RecyclerView? = null
    var recyclerTimKiem:RecyclerView? = null

    var adapterTruyenHot:AdapterTruyenHot? = null
    var adapterTruyenMoi:AdapterTruyenMoi? = null
    var adapterTimKiem:AdapterTruyenHot? = null

    val listTruyenHot = mutableListOf<TruyenModel>()
    val listTruyenMoi = mutableListOf<TruyenModel>()
    var listTimeKiem = mutableListOf<TruyenModel>()
    var listtam = mutableListOf<TruyenModel>()

    var isTheLoai = false
    var fragmentTheoLoai:FragmentTheLoai? = null

    var maxpage = 1
    var oldpage = 1
    var newlinkpage = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewMainActivity().setContentView(this)

        recyclerTruyenHot = find<RecyclerView>(R.id.truyenhot)
        recyclerTruyenMoi = find<RecyclerView>(R.id.truyenmoi)
        recyclerTimKiem = find(R.id.recyclerTimKiem)
        adapterTruyenHot = AdapterTruyenHot(this,listTruyenHot)
        adapterTruyenMoi = AdapterTruyenMoi(this,listTruyenMoi)


        khungHotVaMoi = find(R.id.khungHotVaMoi)
        val imageTheoLoai = find<ImageButton>(R.id.theloai)
        fragmentTheoLoai = FragmentTheLoai()



        val edSearch = find<EditText>(R.id.search)

        edSearch.addTextChangedListener(this)

        imageTheoLoai.setOnClickListener {

            if(!isTheLoai){
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.khungNoiDungTrangChu,fragmentTheoLoai)
                fragmentTransaction.commitNow()
            }else{
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.remove(fragmentTheoLoai)
                fragmentTransaction.commitNow()
            }

            isTheLoai = !isTheLoai

        }

        getDanhSachTruyenHot()
        getDanhSachTruyenMoi()
        getDanhSachTruyen("http://truyentranh.net/danh-sach.tall.html")

    }

    override fun onBackPressed() {
        if(isTheLoai){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.remove(fragmentTheoLoai)
            fragmentTransaction.commitNow()
        }
//        super.onBackPressed()

    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(s?.trim()?.length!! > 0){
            khungHotVaMoi?.visibility = View.INVISIBLE
            recyclerTimKiem?.visibility = View.VISIBLE
            listtam.clear()
            listtam = listTimeKiem.filter { s in it.tentruyen }.toMutableList()

            adapterTimKiem = AdapterTruyenHot(this,listtam)
            recyclerTimKiem?.adapter = adapterTimKiem
            adapterTimKiem?.notifyDataSetChanged()
        }else{
            khungHotVaMoi?.visibility = View.VISIBLE
            recyclerTimKiem?.visibility = View.GONE
        }
    }

    fun getDanhSachTruyen(link:String){

        doAsync {
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
                val tentruyen = value.select("div[class=media-body] h4").text()
                val linktruyen = value.select("a").attr("href")
                val chapter = value.select("div[class=media-body] a").text()
                val linkchap = value.select("div[class=media-body] a").attr("href")

                val chapterModel = ChapterModel(chapter,linkchap)
                val truyenmodel = TruyenModel(tentruyen,0f,0.0,"","","","",linktruyen,linkhinhtruyen,chapterModel)
                listTimeKiem.add(truyenmodel)
            }

            uiThread {
                oldpage++
                if(oldpage == 2){
                    for( value in 2..maxpage){
                        getDanhSachTruyen("$newlinkpage$value")
                    }
                }

            }


        }
    }

    fun getDanhSachTruyenMoi(){
        doAsync {
            val doc = Jsoup.connect("http://truyentranh.net/").get()
            val truyenmoi = doc.select("div[class=media mainpage-manga]")

            for(value in truyenmoi){
                val linkhinhtruyen = value.select("img").attr("src")
                val tentruyen = value.select("h4[class=manga-newest]").text()
                val linktruyen = value.select("a").attr("href")

                val chapter = value.select("div[class=hotup-list] a")
                val linkchap = chapter.attr("href")
                var chapmoi = ""
                for(chap in chapter){
                    chapmoi = chap.text()
                    break
                }

                val chapterModel = ChapterModel(chapmoi,linkchap)


                val truyenmodel = TruyenModel(tentruyen,0f,0.0,"","","","",linktruyen,linkhinhtruyen,chapterModel)

                listTruyenMoi.add(truyenmodel)
            }

            uiThread {
                recyclerTruyenMoi?.adapter = adapterTruyenMoi
                adapterTruyenMoi?.notifyDataSetChanged()
            }
        }
    }

    fun getDanhSachTruyenHot(){
        doAsync {
            val doc = Jsoup.connect("http://truyentranh.net/").get()
            val truyenhot = doc.select("div[class=update-list slider-fixtop] img")

            for(linkhinh in truyenhot){
                val linkhinhtruyen = linkhinh.attr("src")
                val tentruyen = linkhinh.attr("alt")
                val linktruyen = linkhinh.parent().attr("href")
                var chapter = linkhinh.parent().parent().parent().select("div[class=caption] p[class=chapter]").text()
                var linkchap = linkhinh.parent().parent().parent().select("div[class=caption] a[class=Chapter]").attr("href")

                var index = chapter.lastIndexOf("Chap")
                if(index < 0){
                    index = chapter.lastIndexOf("chap")
                }

                chapter = chapter.substring(index)

                val chapterModel = ChapterModel(chapter,linkchap)

                val truyenmodel = TruyenModel(tentruyen,0f,0.0,"","","","",linktruyen,linkhinhtruyen,chapterModel)

                listTruyenHot.add(truyenmodel)
            }

            uiThread {
                recyclerTruyenHot?.adapter = adapterTruyenHot
                adapterTruyenHot?.notifyDataSetChanged()
            }
        }
    }
}


