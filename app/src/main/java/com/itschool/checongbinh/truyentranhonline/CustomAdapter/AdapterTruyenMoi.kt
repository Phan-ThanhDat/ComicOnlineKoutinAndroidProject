package com.itschool.checongbinh.truyentranhonline.CustomAdapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.itschool.checongbinh.truyentranhonline.ChiTietTruyenActivity
import com.itschool.checongbinh.truyentranhonline.CreateView.ItemTruyenMoi
import com.itschool.checongbinh.truyentranhonline.Model.TruyenModel
import com.itschool.checongbinh.truyentranhonline.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Binh on 6/1/17.
 */

class AdapterTruyenMoi(val context:Context,val list:MutableList<TruyenModel>):RecyclerView.Adapter<AdapterTruyenMoi.ViewHolderTruyenMoi>(){

    override fun onBindViewHolder(holder: AdapterTruyenMoi.ViewHolderTruyenMoi?, position: Int) {
        holder?.bindData(context,list.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AdapterTruyenMoi.ViewHolderTruyenMoi {
        val view = ItemTruyenMoi().createView(AnkoContext.Companion.create(context,parent,false))
        return ViewHolderTruyenMoi(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class ViewHolderTruyenMoi(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindData(context:Context,truyenmodal:TruyenModel){
            val imgHinhTruyen = itemView.find<ImageView>(R.id.imgHinhTruyen)
            val txtTenTruyen = itemView.find<TextView>(R.id.txtTenTruyen)
            val txtChapter = itemView.find<TextView>(R.id.chapmoi)
            val txtXem = itemView.find<TextView>(R.id.xem)

            txtTenTruyen.text = truyenmodal.tentruyen
            txtChapter.text = truyenmodal.chapmoi.tenchap
            Picasso.with(context).load(truyenmodal.linkhinh).into(imgHinhTruyen)

            txtXem.setOnClickListener { v->
                context.startActivity<ChiTietTruyenActivity>(truyenmodal.linktruyen)
            }
        }


    }
}

