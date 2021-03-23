package com.example.customermanagementsystem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.adapter.GroupAdapter
import com.example.customermanagementsystem.models.GroupItem
import com.example.customermanagementsystem.R
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = generateData(15)
        recyclerView_groups.adapter =
            GroupAdapter(list)
        recyclerView_groups.layoutManager = LinearLayoutManager(context)
        recyclerView_groups.setHasFixedSize(true)
    }

    private fun generateData(size: Int): List <GroupItem> {
        val list1 = ArrayList<GroupItem>()
        for (i in 0 until size){
            val item = GroupItem(
                "Шевченко Дмитрий",
                "10 Апрель",
                "Вт/Чт/Сб",
                22
            )
            list1 += item
        }
        return list1;
    }
}