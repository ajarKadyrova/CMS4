package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_fab_bottom_sheet.*

class FabBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fab_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_status_btn_btm.setOnClickListener {
            findNavController().navigate(R.id.action_fabBottomSheetFragment_to_newStatusFragment)
        }
        add_client_btn_btm.setOnClickListener {
            val action = FabBottomSheetFragmentDirections.actionFabBottomSheetFragmentToClientProfileFragment("fab")
            findNavController().navigate(action)
        }
    }
}