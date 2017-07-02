package com.itschool.checongbinh.truyentranhonline.CreateView

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
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
class ItemTruyenMoi :AnkoComponent<ViewGroup?>{

    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui){
            linearLayout {
                lparams(width= matchParent,height = wrapContent){
                    margin = dip(5)
                }

                cardView {
                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        imageView {
                            id = R.id.imgHinhTruyen
                            scaleType = ImageView.ScaleType.FIT_XY
                        }.lparams(width = matchParent, height = dip(150))

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

                        view {
                           backgroundColor = ContextCompat.getColor(context,R.color.colorGray)
                        }.lparams(width = matchParent,height = dip(2)){
                            topMargin = dip(5)
                        }

                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL

                            textView {
                                id = R.id.xem
                                textResource = R.string.xem
                                setTypeface(null,Typeface.BOLD)
                                textColor = ContextCompat.getColor(context,R.color.colorAccent)
                                gravity = Gravity.CENTER
//                                setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context,android.R.drawable.ic_menu_view),null,null,null)
                            }.lparams(width= wrapContent, height = wrapContent){
                                weight = 1f
                                padding = dip(10)
                            }

                            view {
                                backgroundColor = ContextCompat.getColor(context,R.color.colorGray)
                            }.lparams(width = dip(2),height = matchParent){

                            }

                            textView {
                                id = R.id.thich
                                textResource = R.string.thich
                                gravity = Gravity.CENTER
                                setTypeface(null,Typeface.BOLD)
                                textColor = ContextCompat.getColor(context,R.color.colorAccent)
                                //                                setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context,android.R.drawable.ic_menu_view),null,null,null)
                            }.lparams(width= wrapContent, height = wrapContent){
                                weight = 1f
                                padding = dip(10)
                            }
                        }
                    }
                }.lparams(width= matchParent, height = wrapContent)
            }
        }
    }

}