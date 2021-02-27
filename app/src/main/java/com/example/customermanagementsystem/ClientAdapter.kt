package com.example.customermanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_view.view.*

class ClientAdapter(private val clientsList: List<ClientItem>) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_view,
            parent, false)
        return ClientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val currentClient = clientsList[position]

        holder.name.text = currentClient.name
        holder.number.text = currentClient.phoneNumber
        holder.course.text = currentClient.course
        holder.date.text = currentClient.date
    }

    override fun getItemCount() = clientsList.size

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.name_tv
        val number : TextView = itemView.number_tv
        val course : TextView = itemView.course_tv
        val date : TextView = itemView.date_tv

    }
}