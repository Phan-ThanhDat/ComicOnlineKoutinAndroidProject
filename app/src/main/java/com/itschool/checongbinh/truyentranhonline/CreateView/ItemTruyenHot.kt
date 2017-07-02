package com.itschool.checongbinh.truyentranhonline.CreateView

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by Binh on 6/1/17.
 */

class ItemTruyenHot:AnkoComponent<ViewGroup?>{
    override fun createView(ui: AnkoContext<ViewGroup?>): View {
       return with(ui){
           frameLayout {
               lparams(width= wrapContent,height = wrapContent){
                   margin = dip(3)
                   gravity = Gravity.CENTER
               }

               cardView {

                   linearLayout {
                       orientation = LinearLayout.VERTICAL
                       imageView {
                           id = R.id.imgHinhTruyen
                           scaleType = ImageView.ScaleType.CENTER_CROP
                           layoutParams = ViewGroup.LayoutParams(dip(150),dip(150))
                       }

                       textView {
                           id = R.id.txtTenTruyen
                           typeface = Typeface.DEFAULT_BOLD
                           textSizeDimen = R.dimen.dimen14sp
                           ellipsize = TextUtils.TruncateAt.END
                           maxLines = 1
                       }.lparams(width= wrapContent, height = wrapContent){
                           topMargin = dip(10)
                           leftMargin = dip(10)
                           bottomMargin = dip(5)
                       }

                       textView {
                           id = R.id.chapmoi
                           textSizeDimen = R.dimen.dimen10sp
                           ellipsize = TextUtils.TruncateAt.END
                           textColor = R.color.colorGray
                       }.lparams(width= wrapContent, height = wrapContent){
                           leftMargin = dip(10)
                           bottomMargin = dip(5)
                       }
                   }

               }.lparams(width= dip(150),height = wrapContent){

               }
           }
       }
    }

}