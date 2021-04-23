package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_new_group_bottom_sheet.*

class NewGroupBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_group_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        create_group_btn_btm.setOnClickListener {
            //val action = NewGroupBottomSheetFragmentDirections.actionNewGroupBottomSheetFragmentToGroupDataFragment("newGroup")
            findNavController().navigate(R.id.action_newGroupBottomSheetFragment_to_addGroupFragment)
        }
    }
}