package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.ClientDTO
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_clients_bottom_sheet.*

class ClientsBottomSheetFragment : BottomSheetDialogFragment() {

    var clientId: Long = 0
    var clientBoard: String = ""
    private lateinit var client: ClientDTO

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_clients_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        open_profile_btm_sheet.setOnClickListener {
            val action = ClientsBottomSheetFragmentDirections.actionBottomSheetFragmentToClientProfileFragment("bottomSheet")
            findNavController().navigate(action)
        }
        val bundle = requireArguments()
        clientId = bundle.getLong("clientID")
         clientBoard = bundle.getString("clientBoard").toString()
        val bundle1 = requireArguments()
        client = bundle1.getSerializable("clients") as ClientDTO
        val inflater: LayoutInflater = LayoutInflater.from(context)
        for (text in client.boardName) {
            val radioButton: RadioButton = inflater.inflate(R.layout.radio_button_item, null, false) as RadioButton
            radioButton.text = text.toString()
            radioGroup.addView(radioButton)

        }
    }
}