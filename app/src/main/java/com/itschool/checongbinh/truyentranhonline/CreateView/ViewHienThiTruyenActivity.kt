package com.itschool.checongbinh.truyentranhonline.CreateView

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.itschool.checongbinh.truyentranhonline.HienThiTruyenActivity
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by Binh on 6/2/17.
 */

class ViewHienThiTruyenActivity:AnkoComponent<HienThiTruyenActivity>{
    override fun createView(ui: AnkoContext<HienThiTruyenActivity>): View {
        return with(ui){
            frameLayout {
                backgroundColor = ContextCompat.getColor(context,R.color.colorBlack)

                recyclerView {
                    id = R.id.recyclerHienThiTruyen
                    layoutManager = LinearLayoutManager(context)
                }.lparams(width = matchParent, height = matchParent)

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    backgroundColor = ContextCompat.getColor(context,R.color.colorBottomBar)
                    textView {
                        id = R.id.txtPrev
                        text = " < "
                        textSizeDimen = R.dimen.dimen16sp
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = ContextCompat.getColor(context,R.color.colorWhite)
                        gravity = Gravity.CENTER
                        backgroundColor = ContextCompat.getColor(context,R.color.colorAccentTranparent)
                    }.lparams(width = dip(50), height = matchParent)
                    textView {
                        id = R.id.txtChapHienTai
                        textSizeDimen = R.dimen.dimen16sp
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = ContextCompat.getColor(context,R.color.colorWhite)
                        gravity = Gravity.CENTER
                    }.lparams(width = matchParent, height = matchParent,weight = 1f)

                    textView {
                        id = R.id.txtNext
                        text = " > "
                        textSizeDimen = R.dimen.dimen16sp
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = ContextCompat.getColor(context,R.color.colorWhite)
                        gravity = Gravity.CENTER
                        backgroundColor = ContextCompat.getColor(context,R.color.colorAccentTranparent)
                    }.lparams(width = dip(50), height = matchParent)


                }.lparams(width = matchParent, height = dip(55)){
                    gravity = Gravity.BOTTOM
                }
            }
        }
    }

}