package com.itschool.checongbinh.truyentranhonline.CreateView

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by Binh on 6/2/17.
 */
class ItemTheLoai:AnkoComponent<ViewGroup?>{

    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui){

            frameLayout {

                cardView {
                    padding = dip(10)
                    top = dip(10)
                    textView {
                        id = R.id.txtTenTheLoai
                        padding = dip(10)
                        gravity = Gravity.CENTER
                    }
                }.lparams(width = matchParent, height = wrapContent){
                    margin = dip(5)
                }
            }

        }
    }

}