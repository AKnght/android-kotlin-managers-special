package com.randythedford.managerspecials.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.util.DisplayMetrics
import android.util.Pair
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.randythedford.managerspecials.R
import com.randythedford.managerspecials.api.data.ProductEntity
import com.randythedford.managerspecials.controls.StrikeThruTextView
import com.randythedford.managerspecials.ui.ProductActivity
import com.squareup.picasso.Picasso

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val productNameTextView: TextView = itemView.findViewById(R.id.productName)
    private val originalPriceTextView: StrikeThruTextView = itemView.findViewById(R.id.productOriginalPrice)
    private val priceTextView: TextView = itemView.findViewById(R.id.productPrice)
    private val imageView: ImageView = itemView.findViewById(R.id.productImage)
    private val displayMetrics = DisplayMetrics()

    init {
        (itemView.context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    }

    fun bindData(product: ProductEntity, canvasUnits: Int) {
        clearViewHolder()

        val segment = displayMetrics.widthPixels.toFloat().div(canvasUnits)

        var calcWidth = 0
        var calcHeight = 0

        if (product.extraWidth > 0) {
            calcWidth = (segment * product.extraWidth).toInt()
            calcHeight = (segment * product.extraWidth).toInt()
            itemView.visibility = View.INVISIBLE
        } else {
            itemView.visibility = View.VISIBLE
            calcWidth = (segment * product.width).toInt() * 2
            calcHeight = (segment * product.height).toInt() * 2

            product.apply {
                productNameTextView.text = displayName
                originalPriceTextView.text = itemView.context.getString(R.string.dollar_sign_format, originalPrice)
                priceTextView.text = itemView.context.getString(R.string.dollar_sign_format, price)

                if (originalPrice == price) {
                    priceTextView.visibility = View.GONE
                    originalPriceTextView.gravity = Gravity.CENTER_VERTICAL or Gravity.END
                    originalPriceTextView.removeStrike()
                } else {
                    priceTextView.visibility = View.VISIBLE
                    originalPriceTextView.gravity = Gravity.BOTTOM or Gravity.END
                    originalPriceTextView.addStrike()
                }

                Picasso.get().load(imageUrl).into(imageView)
            }

            itemView.setOnClickListener(View.OnClickListener {
                //Transition to Product Activity

                it.apply {

                    val intent = Intent(context, ProductActivity::class.java)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imageView.transitionName = "productImage"

                        val options = if (product.originalPrice != product.price) {
                            ActivityOptions.makeSceneTransitionAnimation(context as Activity,
                                    Pair.create(imageView, context.getString(R.string.shared_view_product_image)),
                                    Pair.create(priceTextView, context.getString(R.string.shared_view_product_price)),
                                    Pair.create(originalPriceTextView, context.getString(R.string.shared_view_product_original_price)),
                                    Pair.create(productNameTextView, context.getString(R.string.shared_view_product_name)))
                        } else {
                            ActivityOptions.makeSceneTransitionAnimation(context as Activity,
                                    Pair.create(imageView, context.getString(R.string.shared_view_product_image)),
                                    Pair.create(originalPriceTextView, context.getString(R.string.shared_view_product_original_price)),
                                    Pair.create(productNameTextView, context.getString(R.string.shared_view_product_name)))
                        }

                        intent.putExtra(ProductEntity.NAME, product)
                        context.startActivity(intent, options.toBundle())

                    } else {
                        intent.putExtra(ProductEntity.NAME, product)
                        context.startActivity(intent)
                    }
                }
            })

        }

        itemView.layoutParams.width = calcWidth
        itemView.layoutParams.height = calcHeight
    }

    private fun clearViewHolder() {
        productNameTextView.text = ""
        originalPriceTextView.text = ""
        priceTextView.text = ""
        imageView.setImageDrawable(null)
    }
}