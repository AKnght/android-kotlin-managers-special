package com.randythedford.managerspecials.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.randythedford.managerspecials.R
import com.randythedford.managerspecials.api.data.ProductEntity


class ProductsAdapter(private val dataSet: ArrayList<ProductEntity>,
                      private val canvasUnits: Int = 16) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            ViewHolderType.HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_header, parent, false)
                return HeaderViewHolder(view)
            }
            ViewHolderType.PRODUCT.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_product, parent, false)
                return ProductViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_space, parent, false)
                return SpaceViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewHolderType.HEADER.ordinal -> {
                //No need to bind the header data
            }
            ViewHolderType.PRODUCT.ordinal -> {
                (viewHolder as ProductViewHolder).bindData(dataSet[position - 1], canvasUnits)
            }
            else -> { // Space
                (viewHolder as SpaceViewHolder).bindData(dataSet[position - 1], canvasUnits)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Check if it's the 1st position
        return if (position == 0) {
            //Return Header
            ViewHolderType.HEADER.ordinal
        }
        else {
            if (dataSet[position - 1].extraWidth == 0) {
                ViewHolderType.PRODUCT.ordinal
            }
            else {
                ViewHolderType.SPACE.ordinal
            }
        }
    }

    override fun getItemCount() = dataSet.size + 1

}
