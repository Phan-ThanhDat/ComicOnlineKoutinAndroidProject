package com.itschool.checongbinh.truyentranhonline.CreateView

import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.itschool.checongbinh.truyentranhonline.R
import com.itschool.checongbinh.truyentranhonline.TheLoaiActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by Binh on 6/2/17.
 */

class ViewTheLoaiActivity:AnkoComponent<TheLoaiActivity>{
    override fun createView(ui: AnkoContext<TheLoaiActivity>): View {
        return with(ui){

            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(width = matchParent , height = matchParent){
                    gravity = Gravity.CENTER
                }

                recyclerView {
                    id = R.id.recyclerTheLoai
                    layoutManager = GridLayoutManager(context,2)
                }.lparams(width = matchParent,height = matchParent,weight = 1f){
                    gravity = Gravity.CENTER
                }

                progressBar {
                    id = R.id.progressBar
                    isIndeterminate = true


                }.lparams(width = wrapContent, height = wrapContent, weight = 1f){
                    gravity = Gravity.CENTER
                }
            }

        }
    }

}