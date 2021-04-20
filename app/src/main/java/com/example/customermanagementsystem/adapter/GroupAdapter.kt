package com.example.customermanagementsystem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.GroupDTO
import kotlinx.android.synthetic.main.group_view.view.*
import java.util.*
import kotlin.collections.ArrayList

class GroupAdapter(private var groupsList: List<GroupDTO>,
                   private val listener: OnItemClickListener) :
        RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.group_view, parent, false)
        return GroupViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val currentClient = groupsList[position]

        holder.teacher_name.text = currentClient.teacher.name
        holder.course.text = currentClient.course.name
        val days : MutableList<String>  = ArrayList()
        for(i in currentClient.timeTable.daysOfWeeks){
            when (i) {
                "MONDAY" -> days.add("Понедельник")
                "TUESDAY" -> days.add("Вторник")
                "WEDNESDAY" -> days.add("Среда")
                "THURSDAY" -> days.add("Четверг")
                "FRIDAY" -> days.add("Пятница")
                "SATURDAY" -> days.add("Суббота")
                "SUNDAY" -> days.add("Воскресенье")
            }
        }
        holder.schedule.text = days.toString()
            .replace("[", "" )
            .replace("]", "")
        holder.start_date.text = currentClient.startDate
        holder.students_num.text = currentClient.numberOfStudents.toString() + " учеников"
    }

    override fun getItemCount() = groupsList.size

    fun setData(newList: List<GroupDTO>){
        groupsList = newList
        notifyDataSetChanged()
    }

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener{
        val teacher_name: TextView = itemView.teacher_name_tb
        val course: TextView = itemView.course_name_tv
        val start_date: TextView = itemView.time_tb
        val schedule: TextView = itemView.schedule_tb
        val students_num: TextView = itemView.students_num_tb

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, groupsList.get(position))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, groupDTO: GroupDTO)
    }
}