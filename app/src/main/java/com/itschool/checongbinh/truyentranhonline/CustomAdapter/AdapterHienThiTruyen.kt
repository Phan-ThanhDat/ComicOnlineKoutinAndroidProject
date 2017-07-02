package com.itschool.checongbinh.truyentranhonline.CustomAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.itschool.checongbinh.truyentranhonline.CreateView.ItemHienThiTruyen
import com.itschool.checongbinh.truyentranhonline.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Binh on 6/2/17.
 */
class AdapterHienThiTruyen(val list:MutableList<String>,val context:Context):RecyclerView.Adapter<AdapterHienThiTruyen.ViewHolderHienThiTruyen>(){

    override fun onBindViewHolder(holder: ViewHolderHienThiTruyen?, position: Int) {
        holder?.bindData(list.get(position),context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderHienThiTruyen {
        val view = ItemHienThiTruyen().createView(AnkoContext.Companion.create(context,parent,false))
        return ViewHolderHienThiTruyen(view)
    }

    class ViewHolderHienThiTruyen(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindData(linkhinh:String,context: Context){
            val imageHinh = itemView.find<ImageView>(R.id.imgHinhTruyen)
            Picasso.with(context).load(linkhinh).into(imageHinh)
        }
    }

}