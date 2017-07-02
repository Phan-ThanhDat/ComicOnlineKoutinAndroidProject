package com.itschool.checongbinh.truyentranhonline.CreateView

import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout

/**
 * Created by Binh on 6/1/17.
 */
class ItemChiTietTruyen:AnkoComponent<ViewGroup?>{
    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui){
            linearLayout {

            }
        }
    }

}