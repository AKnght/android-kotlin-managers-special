package com.randythedford.managerspecials.api.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

public data class ProductEntity (
    @SerializedName("display_name") var displayName: String = "",
    @SerializedName("imageUrl") var imageUrl: String = "",
    @SerializedName("original_price") var originalPrice: String = "",
    @SerializedName("price") var price: String = "",
    @SerializedName("width") var width: Int = 0,
    @SerializedName("height") var height: Int = 0,
    var index: Int = 0,
    var extraWidth: Int = 0

    ) : Serializable
{
    companion object {
        const val NAME = "product"
    }

    constructor(extraWidth : Int) : this("","","", "", 0, 0, 0, extraWidth)
}

/*
// For reference
// Raw Json Data for products as of Jan, 27th, 2021
{
      "display_name": "Noodle Dish with Roasted Black Bean Sauce",
      "height": 8,
      "imageUrl": "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
      "original_price": "2.00",
      "price": "1.00",
      "width": 16
}
*/