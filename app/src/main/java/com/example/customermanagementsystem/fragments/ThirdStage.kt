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

class ThirdStage : Fragment(), ClientAdapter.OnItemClickListener {

    private val list1 = ArrayList<ClientItem>()
    private val adapter = ClientAdapter(list1, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = generateData(15)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateData(size: Int): List <ClientItem> {
        val list1 = ArrayList<ClientItem>()
        for (i in 0 until size){
            val item = ClientItem(
                "Кадырова Ажар",
                "+996550121212",
                "Android",
                "12.01.21"
            )
            list1 += item
        }
        return list1;
    }
    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem:ClientItem = list1[position]
        clickedItem.name = "Clicked"
        adapter.notifyItemChanged(position)
    }
}