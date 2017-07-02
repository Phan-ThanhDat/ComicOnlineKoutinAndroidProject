package com.itschool.checongbinh.truyentranhonline.CreateView

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by Binh on 6/2/17.
 */
class ItemChapter:AnkoComponent<ViewGroup?>{

    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui){
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(10)
                }

                textView {
                    id = R.id.txtTenChap
                    textColor = ContextCompat.getColor(context,R.color.colorAccent)
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = matchParent, height = wrapContent,weight = 1f)

                textView {
                    id = R.id.txtNgayDang
                    gravity = Gravity.RIGHT
                }.lparams(width = matchParent, height = wrapContent,weight = 2f)
            }
        }
    }

}