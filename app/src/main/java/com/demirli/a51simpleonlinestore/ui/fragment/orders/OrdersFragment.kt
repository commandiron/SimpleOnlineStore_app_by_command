package com.demirli.a51simpleonlinestore.ui.fragment.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.demirli.a51simpleonlinestore.R
import com.demirli.a51simpleonlinestore.ui.activity.MainViewModel
import com.demirli.a51simpleonlinestore.ui.fragment.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_orders.*

private lateinit var adapter: OrdersAdapter
private lateinit var viewModel: MainViewModel

private lateinit var cartUniqueId: String

class OrdersFragment : Fragment(), OrdersAdapter.OnGoToDetailsButtonClickListener{

    companion object {
        fun newInstance(cartUniqueId: String): OrdersFragment {
            val args = Bundle()
            args.putString("cartUniqueId", cartUniqueId)

            val fragment =
                OrdersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        cartUniqueId = arguments?.getString("cartUniqueId")!!

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirmation_id.setText("Confirmation ID: " + cartUniqueId)

        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(MainViewModel::class.java)

        //UserId test için el ile verildi.
        viewModel.getAllCarts(1).observe(this, Observer {

            val llm = LinearLayoutManager(activity?.applicationContext)
            llm.stackFromEnd = true
            llm.reverseLayout = true
            all_orders_recyclerView.layoutManager = llm

            adapter =
                OrdersAdapter(
                    activity!!.applicationContext,
                    it,
                    this
                )
            all_orders_recyclerView.adapter =
                adapter
        })
    }

    override fun onDetailButtonClick(confirmationId: String) {

        viewModel.getAllCartDetails(confirmationId).observe(this, Observer {

            val cartDetailList =it.toCollection(ArrayList())

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,
                DetailFragment.newInstance(cartDetailList)).addToBackStack("fragmentStack").commit()

        })
    }
}
