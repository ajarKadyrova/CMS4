package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.repository.ViewModel
import androidx.lifecycle.Observer
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.example.customermanagementsystem.models.ClientDTO
import com.example.customermanagementsystem.pagerAdapter.ClientsPagerAdapter
import com.example.customermanagementsystem.repository.Repository
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.wrap_clients.*

class WrapClientsFragment : Fragment() {

    private lateinit var viewModel: ViewModel
    private var clientsList: List<ClientDTO> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.wrap_clients, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager_wrap.adapter = ClientsPagerAdapter(childFragmentManager)
        tabLayout_wrap.setupWithViewPager(viewPager_wrap)
        tabLayout_wrap.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_wrap.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        fab_clients.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fabBottomSheetFragment)
        }
        addPage()
    }

    fun addPage() {

        val adapter: ClientsPagerAdapter? = null
        val criteria: Any = Object()

        val repository = Repository()
        val viewModelFactory =
            ViewModelFactory(
                repository
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllBoards(1)
        viewModel.allClients.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                clientsList = response.body()!!
                Log.d("WRAPCLEITNS", clientsList.toString())
                for (i in clientsList.indices) {
                    val fragmentChild = FirstStage()
                    if (adapter != null) {
                        adapter.addFragment(fragmentChild, clientsList[i].boardName)
                        Log.d("WRAPCLEITNS", clientsList[i].boardName)
                        adapter.notifyDataSetChanged()
                        if (adapter.count > 0) tabLayout_wrap.setupWithViewPager(viewPager_wrap)
                        viewPager_wrap.currentItem = adapter.count - 1
                    }
                }
            }
            else if(!response.isSuccessful){
                Log.d("WRAPCLEITNS", response.body().toString())
                Log.d("WRAPCLEITNS", response.code().toString())
            }
        })
    }
}