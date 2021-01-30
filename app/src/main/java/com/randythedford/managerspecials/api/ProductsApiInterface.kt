package com.randythedford.managerspecials.api

import com.randythedford.managerspecials.api.data.ProductPageEntity
import retrofit2.Call
import retrofit2.http.GET

interface ProductsApiInterface {

    @GET("Swiftly-Systems/code-exercise-android/master/backup")
    fun getManagersSpecial() : Call<ProductPageEntity>

}
