package com.randythedford.managerspecials.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.randythedford.managerspecials.R
import com.randythedford.managerspecials.api.data.ProductEntity
import com.randythedford.managerspecials.databinding.ActivityProductBinding
import com.squareup.picasso.Picasso

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.allowEnterTransitionOverlap = true

        binding = ActivityProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val product = intent.getSerializableExtra(ProductEntity.NAME) as ProductEntity
        product.apply {
            binding.apply {
                productName.text = displayName
                productOriginalPrice.text = getString(R.string.dollar_sign_format, originalPrice)
                productPrice.text = getString(R.string.dollar_sign_format, price)

                if (originalPrice == price) {
                    productPrice.visibility = View.GONE
                    productOriginalPrice.gravity = Gravity.CENTER_VERTICAL or Gravity.END
                    productOriginalPrice.removeStrike()
                } else {
                    productPrice.visibility = View.VISIBLE
                    productOriginalPrice.gravity = Gravity.BOTTOM or Gravity.END
                    productOriginalPrice.addStrike()
                }

                Picasso.get().load(imageUrl).into(binding.productImage)
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
    }

}