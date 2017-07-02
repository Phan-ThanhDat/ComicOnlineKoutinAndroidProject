package com.itschool.checongbinh.truyentranhonline.CustomAdapter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.itschool.checongbinh.truyentranhonline.CreateView.ItemChiTietTruyen
import com.itschool.checongbinh.truyentranhonline.Model.TruyenModel
import org.jetbrains.anko.AnkoContext

/**
 * Created by Binh on 6/1/17.
 */

class AdapterChiTietTruyen(val list:MutableList<TruyenModel>,var context:Context):RecyclerView.Adapter<AdapterChiTietTruyen.ViewHolderChiTietTruyen>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderChiTietTruyen {
        val view = ItemChiTietTruyen().createView(AnkoContext.Companion.create(context,parent,false))

        return ViewHolderChiTietTruyen(view)
    }

    override fun onBindViewHolder(holder: ViewHolderChiTietTruyen?, position: Int) {

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolderChiTietTruyen(itemView: View):RecyclerView.ViewHolder(itemView){

    }
}
