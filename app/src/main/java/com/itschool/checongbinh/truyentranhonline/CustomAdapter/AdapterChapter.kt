package com.itschool.checongbinh.truyentranhonline.CustomAdapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itschool.checongbinh.truyentranhonline.CreateView.ItemChapter
import com.itschool.checongbinh.truyentranhonline.HienThiTruyenActivity
import com.itschool.checongbinh.truyentranhonline.Model.ChapterModel
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Binh on 6/2/17.
 */

class AdapterChapter(val list:MutableList<ChapterModel>,val context:Context):RecyclerView.Adapter<AdapterChapter.ViewHolderChapter>(){

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderChapter?, position: Int) {
        holder?.bindData(list.get(position),context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderChapter {
        val view = ItemChapter().createView(AnkoContext.Companion.create(context,parent,false))
        return ViewHolderChapter(view)
    }

    class ViewHolderChapter(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindData(chapterData : ChapterModel,context:Context){
            val txtTenChap = itemView.find<TextView>(R.id.txtTenChap)
            val txtNgayDangChap = itemView.find<TextView>(R.id.txtNgayDang)

            txtTenChap.text = chapterData.tenchap
            txtNgayDangChap.text = chapterData.ngaydang

            txtTenChap.setOnClickListener { v ->
                val iHienThiTruyen = Intent(context,HienThiTruyenActivity::class.java)
                iHienThiTruyen.putExtra("linktruyen",chapterData.linkchap)
                context.startActivity(iHienThiTruyen)
            }
        }
    }
}
