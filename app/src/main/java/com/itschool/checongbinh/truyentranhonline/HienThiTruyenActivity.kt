package com.itschool.checongbinh.truyentranhonline

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.itschool.checongbinh.truyentranhonline.CreateView.ViewHienThiTruyenActivity
import com.itschool.checongbinh.truyentranhonline.CustomAdapter.AdapterHienThiTruyen
import org.jetbrains.anko.*
import org.jsoup.Jsoup

/**
 * Created by Binh on 6/2/17.
 */
class HienThiTruyenActivity:AppCompatActivity(){

    var linktruyen = ""
    val listHinh = mutableListOf<String>()
    var adapter:AdapterHienThiTruyen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewHienThiTruyenActivity().setContentView(this)

        linktruyen = intent.getStringExtra("linktruyen")
        adapter = AdapterHienThiTruyen(listHinh,this)

        getDanhSachHinhTruyen(linktruyen)
    }

    fun getDanhSachHinhTruyen(link:String){
        listHinh.clear()
        doAsync {

            val doc = Jsoup.connect(link).get()
            val listTruyen = doc.select("div[class=each-page] img")
            val prevChap = doc.select("div[class=chapter-control] a[class=LeftArrow]").attr("href")
            val chapHienTai = doc.select("h3[class=chapter-title]").text()
            val gnextChap = doc.select("div[class=chapter-control] a[class=RightArrow]").attr("href")
            val nextChap = gnextChap ?: "a"

            for(value in listTruyen){
                listHinh.add(value.attr("src"))
            }

            Log.d("kiemtra",nextChap)

            uiThread {
                val recyclerHienThiTruyen = find<RecyclerView>(R.id.recyclerHienThiTruyen)
                val txtNextChap = find<TextView>(R.id.txtNext)
                val txtPrevChap = find<TextView>(R.id.txtPrev)
                val txtChapHienTai = find<TextView>(R.id.txtChapHienTai)

                recyclerHienThiTruyen.adapter = adapter
                adapter?.notifyDataSetChanged()

                txtChapHienTai.text = chapHienTai

                txtNextChap.setOnClickListener {

                    if(nextChap.trim() == ""){
                        toast(R.string.chuacochapmoi)
                    }else{
                        getDanhSachHinhTruyen(nextChap)
                    }

                }

                txtPrevChap.setOnClickListener {
                    if(prevChap.trim() != ""){
                        getDanhSachHinhTruyen(prevChap)
                    }
                }
            }
        }
    }
}