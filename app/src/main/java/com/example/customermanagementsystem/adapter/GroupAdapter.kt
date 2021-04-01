package com.example.customermanagementsystem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.GroupItem
import kotlinx.android.synthetic.main.group_view.view.*

class GroupAdapter(private val groupsList: List<GroupItem>,
                   private val listener: OnItemClickListener) :
        RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.group_view,
                parent, false)
        return GroupViewHolder(
                itemView
        )
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val currentClient = groupsList[position]

        holder.teacher_name.text = currentClient.teacher
        holder.start_date.text = currentClient.startDate
        holder.schedule.text = currentClient.schedule
        holder.students_num.text = currentClient.students.toString()
    }

    override fun getItemCount() = groupsList.size

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener{
        val teacher_name: TextView = itemView.teacher_name_tb
        val start_date: TextView = itemView.time_tb
        val schedule: TextView = itemView.schedule_tb
        val students_num: TextView = itemView.students_num_tb

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