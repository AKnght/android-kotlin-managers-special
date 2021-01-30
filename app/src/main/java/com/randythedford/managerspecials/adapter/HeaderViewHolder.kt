package com.randythedford.managerspecials.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.randythedford.managerspecials.R

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var headerView: TextView = itemView.findViewById(R.id.headerView)

}