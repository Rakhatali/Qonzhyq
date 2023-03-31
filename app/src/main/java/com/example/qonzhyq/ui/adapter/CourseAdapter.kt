package com.example.qonzhyq.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qonzhyq.R
import com.example.qonzhyq.data.models.Course

class CourseAdapter(
    private val context: Context,
    private val courses: List<Course>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var select: ((Course) -> Unit)? = null

    private inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        private val imageView: ImageView = view.findViewById(R.id.img_service)
        private val nameTextView: TextView = view.findViewById(R.id.tv_title)
        private val descriptionTextView: TextView = view.findViewById(R.id.tv_description)
        private val container: FrameLayout = view.findViewById(R.id.container)

        fun initViews(course: Course) {
            nameTextView.text = course.name
            descriptionTextView.text = course.description
            container.setOnClickListener {
                select?.invoke(courses[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recommend, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArtistViewHolder).initViews(courses[position])
    }

    override fun getItemCount(): Int = courses.size

}