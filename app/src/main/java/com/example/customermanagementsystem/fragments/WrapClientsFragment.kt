package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.ClientDTO
import com.example.customermanagementsystem.models.Filter
import com.example.customermanagementsystem.pagerAdapter.ClientsPagerAdapter
import com.example.customermanagementsystem.repository.Repository
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.wrap_clients.*
import java.util.*

/*class WrapClientsFragment : Fragment() {

    private lateinit var viewModel: ViewModel
    private var clientsList: List<ClientDTO> = ArrayList()
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var adapter: ClientsPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.wrap_clients, container, false)
        viewPager = view.findViewById<View>(R.id.viewPager_wrap) as ViewPager
        adapter = context?.let { ClientsPagerAdapter(fragmentManager, it) }
        viewPager!!.adapter = adapter
        tabLayout = view.findViewById<View>(R.id.tabLayout_wrap) as TabLayout
        tabLayout!!.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_clients.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fabBottomSheetFragment)
        }
        addPage()
    }

    fun addPage() {

        val body = BoardID(null)
        val filter = Filter(body, null, null, null)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllBoards(1, filter)
        viewModel.allClients.observe(viewLifecycleOwner, Observer { response ->
            if (!response.isSuccessful) {
                Log.d("ANSWER", "Code:" + response.code())
            }
            if (response.isSuccessful) {
                clientsList = response.body()!!
                val boards: MutableList<String> = java.util.ArrayList()
                for (i in clientsList.indices) {
                    boards.add(clientsList[i].boardName)
                }
                for (i in boards.indices) {
                    val fragmentChild = StudentsFragment()
                    adapter?.addFragment(fragmentChild, boards[i])
                    adapter?.notifyDataSetChanged()
                    if(adapter != null) {
                        if (adapter!!.count > 0) tabLayout!!.setupWithViewPager(viewPager)
                        viewPager!!.currentItem = adapter!!.count - 1
                    }
                }
            }
        })
    }
}*/

class WrapClientsFragment: Fragment() {
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var adapter: ClientsPagerAdapter? = null
    private lateinit var viewModel: ViewModel
    private var coursesList: List<ClientDTO> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.wrap_clients, container, false)
        viewPager = view.findViewById<View>(R.id.viewPager_wrap) as ViewPager
        adapter = context?.let { ClientsPagerAdapter(fragmentManager, it) }
        viewPager!!.adapter = adapter
        tabLayout = view.findViewById<View>(R.id.tabLayout_wrap) as TabLayout
        tabLayout!!.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_clients.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fabBottomSheetFragment)
        }

        val filter = Filter(null, null, null, null)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllBoards(1, filter)
        viewModel.allBoards.observe(viewLifecycleOwner, Observer { response ->
            if (!response.isSuccessful) {
                Log.d("ANSWER", "Code:" + response.code())
            }
            if (response.isSuccessful) {
                coursesList = response.body()!!
                for (i in coursesList.indices) {
                    val bundle = Bundle()
                    val body: ClientDTO = coursesList[i]
                    bundle.putSerializable("clients", body)
                    val fragmentChild = FirstStage()

                    adapter?.addFragment(fragmentChild, coursesList[i].boardName)
                    fragmentChild.arguments = bundle
                    adapter?.notifyDataSetChanged()
                    if(adapter != null) {
                        if (adapter!!.count > 0) tabLayout!!.setupWithViewPager(viewPager)
                        viewPager!!.currentItem = adapter!!.count - 1
                    }
                    else if (adapter == null){

                    }
                }
            }
        })
    }
}