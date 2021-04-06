package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.ViewModel
import com.example.customermanagementsystem.ViewModelFactory
import com.example.customermanagementsystem.adapter.ClientAdapter
import com.example.customermanagementsystem.models.ClientItem
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.fragment_clients.*


class FirstStage : Fragment(), ClientAdapter.OnItemClickListener {

    private lateinit var viewModel: ViewModel
    private val list1 = ArrayList<ClientItem>()
    private val adapter = ClientAdapter(list1, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_clients, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val list = generateData(15)
        list1.add(ClientItem("Кадырова Ажар", "+996550121212", "Android", "12.01.21"))
        list1.add(ClientItem("Айсулуу Арстанбаева","0505555555","Python","25.03.2021"))
        list1.add(ClientItem("Аида Исмаилова", "0505555555", "PM", "10.03.2021"))
        list1.add(ClientItem("Дастан Асанов", "0505555555", "UX/UI","10.02.2021"))
        list1.add(ClientItem("Эмир Арстанбаев", "0500555555", "JavaScript","12.02.2021"))
        list1.add(ClientItem("Лина Ким", "04445434323", "Android","20.02.2021"))
        list1.add(ClientItem("Ислам Токтогазиев", "0505555555", "Android", "23.10.2021"))
        list1.add(ClientItem("Дастан Маратов", "0505555555", "JavaScript", "12.01.2021"))
        list1.add(ClientItem("Дастан Камилов", "0505555555", "Python", "10.03.2021"))
        list1.add(ClientItem("Камал Кожонов", "0500555555", "Java","12.02.2021"))
        list1.add(ClientItem("Бекжан Акматов","0707898989","Android","25.03.2021"))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }


    override fun onItemClick(position: Int) {
        val clickedItem:ClientItem = list1[position]
        adapter.notifyItemChanged(position)
        findNavController().navigate(R.id.action_mainFragment_to_bottomSheetFragment)
    }
}
