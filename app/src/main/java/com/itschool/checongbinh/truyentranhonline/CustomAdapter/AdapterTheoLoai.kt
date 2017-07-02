package com.itschool.checongbinh.truyentranhonline.CustomAdapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itschool.checongbinh.truyentranhonline.CreateView.ItemTheLoai
import com.itschool.checongbinh.truyentranhonline.Model.ChapterModel
import com.itschool.checongbinh.truyentranhonline.R
import com.itschool.checongbinh.truyentranhonline.TheLoaiActivity
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Binh on 6/2/17.
 */

class AdapterTheoLoai(val list:MutableList<ChapterModel>,val context:Context) : RecyclerView.Adapter<AdapterTheoLoai.ViewHolderTheoLoai>(){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderTheoLoai {
        val view = ItemTheLoai().createView(AnkoContext.Companion.create(context,parent,false))
        return ViewHolderTheoLoai(view)
    }

    override fun onBindViewHolder(holder: ViewHolderTheoLoai?, position: Int) {
        holder?.bindData(list.get(position),context)
    }

    class ViewHolderTheoLoai(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindData(data:ChapterModel,context: Context){
            val txtTenTheLoai = itemView.find<TextView>(R.id.txtTenTheLoai)
            txtTenTheLoai.text = data.tenchap

            itemView.setOnClickListener {
                val iTheLoai = Intent(context,TheLoaiActivity::class.java)
                iTheLoai.putExtra("linktruyen",data.linkchap)
                context.startActivity(iTheLoai)
            }
        }
    }

}