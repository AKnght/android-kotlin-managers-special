package com.randythedford.managerspecials.helper

interface OnDataItemClick <T> {
    fun onClick(dataItem: T, id : Int)
}