package com.fgascon.quidditchmanager.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fgascon.quidditchmanager.data.preferences.Prefs
import com.fgascon.quidditchmanager.databinding.FragmentRegisterBinding
import com.fgascon.quidditchmanager.manager.MainActivity
import com.google.android.material.snackbar.Snackbar


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.apply {
            registerFragment = this@RegisterFragment
        }

        viewModel.errorText.observe(viewLifecycleOwner) {
            Snackbar.make(this.requireContext(), binding.root, it, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.RED)
                .show()
        }

        viewModel.canCreateAccount.observe(viewLifecycleOwner) { accountIsCreated ->
            if (accountIsCreated) goToMainActivity()
        }

        return  binding.root
    }

    private fun goToMainActivity() {

        Prefs(requireContext()).setLogged(true)

        Intent(context, MainActivity::class.java).apply {
            startActivity(this)
        }
        activity?.finish()
    }

    fun createAccount() {
        hideKeyboard()

        viewModel.setEmail(binding.emailInput.text.toString())
        viewModel.setPassword(binding.passwordInput.text.toString())

        viewModel.tryCreateAccount()
    }

    fun goToLoginFragment(){
        val navController = findNavController()
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        navController.navigate(action)
    }

    private fun hideKeyboard() {
        val keyboard =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}