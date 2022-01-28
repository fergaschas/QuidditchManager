package com.fgascon.quidditchmanager.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fgascon.quidditchmanager.R
import com.fgascon.quidditchmanager.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {

    private lateinit var binding:FragmentResetPasswordBinding
    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)

        binding.apply {
            resetPasswordFragment = this@ResetPasswordFragment
            loginViewModel = viewModel
        }

        viewModel.errorText.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}