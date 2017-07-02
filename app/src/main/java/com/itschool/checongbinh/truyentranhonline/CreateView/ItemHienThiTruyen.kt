package com.itschool.checongbinh.truyentranhonline.CreateView

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.itschool.checongbinh.truyentranhonline.R
import org.jetbrains.anko.*

/**
 * Created by Binh on 6/2/17.
 */
class ItemHienThiTruyen:AnkoComponent<ViewGroup?>{
    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui){

            frameLayout {

                lparams(width = matchParent, height = matchParent)

                imageView {
                    id = R.id.imgHinhTruyen
                    scaleType = ImageView.ScaleType.FIT_XY
                }
            }
        }
    }

}