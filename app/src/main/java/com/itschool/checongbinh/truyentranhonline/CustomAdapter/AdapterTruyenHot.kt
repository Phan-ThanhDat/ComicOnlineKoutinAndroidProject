package com.itschool.checongbinh.truyentranhonline.CustomAdapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
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
import org.jetbrains.anko.layoutInflater

/**
 * Created by Binh on 6/1/17.
 */

class AdapterTruyenHot(val context:Context, val list:MutableList<TruyenModel>):RecyclerView.Adapter<AdapterTruyenHot.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val truyenModel = list.get(position)
        holder?.bindData(context,truyenModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = ItemTruyenHot().createView(AnkoContext.Companion.create(context,parent,false))

        val viewholder = ViewHolder(view)
        return viewholder
    }

    override fun getItemCount(): Int {
        if(list.size > 15){
            return 10
        }else{
            return list.size
        }
//        return list.size
    }

    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        fun bindData(context: Context,data:TruyenModel){
            val imgHinhTruyen = itemView.find<ImageView>(R.id.imgHinhTruyen)
            val txtTenTruyen = itemView.find<TextView>(R.id.txtTenTruyen)
            val txtChapMoi = itemView.find<TextView>(R.id.chapmoi)

            Picasso.with(context).load(data.linkhinh).into(imgHinhTruyen)
            txtTenTruyen.text = data.tentruyen
            txtChapMoi.text = data.chapmoi.tenchap
            itemView.setOnClickListener { v->
                context.startActivity<ChiTietTruyenActivity>(data.linktruyen)
            }


        }
    }

}

inline fun <reified T:ChiTietTruyenActivity>  Context.startActivity(linkchitiet:String){
    val iChiTietTruyen = Intent(this,T::class.java)
    iChiTietTruyen.putExtra("linkchitiet",linkchitiet)
    startActivity(iChiTietTruyen)
}
