package com.example.customermanagementsystem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.ClientItem
import kotlinx.android.synthetic.main.list_view.view.*

class ClientAdapter(private val clientsList: List<ClientItem>,
                    private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_view,
            parent, false)
        return ClientViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val currentClient = clientsList[position]

        holder.name.text = currentClient.name
        holder.number.text = currentClient.phoneNumber
        holder.course.text = currentClient.course
        holder.date.text = currentClient.date
    }

    override fun getItemCount() = clientsList.size

    inner class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val name : TextView = itemView.name_tv
        val number : TextView = itemView.number_tv
        val course : TextView = itemView.date_tv2
        val date : TextView = itemView.date_tv

        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position:Int = adapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}