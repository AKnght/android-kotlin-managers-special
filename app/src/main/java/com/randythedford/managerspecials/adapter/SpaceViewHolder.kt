package com.randythedford.managerspecials.adapter

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.randythedford.managerspecials.api.data.ProductEntity

class SpaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindData(product: ProductEntity, canvasUnits: Int) {
        val displayMetrics = DisplayMetrics()
        (itemView.context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        val segment = displayMetrics.widthPixels.toFloat().div(canvasUnits)

        itemView.layoutParams.width = (segment * product.extraWidth).toInt()
        itemView.layoutParams.height = (segment * product.extraWidth).toInt()
    }

}