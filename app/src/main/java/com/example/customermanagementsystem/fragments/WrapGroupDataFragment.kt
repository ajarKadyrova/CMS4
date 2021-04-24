package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.pagerAdapter.GroupsDataPagerAdapter
import kotlinx.android.synthetic.main.fragment_wrap_group_data.*


class WrapGroupDataFragment : Fragment() {
    var groupId: Long = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        groupId = requireArguments().getLong("groupId", 0)
        return inflater.inflate(R.layout.fragment_wrap_group_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager_wrap_group_data.adapter = GroupsDataPagerAdapter(childFragmentManager)
        tabLayout_wrap_groups_data.setupWithViewPager(viewPager_wrap_group_data)

    }
}