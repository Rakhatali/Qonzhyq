package com.example.qonzhyq.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dentalplaza.front.dialog.Dialog
import com.example.qonzhyq.R
import com.example.qonzhyq.data.repository.Repository
import com.example.qonzhyq.databinding.FragmentLoginBinding
import com.example.qonzhyq.ui.activities.MainActivity
import com.example.qonzhyq.ui.factory.LoginViewModelFactory
import com.example.qonzhyq.ui.viewmodel.LoginViewModel
import com.example.qonzhyq.utils.Constants
import java.util.UUID


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var dialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVars()
        setListeners()
    }


    private fun initVars() {
        val repository = Repository()
        val viewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        dialog = Dialog(requireActivity())

        val sharedPreference = requireActivity().getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        if (sharedPreference.getString("token", "user") != "user") {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setListeners() {
        binding.btnSignIn.setOnClickListener {
            dialog.startLoadingdialog()
            if (binding.etLogin.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {
                if (binding.etLogin.text.toString().toLowerCase() == "dauletargynbayev" && binding.etPassword.text.toString() == "123456") {
                    dialog.dismissdialog()
                    val sharedPreference = requireActivity().getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
                    val editor = sharedPreference.edit()
                    editor.putString("token", UUID.randomUUID().toString())
                    editor.apply()
                    val intent = Intent(activity, MainActivity::class.java)

                    startActivity(intent)
                }
            }

            dialog.dismissdialog()
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }


        viewModel.tokenResponse.observe(viewLifecycleOwner, Observer {
            dialog.dismissdialog()
            if (it.isSuccessful) {
                val sharedPreference = requireActivity().getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("token", it.body()!!.token)
                editor.apply()
                Log.d("Response", sharedPreference.getString("token", "user")!!)
                dialog.dismissdialog()
                val intent: Intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Log.d("Response", it.errorBody().toString())
                Log.d("Response", it.code().toString())
                Toast.makeText(activity, it.errorBody().toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getCookie(siteName: String?, cookieName: String?): String? {
        var CookieValue: String? = null
        val cookieManager = CookieManager.getInstance()
        val cookies = cookieManager.getCookie(siteName)
        val temp = cookies.split(";".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        for (ar1 in temp) {
            if (ar1.contains(cookieName!!)) {
                val temp1 = ar1.split("=".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                CookieValue = temp1[1]
                break
            }
        }
        return CookieValue
    }

}