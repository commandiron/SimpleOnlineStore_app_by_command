package com.demirli.a51simpleonlinestore.ui.fragment.products

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirli.a51simpleonlinestore.R
import com.demirli.a51simpleonlinestore.model.Product
import com.demirli.a51simpleonlinestore.ui.activity.MainViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import java.lang.ClassCastException

private lateinit var adapter: ProductsAdapter
private lateinit var viewModel: MainViewModel

class ProductsFragment : Fragment(),
    ProductsAdapter.OnCartButtonClick {

    private var productsFragmentListener: ProductsFragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(MainViewModel::class.java)

        viewModel.getAllProducts().observe(this, Observer {

            products_recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter =
                ProductsAdapter(
                    activity!!.applicationContext,
                    it,
                    this
                )
            products_recyclerView.adapter =
                adapter
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            productsFragmentListener = context as ProductsFragmentListener
        }catch (e:  ClassCastException){
            throw ClassCastException(context.toString() + "ProductsFragmentListener implement edilmeli.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        productsFragmentListener = null
    }

    override fun onCartButtonClick(product: Product) {
        productsFragmentListener?.cartItemFromProductsFragment(product)
    }
}
