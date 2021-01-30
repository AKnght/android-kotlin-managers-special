package com.randythedford.managerspecials.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.randythedford.managerspecials.adapter.ProductsAdapter
import com.randythedford.managerspecials.api.data.ProductEntity
import com.randythedford.managerspecials.api.data.ProductPageEntity
import com.randythedford.managerspecials.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel;
    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViewModel()

        mainViewModel.isLoading().observe(this, Observer { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            }
            else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        })

        mainViewModel.getManagersPage().observe(this, Observer { page ->
            calculateProducts(page)

            //CanvasSize is doubled inside GridLayoutManager to prevent a left over of odd span which can't be split up
            val gridLayoutManager = GridLayoutManager(this, page.canvasUnit.toInt() * 2, GridLayoutManager.VERTICAL, false)

            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

                override fun getSpanSize(position: Int): Int {
                    if (position == 0) {
                        return page.canvasUnit.toInt() * 2
                    }
                    val product = page.products[position - 1]

                    return if (product.extraWidth > 0) {
                        product.extraWidth
                    } else {
                        product.width * 2
                    }
                }
            }

            binding.recyclerView.layoutManager = gridLayoutManager
            adapter = ProductsAdapter(page.products, page.canvasUnit.toInt() * 2);
            binding.recyclerView.adapter = adapter
        })
    }


    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun calculateProducts(page: ProductPageEntity) {
        val allProductsArray = arrayListOf<ProductEntity>()
        val currentProductRowArray = arrayListOf<ProductEntity>()

        var currentRow: Int = 0
        var currentSpan: Int = 0
        val canvasUnit: Int = page.canvasUnit.toInt()

        for (index in page.products.indices) {
            val product = page.products[index]
            val productWidth = product.width.toInt()

            // See if the next view doesn't fit in row?
            if (productWidth + currentSpan > canvasUnit) {
                val extraWidth = canvasUnit - currentSpan

                if (extraWidth > 0) {
                    //Start Space
                    allProductsArray.add(ProductEntity(extraWidth))
                }

                for (rowItem in currentProductRowArray) {
                    allProductsArray.add(rowItem)
                }

                if (extraWidth > 0) {
                    //End Space
                    allProductsArray.add(ProductEntity(extraWidth))
                }

                currentRow++
                currentSpan = productWidth
                product.index = currentRow
                currentProductRowArray.clear()
            } else {
                currentSpan = currentSpan.toInt() + productWidth
            }

            currentProductRowArray.add(product)
        }

        // final row handling
        if (currentSpan % canvasUnit > 0) {
            val extraWidth = canvasUnit - currentSpan

            if (extraWidth > 0) {
                //Start Space
                allProductsArray.add(ProductEntity(extraWidth))
            }

            for (rowItem in currentProductRowArray) {
                allProductsArray.add(rowItem)
            }

            if (extraWidth > 0) {
                //End Space
                allProductsArray.add(ProductEntity(extraWidth))
            }
        }

        page.products = allProductsArray
    }

}