package com.randythedford.managerspecials.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.randythedford.managerspecials.api.ProductRepository
import com.randythedford.managerspecials.api.data.ProductPageEntity


class MainViewModel() : ViewModel() {

    private var page: MutableLiveData<ProductPageEntity> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()
    private val productRepository: ProductRepository = ProductRepository()

    public fun isLoading() : LiveData<Boolean> {
        loading = productRepository.isLoading()
        return loading
    }
    public fun getManagersPage() : LiveData<ProductPageEntity> {
        page = productRepository.getManagersSpecial()
        return page
    }

}