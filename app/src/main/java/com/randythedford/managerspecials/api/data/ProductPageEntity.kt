package com.randythedford.managerspecials.api.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

public data class ProductPageEntity(

    @SerializedName("canvasUnit") var canvasUnit: String = "",
    @SerializedName("managerSpecials") var products: ArrayList<ProductEntity> = arrayListOf()
) : Serializable

/*
// For reference
// Raw Json Data for product Page as of Jan, 27th, 2021
{
  "canvasUnit": 16,
  "managerSpecials": [
    {
      "display_name": "Noodle Dish with Roasted Black Bean Sauce",
      "height": 8,
      "imageUrl": "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/L.png",
      "original_price": "2.00",
      "price": "1.00",
      "width": 16
    },
    {
      "display_name": "Onion Flavored Rings",
      "height": 8,
      "imageUrl": "https://raw.githubusercontent.com/prestoqinc/code-exercise-ios/master/images/J.png",
      "original_price": "2.00",
      "price": "1.00",
      "width": 8
    }
  ]
}
*/