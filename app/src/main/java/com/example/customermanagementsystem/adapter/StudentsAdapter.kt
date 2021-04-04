package com.example.customermanagementsystem.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.StudentsDTO
import kotlinx.android.synthetic.main.list_view.view.*

class StudentsAdapter (private var studentsList: List<StudentsDTO>,
                       private val listener: OnItemClickListener) :
        RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.list_view,
                parent, false)
        return StudentsViewHolder(
                itemView
        )
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        val res: Resources = holder.itemView.context.resources
        val currentStudent = studentsList[position]

        holder.name.text = currentStudent.studentName
        holder.number.text = currentStudent.phoneNumber
        holder.course.text = currentStudent.completedCourses.toString()
    }

    override fun getItemCount() = studentsList.size

    fun setData(newList: List<StudentsDTO>){
        studentsList = newList
        notifyDataSetChanged()
    }

    inner class StudentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
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
            if(position!= RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}