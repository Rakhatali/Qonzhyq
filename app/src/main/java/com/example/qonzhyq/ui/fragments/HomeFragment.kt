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
import com.example.qonzhyq.data.models.Course
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.databinding.FragmentHomeBinding
import com.example.qonzhyq.ui.activities.MainActivity
import com.example.qonzhyq.ui.adapter.CourseAdapter
import com.example.qonzhyq.ui.factory.HomeViewModelFactory
import com.example.qonzhyq.ui.viewmodel.HomeViewModel
import com.example.qonzhyq.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.UUID

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

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000L)
            requireActivity().runOnUiThread {
                binding.btnBest1.visibility = View.VISIBLE
                binding.btnBest2.visibility = View.VISIBLE

                binding.btnBest1.setOnClickListener {
                    (activity as MainActivity).currentCourse.url = "${Constants.BASE_URL}student/lessons/detail/20"
                    findNavController().navigate(R.id.action_homeFragment_to_courseFragment)
                }
                binding.btnBest2.setOnClickListener {
                    val course = Course(UUID.randomUUID().toString(), "desc", "name","${Constants.BASE_URL}student/lessons/detail/18")
                    (activity as MainActivity).currentCourse = course
                    findNavController().navigate(R.id.action_homeFragment_to_courseFragment)
                }
            }
        }



    }

    private fun setListeners() {

        binding.btnBest.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_courseFragment)
        }
//        viewModel.courseResponse.observe(viewLifecycleOwner, Observer {
//            if (it.isSuccessful) {
//                binding.tvBestTitle.text = it.body()!![1].name
//                binding.tvBestDescription.text = it.body()!![1].description
//                binding.btnBest.setOnClickListener {view->
//                    (activity as MainActivity).currentCourse = it.body()!![1]
//                    findNavController().navigate(R.id.action_homeFragment_to_courseFragment)
//                }
//                binding.rvRecommend.adapter = CourseAdapter(requireContext(), it.body()!!).apply {
//                    select = {course->
//                        (activity as MainActivity).currentCourse = course
//                        findNavController().navigate(R.id.action_homeFragment_to_courseFragment)
//                    }
//                }
//            } else {
//                Log.d("Response", it.errorBody().toString())
//                Log.d("Response", it.code().toString())
//            }
//        })


    }


}