package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.adapter.ClientAdapter
import com.example.customermanagementsystem.models.ClientItem
import kotlinx.android.synthetic.main.fragment_clients.*

class ScheduleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val list = generateData(15)
        //recyclerView.adapter = ClientAdapter(list)
       // recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.setHasFixedSize(true)
    }

    private fun generateData(size: Int): List <ClientItem> {
        val list1 = ArrayList<ClientItem>()
        for (i in 0 until size){
            val item = ClientItem(
                "Турсунбеков Камиль",
                "+996550101010",
                "#Python",
                "12.01.2021"
            )
            list1 += item
        }
        return list1;
    }
}