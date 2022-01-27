package com.fgascon.quidditchmanager.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fgascon.quidditchmanager.MainActivity
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

        viewModel.errorText.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        binding.signInBtn.setOnClickListener {
            hideKeyboard()
            signIn()
        }

        return binding.root
    }

    fun goToResetPasswordFragment() {
        val navController = findNavController()
        val action = ""// TODO: 27/1/22
    }

    fun goToSignUpFragment() {
        val navController = findNavController()
        // TODO: 27/1/22 navigate to register fragment
    }

    fun signIn() {

        viewModel.setEmail(binding.emailInput.text.toString())
        viewModel.setPassword(binding.passwordInput.text.toString())

        if (!viewModel.canSignIn()) return

        Intent(context, MainActivity::class.java).apply {
            startActivity(this)
        }
        activity?.finish()
    }

    fun hideKeyboard() {
        val keyboard = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

}