package com.example.qonzhyq.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.qonzhyq.R
import com.example.qonzhyq.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

}