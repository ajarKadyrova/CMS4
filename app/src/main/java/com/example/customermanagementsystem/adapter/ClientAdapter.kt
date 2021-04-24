package com.example.customermanagementsystem.adapter

import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.Client
import com.example.customermanagementsystem.models.ClientDTO
import com.example.customermanagementsystem.models.ClientItem
import com.example.customermanagementsystem.models.StudentsDTO
import kotlinx.android.synthetic.main.list_view.view.*

class ClientAdapter(private var clientsList: List<Client>,
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
        val res: Resources = holder.itemView.context.resources
        val currentClient = clientsList[position]
        if (currentClient.firstName == null && currentClient.lastName == null) {
            holder.name.text = "Заявка №" + (position+1)
        } else{
            holder.name.text = currentClient.firstName + " " + currentClient.lastName
        }
        holder.number.text = currentClient.phoneNumber
        holder.course.text = "currentClient.wantsCourse.name"
        when (holder.course.text) {
            "Android" -> {
                holder.course.backgroundTintList = ColorStateList.valueOf(res.getColor(R.color.green))
            }
            "UX/UI" -> {
                holder.course.backgroundTintList = ColorStateList.valueOf(res.getColor(R.color.course_purple))
            }
            "JavaScript" -> {
                holder.course.backgroundTintList = ColorStateList.valueOf(res.getColor(R.color.yellow))
            }
            ("PM") -> {
                holder.course.backgroundTintList = ColorStateList.valueOf(res.getColor(R.color.course_pink))
            }
            "Java" -> {
                holder.course.backgroundTintList = ColorStateList.valueOf(res.getColor(R.color.course_orange))
            }
            "Python" -> {
                holder.course.backgroundTintList = ColorStateList.valueOf(res.getColor(R.color.course_blue))
            }
        }
        holder.date.text = currentClient.registrationDate
    }

    override fun getItemCount() = clientsList.size

    fun setData(newList: List<Client>) {
        clientsList = newList
        notifyDataSetChanged()
    }

    inner class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
        val name: TextView = itemView.name_tv
        val number: TextView = itemView.number_tv
        val course: TextView = itemView.date_tv2
        val date: TextView = itemView.date_tv

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}