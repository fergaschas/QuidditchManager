package com.fgascon.quidditchmanager.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    companion object{
        private const val TAG = "LoginViewModel"
    }

    private val _email: MutableLiveData<String> = MutableLiveData()
    val email:LiveData<String> get() = _email

    private val _password: MutableLiveData<String> = MutableLiveData()
    val password:LiveData<String> get() = _password


}