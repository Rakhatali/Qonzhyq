package com.example.qonzhyq.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qonzhyq.R
import com.example.qonzhyq.data.models.Course
import com.example.qonzhyq.data.models.Lesson

class LessonAdapter(
    private val context: Context,
    private val lessons: List<Lesson>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var select: ((Course) -> Unit)? = null

    private inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //        private val imageView: ImageView = view.findViewById(R.id.img_service)
        private val nameTextView: TextView = view.findViewById(R.id.tv_title)
        private val descriptionTextView: TextView = view.findViewById(R.id.tv_description)

        fun initViews(lesson: Lesson) {
            nameTextView.text = lesson.name
            descriptionTextView.text = lesson.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lesson, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArtistViewHolder).initViews(lessons[position])
    }

    override fun getItemCount(): Int = lessons.size

}