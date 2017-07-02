package com.itschool.checongbinh.truyentranhonline.CustomAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.itschool.checongbinh.truyentranhonline.ChiTietTruyenActivity
import com.itschool.checongbinh.truyentranhonline.CreateView.ItemTruyenHot
import com.itschool.checongbinh.truyentranhonline.Model.TruyenModel
import com.itschool.checongbinh.truyentranhonline.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Binh on 6/2/17.
 */

class AdapterHienThiTheLoai(val list:MutableList<TruyenModel>, val context: Context) : RecyclerView.Adapter<AdapterHienThiTheLoai.ViewHolderXemTheoLoai>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderXemTheoLoai {
        val view = ItemTruyenHot().createView(AnkoContext.Companion.create(context, parent, false))
        return ViewHolderXemTheoLoai(view)
    }

    override fun onBindViewHolder(holder: ViewHolderXemTheoLoai?, position: Int) {
        holder?.bindData(list.get(position), context)
    }

    class ViewHolderXemTheoLoai(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: TruyenModel, context: Context) {
            val imgHinhTruyen = itemView.find<ImageView>(R.id.imgHinhTruyen)
            val txtTenTruyen = itemView.find<TextView>(R.id.txtTenTruyen)
            val txtChapMoi = itemView.find<TextView>(R.id.chapmoi)

            Picasso.with(context).load(data.linkhinh).into(imgHinhTruyen)
            txtTenTruyen.text = data.tentruyen
            txtChapMoi.text = data.chapmoi.tenchap

            itemView.setOnClickListener {
                context.startActivity<ChiTietTruyenActivity>(data.linktruyen)
            }
        }
    }
}
