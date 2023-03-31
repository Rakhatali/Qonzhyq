package com.example.qonzhyq.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qonzhyq.R
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.databinding.FragmentHomeBinding
import com.example.qonzhyq.ui.activities.MainActivity
import com.example.qonzhyq.ui.adapter.CourseAdapter
import com.example.qonzhyq.ui.factory.HomeViewModelFactory
import com.example.qonzhyq.ui.viewmodel.HomeViewModel
import com.example.qonzhyq.utils.Constants
import java.sql.Date
import java.text.SimpleDateFormat

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVars()
        setListeners()
    }

    private fun initVars() {
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.getCourses()


        viewModel.getUser()

    }

    private fun setListeners() {

        viewModel.courseResponse.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                binding.tvBestTitle.text = it.body()!![1].name
                binding.tvBestDescription.text = it.body()!![1].description
                binding.btnBest.setOnClickListener {view->
                    (activity as MainActivity).currentCourse = it.body()!![1]
                    findNavController().navigate(R.id.action_homeFragment_to_courseFragment)
                }
                binding.rvRecommend.adapter = CourseAdapter(requireContext(), it.body()!!).apply {
                    select = {course->
                        (activity as MainActivity).currentCourse = course
                        findNavController().navigate(R.id.action_homeFragment_to_courseFragment)
                    }
                }
            } else {
                Log.d("Response", it.errorBody().toString())
                Log.d("Response", it.code().toString())
            }
        })


    }


}