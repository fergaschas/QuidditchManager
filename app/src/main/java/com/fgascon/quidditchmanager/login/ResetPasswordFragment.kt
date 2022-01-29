package com.fgascon.quidditchmanager.login

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fgascon.quidditchmanager.databinding.FragmentResetPasswordBinding
import com.google.android.material.snackbar.Snackbar

class ResetPasswordFragment : Fragment() {

    private lateinit var binding:FragmentResetPasswordBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)

        binding.apply {
            resetPasswordFragment = this@ResetPasswordFragment
        }

        viewModel.errorText.observe(viewLifecycleOwner) {
            Snackbar.make(this.requireContext(), binding.root, it, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.RED)
                .show()
        }

        viewModel.okText.observe(viewLifecycleOwner){
            Snackbar.make(this.requireContext(), binding.root, it, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.GREEN)
                .setTextColor(Color.BLACK)
                .show()

            goToLoginFragment()
        }

        return binding.root
    }

    fun resetPassword(){
        hideKeyboard()
        viewModel.setEmail(binding.emailInput.text.toString())

        viewModel.resetPassword()
    }

    private fun goToLoginFragment(){
        val navController = findNavController()
        val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
        navController.navigate(action)
    }

    private fun hideKeyboard() {
        val keyboard =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}