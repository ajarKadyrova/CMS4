package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.Client
import com.example.customermanagementsystem.models.ClientDTO
import com.example.customermanagementsystem.models.Filter
import com.example.customermanagementsystem.repository.Repository
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_clients_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_clients_bottom_sheet.view.*
import java.util.ArrayList

class ClientsBottomSheetFragment : BottomSheetDialogFragment() {

    var clientId: Long = 0
    var clientBoard: Int = 0
    private lateinit var client: ClientDTO
    lateinit var viewModel: ViewModel
    private var boardsList: List<ClientDTO> = ArrayList()
    private var mylist: List<ClientDTO> = ArrayList()
    lateinit var radioButton: RadioButton
   // private lateinit var boardsHashMap: HashMap<String, Long>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_clients_bottom_sheet, container, false)
        val bundle = requireArguments()
        clientId = bundle.getLong("clientID", -1)
        Log.e("clientID : ", clientId.toString())
        val filter = Filter(null, null, null, null)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        val inflater: LayoutInflater = LayoutInflater.from(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllBoards(1, filter)
        viewModel.allBoards.observe(viewLifecycleOwner, Observer { response ->
            if (!response.isSuccessful) {
                Log.e("ANSWER_FRAGMENT", "Code:" + response.code())
            }
            if (response.isSuccessful) {
                Log.e("ANSWER_FRAGMENT", "Code:" + response.code())
                boardsList = response.body()!!
                mylist = boardsList
                for (text in boardsList.indices) {
//                    boardsHashMap = HashMap()
//                    boardsHashMap[boardsList[text].boardName] = boardsList[text].id
                    radioButton = inflater.inflate(R.layout.radio_button_item, null, false) as RadioButton
                    radioButton.text = boardsList[text].boardName.toString()
                    radioButton.id = boardsList[text].id.toInt()
                    Log.e("ids: ", boardsList[text].id.toString())
                    radioGroup.addView(radioButton)
                }

            }
        })
        rootview.apply_btn_btm_sheet.setOnClickListener {
            //  Log.e("clientBoard: ", clientBoard.toString())
            viewModel.changeClientStatus(clientId, 2)
            viewModel.changeClientStatusResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    val str = response.body()!!
                    Toast.makeText(context, "Статус изменен",Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                    Log.e("STATUS", "Code:" + response.code())
                }
            })
        }
        return rootview
    }

//    private fun setClicks(view : View) {
//        view.
//    }

//    private fun checkClickedBoard(listOfDTO: List<ClientDTO>): Int {
//        for (i in listOfDTO.indices) {
//            if (listOfDTO[i].id == radioButton.id.toLong()) {
//            }
//            return if (radioButton.isChecked) {
//                radioButton.id
//            } else {
//                1
//            }
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        open_profile_btm_sheet.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable("clientID", clientId)
           // val action = ClientsBottomSheetFragmentDirections.actionBottomSheetFragmentToClientProfileFragment("bottomSheet")
            findNavController().navigate(R.id.action_bottomSheetFragment_to_clientProfileFragment, bundle)
        }

    }
}