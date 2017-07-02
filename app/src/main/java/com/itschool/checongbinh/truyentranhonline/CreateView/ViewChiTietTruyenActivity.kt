package com.itschool.checongbinh.truyentranhonline.CreateView

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.itschool.checongbinh.truyentranhonline.ChiTietTruyenActivity
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Binh on 6/1/17.
 */

class ViewChiTietTruyenActivity:AnkoComponent<ChiTietTruyenActivity>{
    override fun createView(ui: AnkoContext<ChiTietTruyenActivity>): View {
        return with(ui){

            nestedScrollView {
                linearLayout {
                    lparams(width = matchParent, height = matchParent)
                    orientation = LinearLayout.VERTICAL
                    backgroundColor = ContextCompat.getColor(context,R.color.colorGray)


                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        backgroundColor = ContextCompat.getColor(context,R.color.colorWhite)
                        imageView {
                            id = R.id.imgHinhTruyen
                            scaleType = ImageView.ScaleType.FIT_XY
                        }.lparams(width = dip(250), height = dip(200)){
                            weight = 1f
                        }

                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            lparams(width = matchParent, height = wrapContent){
                                weight = 1f
                                leftPadding = dip(10)
                            }

                            textView {
                                id = R.id.txtTenTruyen
                                textSizeDimen = R.dimen.dimen20sp
                                setTypeface(null, Typeface.BOLD)
                            }

                            textView {
                                id = R.id.txtTenKhac
                                textColor = R.color.colorGray
                                textSizeDimen = R.dimen.dimen16sp
                            }
                        }
                    }

                    //viewdanhgia

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER
                        backgroundColor = ContextCompat.getColor(context,R.color.colorAccent)

                        textView {
                            id = R.id.txtTongDanhGia
                            setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(context,R.drawable.ic_stars_white_24dp),null,null)
                            text = "0"
                            textColor = ContextCompat.getColor(context,R.color.colorWhite)
                            textSizeDimen = R.dimen.dimen16sp
                            typeface = Typeface.DEFAULT_BOLD
                            gravity = Gravity.CENTER

                        }.lparams(width = matchParent, height = wrapContent){
                            padding = dip(5)
                            weight = 1f
                        }

                        view {
                            backgroundColor = ContextCompat.getColor(context,R.color.colorWhite)
                        }.lparams(width = dip(1),height = matchParent){

                        }

                        textView {
                            id = R.id.txtTongVote
                            setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(context,R.drawable.ic_speaker_notes_white_24dp),null,null)
                            text = "0"
                            textColor = ContextCompat.getColor(context,R.color.colorWhite)
                            textSizeDimen = R.dimen.dimen16sp
                            typeface = Typeface.DEFAULT_BOLD
                            gravity = Gravity.CENTER
                        }.lparams(width = matchParent, height = wrapContent){
                            padding = dip(5)
                            weight = 1f

                        }

                        view {
                            backgroundColor = ContextCompat.getColor(context,R.color.colorWhite)
                        }.lparams(width = dip(1),height = matchParent){

                        }

                        textView {
                            id = R.id.txtChapMoi
                            setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(context,R.drawable.ic_fiber_new_white_24dp),null,null)
                            textColor = ContextCompat.getColor(context,R.color.colorWhite)
                            textSizeDimen = R.dimen.dimen16sp
                            typeface = Typeface.DEFAULT_BOLD
                            gravity = Gravity.CENTER
                        }.lparams(width = matchParent, height = wrapContent){
                            padding = dip(5)
                            weight = 1f
                        }
                    }
                    //end view danh gia

                    //view noidung
                    cardView {
                        cardElevation = 2f

                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            lparams(width = matchParent, height = wrapContent){
                                padding = dip(5)
                            }

                            textView {
                                textResource = R.string.noidung
                                textSizeDimen = R.dimen.dimen20sp

                            }

                            textView {
                                id = R.id.txtNoiDungTruyen
                                maxLines = 4
                                ellipsize = TextUtils.TruncateAt.END
                            }

                            imageView {
                                id = R.id.imgXemThemNoiDung
                                imageResource = R.drawable.ic_keyboard_arrow_down_black_24dp
                            }
                        }
                    }

                    //end view noi dung

                    // view danh sach chuong
                    cardView {
                        cardElevation = 7f

                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            lparams(width = matchParent, height = wrapContent){
                                padding = dip(5)
                            }

                            textView {
                                textResource = R.string.danhsachchap
                                textSizeDimen = R.dimen.dimen20sp

                            }

                            recyclerView {
                                id = R.id.recyclerDanhSachChuong
                                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)
                            }

                        }

                    }.lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(5)
                    }
                    //end view danh sach chuong
                }
            }


        }
    }

}