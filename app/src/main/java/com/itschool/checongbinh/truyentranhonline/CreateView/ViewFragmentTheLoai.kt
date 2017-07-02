package com.itschool.checongbinh.truyentranhonline.CreateView

import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.itschool.checongbinh.truyentranhonline.ManageFragment.FragmentTheLoai
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by Binh on 6/2/17.
 */
class ViewFragmentTheLoai:AnkoComponent<ViewGroup?>{

    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui){
            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(width = matchParent, height = matchParent)
                backgroundColor = ContextCompat.getColor(context,R.color.colorGray)

                progressBar {
                    id = R.id.progressBar
                }

                recyclerView {
                    id = R.id.recyclerTheLoai
                    layoutManager = GridLayoutManager(context,2)

                }.lparams(width = matchParent, height = wrapContent)


            }
        }
    }

}