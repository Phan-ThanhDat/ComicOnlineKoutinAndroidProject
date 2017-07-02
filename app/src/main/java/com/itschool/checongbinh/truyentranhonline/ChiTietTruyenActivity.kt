package com.itschool.checongbinh.truyentranhonline

import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.itschool.checongbinh.truyentranhonline.CreateView.ViewChiTietTruyenActivity
import com.itschool.checongbinh.truyentranhonline.CustomAdapter.AdapterChapter
import com.itschool.checongbinh.truyentranhonline.Model.ChapterModel
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jsoup.Jsoup

/**
 * Created by Binh on 6/1/17.
 */
class ChiTietTruyenActivity:AppCompatActivity(){
    var linktruyen:String? = null
    var isXemThem = false
    val listChuong = mutableListOf<ChapterModel>()

    var adapter:AdapterChapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewChiTietTruyenActivity().setContentView(this)

        linktruyen = intent.getStringExtra("linkchitiet");



        adapter = AdapterChapter(listChuong,this)

        getChiTietTruyen(linktruyen)
    }

    fun getChiTietTruyen(link:String?){

        doAsync {
            val doc = Jsoup.connect(link).get()
            val linkhinh = doc.select("div[class=media-left cover-detail] img").attr("src")
            val tentruyen = doc.select("h1[class=title-manga]").text()
            val tenkhac = doc.select("p[class=description-update]").html()
            val tongdanhgia = doc.select("div[class=total-rating] span[class=VoteScore]").text()
            val tongVote = doc.select("div[class=total-rating] span[rel=total-vote]").text()
            val noidung = doc.select("div[class=manga-content]").text()
            val listChapMoi = doc.select("div[class=chapter-list] a")
            val listChap = doc.select("div[class=total-chapter] section a")

            var chapmoi = "0"
            for(value in listChapMoi){
                chapmoi = value.attr("title")
                break
            }

            var index = chapmoi.lastIndexOf("Chap")
            if(index < 0){
                index = chapmoi.lastIndexOf("chap")
            }

            chapmoi = chapmoi.substring(index)


            for (chap in listChap){
                val tenchap = chap.attr("title")
                val linkchap = chap.attr("href")
                val ngaydangchap = chap.select("span").text()

                val chapModel = ChapterModel(tenchap,linkchap)
                chapModel.ngaydang = ngaydangchap

                listChuong.add(chapModel)
            }

            uiThread {
                val imgHinhTruyen = find<ImageView>(R.id.imgHinhTruyen)
                val txtTenTruyen = find<TextView>(R.id.txtTenTruyen)
                val txtTenKhac = find<TextView>(R.id.txtTenKhac)
                val txtTongDanhGia = find<TextView>(R.id.txtTongDanhGia)
                val txtTongVote = find<TextView>(R.id.txtTongVote)
                val txtChapMoi = find<TextView>(R.id.txtChapMoi)
                val txtNoiDungTruyen = find<TextView>(R.id.txtNoiDungTruyen)
                val xemThemNoiDung = find<ImageView>(R.id.imgXemThemNoiDung)
                val recyclerDanhSachChuong = find<RecyclerView>(R.id.recyclerDanhSachChuong)

                txtTenTruyen.text = tentruyen
                txtTenKhac.text = Html.fromHtml(tenkhac)
                txtTongDanhGia.text = "$tongdanhgia/10"
                txtTongVote.text = tongVote
                txtChapMoi.text = chapmoi
                txtNoiDungTruyen.text = noidung

                Picasso.with(applicationContext).load(linkhinh).into(imgHinhTruyen)
                recyclerDanhSachChuong.adapter = adapter
                adapter?.notifyDataSetChanged()

                xemThemNoiDung.setOnClickListener { v->
                    if(!isXemThem){
                        txtNoiDungTruyen.maxLines = Int.MAX_VALUE
                        xemThemNoiDung.imageResource = R.drawable.ic_keyboard_arrow_up_black_24dp
                    }else{
                        txtNoiDungTruyen.maxLines = 4
                        xemThemNoiDung.imageResource = R.drawable.ic_keyboard_arrow_down_black_24dp
                    }

                    isXemThem = !isXemThem

                }


            }

        }

    }
}