package com.example.customermanagementsystem.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.adapter.GroupAdapter
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.ViewModel
import com.example.customermanagementsystem.ViewModelFactory
import com.example.customermanagementsystem.models.GroupDTO
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment(), GroupAdapter.OnItemClickListener {

    val list1 = ArrayList<GroupDTO>()
    private val adapter = GroupAdapter(list1, this)
    private lateinit var viewModel: ViewModel
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val token = sharedPreferences.getInt("token", 0)

        //val list = generateData(15)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllGroups(1)
        viewModel.allGroups.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Groups", response.message())
            }
            else{
                Log.d("Groups", response.errorBody().toString())
                Log.d("Groups", response.code().toString())
                Log.d("Groups", response.message())
            }
        })

        recyclerView_groups.adapter = adapter
        recyclerView_groups.layoutManager = LinearLayoutManager(context)
        recyclerView_groups.setHasFixedSize(true)

        fab_groups.setOnClickListener {
            findNavController().navigate(R.id.action_groupsFragment_to_newGroupBottomSheetFragment)
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem: GroupDTO = list1[position]
        adapter.notifyItemChanged(position)
        findNavController().navigate(R.id.action_groupsFragment_to_wrapGroupDataFragment)
    }
}