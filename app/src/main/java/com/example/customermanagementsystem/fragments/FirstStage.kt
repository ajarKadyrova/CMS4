package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.adapter.ClientAdapter
import com.example.customermanagementsystem.models.Client
import com.example.customermanagementsystem.models.ClientDTO
import kotlinx.android.synthetic.main.fragment_clients.*
import java.util.ArrayList


class FirstStage : Fragment(), ClientAdapter.OnItemClickListener {

    private var clientsList: List<Client> = ArrayList()
    private lateinit var client: ClientDTO
    private val adapter = ClientAdapter(clientsList, this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_clients, container, false)
        val bundle = requireArguments()
        client = bundle.getSerializable("clients") as ClientDTO
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_bar_clients.visibility = View.VISIBLE
        clientsList = client.clients
        if(clientsList.isNotEmpty()) {
            adapter.setData(client.clients)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            progress_bar_clients.visibility = View.GONE
            textView.visibility = View.GONE
        }
        else {
            textView.setText(R.string.no_clients)
            progress_bar_clients.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }
    }


    override fun onItemClick(position: Int) {
        val clickedItem: Long = clientsList[position].id
        adapter.notifyItemChanged(position)
        val bundle = Bundle()
        val id: Long = clientsList[position].id
        //val board: String = clientsList[position].boards.boardName
        bundle.putLong("clientID", id)
        bundle.putSerializable("clients", client)
        //bundle.putString("clientBoard", board)
        Log.e("clientId: ", id.toString())
        findNavController().navigate(R.id.action_mainFragment_to_bottomSheetFragment, bundle)
    }
}
