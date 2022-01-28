package com.fgascon.quidditchmanager.login

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> get() = _user

    private val _email: MutableLiveData<String> = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password: MutableLiveData<String> = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _canSignIn: MutableLiveData<Boolean> = MutableLiveData(false)
    val canSignIn: LiveData<Boolean> get() = _canSignIn

    val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> get() = _errorText

    val _okText = MutableLiveData<String>()
    val okText: LiveData<String> get() = _okText

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun trySignIn() {

        if (!validateRegistrationFields())
            return

        auth.signInWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                    _canSignIn.value = true
                } else {
                    _errorText.value = "Login incorrecto"
                }
            }
    }

    fun resetPassword() {
        if (!validateEmailText(email.value)) {
            _errorText.value = "Este email no es correcto"
            return
        }

        auth.sendPasswordResetEmail(email.value!!)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _okText.value = "Email para restablecer la contrasena enviado"
                }else{
                    _errorText.value = "Este email no esta registrado"
                }
            }
    }

    private fun validateEmailText(email: String?): Boolean {
        if (email.isNullOrEmpty())
            return false

        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePasswordText(password: String?): Boolean {
        if (password.isNullOrEmpty())
            return false

        return true
    }

    private fun validateRegistrationFields(): Boolean {
        val isValid = validateEmailText(email.value)
                && validatePasswordText(password.value)
        if (!isValid) {
            _errorText.value = "Login incorrecto"
        }
        return isValid
    }

    fun createAccount(): Boolean {

        var canCreateAccount = false
        if (validateRegistrationFields())
            return false

        auth.createUserWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                    canCreateAccount = true
                } else {
                    _errorText.value = "Error en el registro"
                }
            }
        return canCreateAccount
    }

    fun setEmail(email: String?) {
        _email.value = email ?: ""
    }

    fun setPassword(password: String?) {
        _password.value = password ?: ""
    }

}