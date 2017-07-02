package com.itschool.checongbinh.truyentranhonline.CreateView

import android.content.res.Resources
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.itschool.checongbinh.truyentranhonline.MainActivity
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Binh on 6/1/17.
 */

class ViewMainActivity:AnkoComponent<MainActivity>{
    override fun createView(ui: AnkoContext<MainActivity>): View {
        return with(ui){
            linearLayout {
                lparams(width= matchParent, height = matchParent)
                isFocusable = true
                isFocusableInTouchMode = true
                backgroundColor = ContextCompat.getColor(context,R.color.colorGray)
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    editText {
                        hintResource = R.string.timkiem
                        backgroundDrawable = ContextCompat.getDrawable(context,R.drawable.box_shadow)

                        textColor = R.color.colorGray

                        id = R.id.search
                        setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context,android.R.drawable.ic_menu_search),null,null,null)
                    }.lparams(width= matchParent,height = wrapContent,weight = 1f){

                    }

                    imageButton {
                        id = R.id.theloai
                        imageResource = R.drawable.ic_reorder_black_24dp
                        backgroundDrawable = ContextCompat.getDrawable(context,R.drawable.box_shadow)
                        padding = dip(10)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            elevation = 2f
                        }
                    }.lparams(width = wrapContent, height = matchParent){

                    }
                }


                nestedScrollView {
                    lparams(width= matchParent,height = matchParent)

                    frameLayout {
                        id = R.id.khungNoiDungTrangChu
                        lparams(width= matchParent,height = matchParent)

                        recyclerView {
                            id = R.id.recyclerTimKiem
                            layoutManager = GridLayoutManager(context,2)
                        }.lparams(width = matchParent, height = matchParent)

                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            id = R.id.khungHotVaMoi
                            textView {
                                textResource = R.string.truyenhot
                                textSizeDimen = R.dimen.dimen20sp
                                top = R.dimen.dimen10dp
                                textSizeDimen = R.dimen.dimen20sp

                                setTypeface(null,Typeface.BOLD)
                            }.lparams(width= matchParent,height = matchParent){
                                margin = dip(10)
                            }

                            recyclerView{
                                lparams(width= matchParent, height = matchParent)
                                id = R.id.truyenhot
                                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                            }

                            textView {
                                textResource = R.string.truyenmoi
                                textSizeDimen = R.dimen.dimen20sp
                                top = R.dimen.dimen10dp
                                textSizeDimen = R.dimen.dimen20sp
                                setTypeface(null,Typeface.BOLD)
                            }.lparams(width= matchParent,height = wrapContent){
                                margin = dip(10)
                            }

                            recyclerView{
                                lparams(width= matchParent, height = matchParent)
                                id = R.id.truyenmoi
                                layoutManager = GridLayoutManager(context,2)
                            }

                        }.lparams(width= matchParent,height = wrapContent)
                    }



                }



            }
        }
    }



}