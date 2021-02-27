package com.example.customermanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.group_view.view.*

class GroupAdapter(private val groupsList: List<GroupItem>) : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.group_view,
            parent, false)
        return GroupViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val currentClient = groupsList[position]

        holder.teacher_name.text = currentClient.teacher
        holder.start_date.text = currentClient.startDate
        holder.schedule.text = currentClient.schedule
        holder.students_num.text = currentClient.students.toString()
    }

    override fun getItemCount() = groupsList.size

    class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val teacher_name : TextView = itemView.teacher_name_tb
        val start_date : TextView = itemView.start_date_tb
        val schedule : TextView = itemView.schedule_tb
        val students_num : TextView = itemView.students_num_tb

    }
}