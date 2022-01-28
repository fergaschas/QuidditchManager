package com.fgascon.quidditchmanager.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fgascon.quidditchmanager.MainActivity
import com.fgascon.quidditchmanager.R
import com.fgascon.quidditchmanager.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

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

            viewModel.canSignIn.observe(viewLifecycleOwner) { canSignIn ->
                if (canSignIn) goToMainActivity()
            }
        }

        viewModel.errorText.observe(viewLifecycleOwner) {
            Snackbar.make(this.requireContext(), binding.root, it, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.RED)
                .show()
        }

        return binding.root
    }

    fun goToResetPasswordFragment() {
        val navController = findNavController()
        val action = LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment()

        navController.navigate(action)

    }

    fun goToSignUpFragment() {
        val navController = findNavController()
        // TODO: 27/1/22 navigate to register fragment
    }

    fun signIn() {
        hideKeyboard()

        viewModel.setEmail(binding.emailInput.text.toString())
        viewModel.setPassword(binding.passwordInput.text.toString())

        viewModel.trySignIn()
    }

    private fun goToMainActivity() {

        Intent(context, MainActivity::class.java).apply {
            startActivity(this)
        }
        activity?.finish()
    }

    fun hideKeyboard() {
        val keyboard =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

}