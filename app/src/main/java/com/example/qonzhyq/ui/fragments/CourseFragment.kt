package com.example.qonzhyq.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.qonzhyq.R
import com.example.qonzhyq.data.models.Course
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.databinding.FragmentCourseBinding
import com.example.qonzhyq.databinding.FragmentMyCourseBinding
import com.example.qonzhyq.ui.activities.MainActivity
import com.example.qonzhyq.ui.adapter.LessonAdapter
import com.example.qonzhyq.ui.factory.MyCourseViewModelFactory
import com.example.qonzhyq.ui.viewmodel.MyCourseViewModel
import com.example.qonzhyq.utils.Constants


class CourseFragment : Fragment() {

    private lateinit var binding: FragmentCourseBinding
    private lateinit var viewModel: MyCourseViewModel
    private lateinit var course: Course


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVars()
        setListeners()
    }

    private fun initVars() {
        val repository = Repository()
        val viewModelFactory = MyCourseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyCourseViewModel::class.java)
        course = (activity as MainActivity).currentCourse
//        viewModel.getLesson(course.id!!)
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl((activity as MainActivity).currentCourse.url)
        binding.webView.getSettings().setDomStorageEnabled(true)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
    }

    private fun setListeners() {

//        viewModel.lessonsResponse.observe(viewLifecycleOwner, Observer {
//            if (it.isSuccessful) {
//                binding.rvRecommend.adapter = LessonAdapter(requireContext(), it.body()!!).apply {
//                    select = {}
//                }
//            } else {
//                Log.d("Response", it.errorBody().toString())
//                Log.d("Response", it.code().toString())
//            }
//        })


    }

}