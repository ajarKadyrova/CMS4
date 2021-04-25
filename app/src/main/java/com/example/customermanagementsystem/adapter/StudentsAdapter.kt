package com.example.customermanagementsystem.adapter

import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.StudentsDTO
import kotlinx.android.synthetic.main.list_view.view.*

class StudentsAdapter(
    private var studentsList: List<StudentsDTO>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false)
        return StudentsViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        val res: Resources = holder.itemView.context.resources
        val currentStudent = studentsList[position]

        holder.name.text = currentStudent.firstName + " " + currentStudent.lastName
        holder.number.text = currentStudent.phoneNumber
        if (currentStudent.wantsCourse != null) {
            holder.course.text = currentStudent.wantsCourse.name
            when (holder.course.text) {
                "Android" -> {
                    holder.course.backgroundTintList =
                        ColorStateList.valueOf(res.getColor(R.color.green))
                }
                "UX/UI" -> {
                    holder.course.backgroundTintList =
                        ColorStateList.valueOf(res.getColor(R.color.course_purple))
                }
                "JavaScript" -> {
                    holder.course.backgroundTintList =
                        ColorStateList.valueOf(res.getColor(R.color.yellow))
                }
                "PM" -> {
                    holder.course.backgroundTintList =
                        ColorStateList.valueOf(res.getColor(R.color.course_pink))
                }
                "Java" -> {
                    holder.course.backgroundTintList =
                        ColorStateList.valueOf(res.getColor(R.color.course_orange))
                }
                "Python" -> {
                    holder.course.backgroundTintList =
                        ColorStateList.valueOf(res.getColor(R.color.course_blue))
                }
                "IOS" -> {
                    holder.course.backgroundTintList =
                        ColorStateList.valueOf(res.getColor(R.color.course_light_blue))
                }
            }
        }
        else if (currentStudent.wantsCourse == null){
            holder.course.visibility = View.GONE
        }
    }

    override fun getItemCount() = studentsList.size

    fun setData(newList: List<StudentsDTO>) {
        studentsList = newList
        notifyDataSetChanged()
    }

    inner class StudentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
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