package com.example.customermanagementsystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.customermanagementsystem.pagerAdapter.ClientsPagerAdapter
import kotlinx.android.synthetic.main.fragment_wrap_group_data.*

class WrapGroupDataFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wrap_group_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager_wrap_group_data.adapter = ClientsPagerAdapter(childFragmentManager)
        tabLayout_wrap_groups_data.setupWithViewPager(viewPager_wrap_group_data)
    }
}