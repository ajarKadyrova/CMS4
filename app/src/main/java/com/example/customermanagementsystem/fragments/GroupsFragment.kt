package com.example.customermanagementsystem.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.ViewModel
import com.example.customermanagementsystem.ViewModelFactory
import com.example.customermanagementsystem.adapter.GroupAdapter
import com.example.customermanagementsystem.models.GroupDTO
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment(), GroupAdapter.OnItemClickListener {

    private lateinit var viewModel: ViewModel
    lateinit var sharedPreferences: SharedPreferences
    private var groupsList: List<GroupDTO> = ArrayList()
    private val adapter by lazy { GroupAdapter(groupsList, this)}
    private var groupId: Long = 0
    private var instance: GroupsFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_groups, container, false)
        instance = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val token = sharedPreferences.getInt("token", 0)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllGroups(1)
        viewModel.allGroups.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { adapter.setData(it) }
                recyclerView_groups.adapter = adapter
                recyclerView_groups.layoutManager = LinearLayoutManager(context)
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Groups", "body + " + response.body().toString())
            }
        })

        fab_groups.setOnClickListener {
            findNavController().navigate(R.id.action_groupsFragment_to_newGroupBottomSheetFragment)
        }
    }

    override fun onItemClick(position: Int, groupDTO: GroupDTO) {
        groupId = groupDTO.id
        val sharedPrefs: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putLong("groupId", groupId).apply()
        findNavController().navigate(R.id.action_groupsFragment_to_wrapGroupDataFragment)
    }

    public fun getInstanceGroup(): GroupsFragment? {
        return instance
    }

    public fun getGroupId(): Long {
        return groupId
    }
}