package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.pagerAdapter.ClientsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_clients.*
import kotlinx.android.synthetic.main.wrap_clients.*

class WrapClientsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.wrap_clients, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager_wrap.adapter = ClientsPagerAdapter(childFragmentManager)
        tabLayout_wrap.setupWithViewPager(viewPager_wrap)
        fab_clients.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fabBottomSheetFragment)

        }
    }
}