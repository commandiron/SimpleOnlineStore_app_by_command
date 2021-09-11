package com.demirli.a51simpleonlinestore.ui.fragment.products

import com.demirli.a51simpleonlinestore.model.Product

interface ProductsFragmentListener {
    fun cartItemFromProductsFragment(product: Product)
}