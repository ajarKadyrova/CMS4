package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.adapter.ClientAdapter
import com.example.customermanagementsystem.models.ClientItem
import com.example.customermanagementsystem.R
import kotlinx.android.synthetic.main.fragment_clients.*

class StudentsFragment : Fragment(), ClientAdapter.OnItemClickListener{

    val list1 = ArrayList<ClientItem>()
    private val adapter = ClientAdapter(list1, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students, container, false)
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
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateData(size: Int): List <ClientItem> {
        for (i in 0 until size){
            val item = ClientItem(
                "Турсунбеков Камиль",
                "+996550101010",
                "Python",
                "12.01.2021"
            )
            list1 += item
        }
        return list1;
    }
    override fun onItemClick(position: Int) {
        val clickedItem: ClientItem = list1[position]
        adapter.notifyItemChanged(position)
    }
}