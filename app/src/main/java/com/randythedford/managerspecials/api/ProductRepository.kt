package com.randythedford.managerspecials.api

import androidx.lifecycle.MutableLiveData
import com.randythedford.managerspecials.api.data.ProductPageEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    var pageEntity: MutableLiveData<ProductPageEntity> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun isLoading(): MutableLiveData<Boolean> {
        return loading;
    }

    fun getManagersSpecial(): MutableLiveData<ProductPageEntity> {

        val call = ProductsClient.apiProductsInterface.getManagersSpecial()
        loading.value = true

        call.enqueue(object : Callback<ProductPageEntity> {
            override fun onFailure(call: Call<ProductPageEntity>, t: Throwable) {
                loading.value = false
            }

            override fun onResponse(call: Call<ProductPageEntity>, response: Response<ProductPageEntity>) {
                loading.value = false

                pageEntity.value = response.body()
            }
        })

        return pageEntity
    }
}