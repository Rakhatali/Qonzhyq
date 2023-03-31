package com.example.qonzhyq.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.qonzhyq.R
import com.example.qonzhyq.data.models.Course
import com.example.qonzhyq.databinding.ActivityMainBinding
import com.example.qonzhyq.utils.Constants

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    var currentCourse = Course()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val sharedPreference = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        if (sharedPreference.getString("token", "user") != "user") {
            val token = sharedPreference.getString("token", "user")!!
            Constants.token = token
        }
        binding.bottomNavigation.setupWithNavController(navController)
    }
}