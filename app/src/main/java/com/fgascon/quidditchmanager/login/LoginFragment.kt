package com.fgascon.quidditchmanager.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.fgascon.quidditchmanager.R
import com.fgascon.quidditchmanager.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.apply {
            loginFragment = this@LoginFragment
            loginViewModel = viewModel
        }

        binding.signInBtn.setOnClickListener{
            viewModel.email.value = binding.emailInput.text.toString()
            Toast.makeText(context, viewModel.email.value, Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }



}