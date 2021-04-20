package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.example.customermanagementsystem.models.PostNewBoard
import com.example.customermanagementsystem.repository.Repository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_new_status.*

class NewStatusFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_status_btn_btm2.setOnClickListener {
            val newBoardName = status_name.text.toString()
            val newBoard = PostNewBoard(newBoardName)
            val repository = Repository()
            val viewModelFactory =
                ViewModelFactory(
                    repository
                )
            viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
            viewModel.createBoard(newBoard)
            viewModel.newBoard.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    Toast.makeText(context, resources.getString(R.string.board_is_created), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_newStatusFragment_to_mainFragment)
                } else if(!response.isSuccessful){
                    Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                }
            })
        }


    }

}