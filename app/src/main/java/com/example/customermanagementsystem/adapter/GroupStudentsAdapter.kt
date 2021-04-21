package com.example.customermanagementsystem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.GroupDTO
import kotlinx.android.synthetic.main.group_students_view.view.*

class GroupStudentsAdapter(private var studentsList:GroupDTO) :RecyclerView.Adapter<GroupStudentsAdapter.GroupStudentsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupStudentsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.group_students_view, parent, false)
        return GroupStudentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupStudentsViewHolder, position: Int) {
        val currentStudent = studentsList.students[position]
        holder.name.text = currentStudent.studentName
        //holder.payment1.text = currentStudent.payment1.toString()
        //holder.payment2.text = currentStudent.payment2.toString()
        //holder.payment3.text = currentStudent.payment3.toString()
    }

    override fun getItemCount() = 1

    fun setData(newList: GroupDTO){
        studentsList = newList
        notifyDataSetChanged()
    }

    class GroupStudentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name :TextView = itemView.student_group_name
        val payment1:TextView = itemView.payment1_student_group
        val payment2:TextView = itemView.payment2_student_group
        val payment3:TextView = itemView.payment3_student_group
    }
}