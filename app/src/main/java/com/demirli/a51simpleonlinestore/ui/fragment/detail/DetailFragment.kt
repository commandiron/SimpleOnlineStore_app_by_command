package com.demirli.a51simpleonlinestore.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirli.a51simpleonlinestore.R
import com.demirli.a51simpleonlinestore.model.CartDetail
import com.demirli.a51simpleonlinestore.model.Product
import com.demirli.a51simpleonlinestore.ui.activity.MainViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

private lateinit var adapter: DetailAdapter
private lateinit var viewModel: MainViewModel

private lateinit var listOfCartDetail: ArrayList<CartDetail>
private lateinit var productList: ArrayList<Product>


class DetailFragment : Fragment() {

    companion object {
        fun newInstance(listOfCartDetail: ArrayList<CartDetail>): DetailFragment {
            val args = Bundle()
            try {
                args.putSerializable("listOfCartDetail", listOfCartDetail)
            }catch (e: Exception){
                e.printStackTrace()
            }

            val fragment =
                DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_detail, container, false)

        productList = arrayListOf()

        try {
            listOfCartDetail = arguments?.getSerializable("listOfCartDetail") as ArrayList<CartDetail>

        }catch (e: Exception){
            e.printStackTrace()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(MainViewModel::class.java)

        val llm = LinearLayoutManager(activity?.applicationContext)
        llm.stackFromEnd = true
        llm.reverseLayout = true
        cart_details_recyclerView.layoutManager = llm

        for(i in listOfCartDetail){
            viewModel.getSingleProduct(i.product_id).observe(this, Observer {

                productList.add(it)

                println(productList)
                adapter =
                    DetailAdapter(
                        activity!!.applicationContext,
                        productList,
                        listOfCartDetail
                    )
                cart_details_recyclerView.adapter =
                    adapter
                adapter.notifyDataSetChanged()
            })
        }
        super.onViewCreated(view, savedInstanceState)
    }

}
