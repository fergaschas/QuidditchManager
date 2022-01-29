package com.fgascon.quidditchmanager.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val email: MutableLiveData<String> = MutableLiveData("")

    private val password: MutableLiveData<String> = MutableLiveData("")

    private val _canSignIn: MutableLiveData<Boolean> = MutableLiveData(false)
    val canSignIn: LiveData<Boolean> get() = _canSignIn

    private val _canCreateAccount: MutableLiveData<Boolean> = MutableLiveData(false)
    val canCreateAccount: LiveData<Boolean> get() = _canCreateAccount

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> get() = _errorText

    private val _okText = MutableLiveData<String>()
    val okText: LiveData<String> get() = _okText

    fun trySignIn() {

        if (!validateRegistrationFields())
            return

        auth.signInWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _canSignIn.value = true
                } else {
                    _errorText.value = "Login incorrecto"
                }
            }
    }

    fun tryCreateAccount() {

        if (!validateRegistrationFields())
            return

        auth.createUserWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _canCreateAccount.value = true
                } else {
                    _errorText.value = "Error en el registro"
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
                if (task.isSuccessful) {
                    _okText.value = "Email para restablecer la contrasena enviado"
                } else {
                    _errorText.value = "Este email no esta registrado"
                }
            }
    }

    private fun validateEmailText(email: String?): Boolean {
        if (email.isNullOrEmpty())
            return false

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorText.value = "El email no es válido"
            return false
        }

        return true
    }

    private fun validatePasswordText(password: String?): Boolean {
        if (password.isNullOrEmpty())
            return false

        if (password.length < 6) {
            _errorText.value = "la contraseña tiene que tener mínimo 6 carácteres"
            return false
        }

        return true
    }

    private fun validateRegistrationFields(): Boolean {

        return validateEmailText(email.value)
                && validatePasswordText(password.value)
    }

    fun setEmail(email: String?) {
        this.email.value = email ?: ""
    }

    fun setPassword(password: String?) {
        this.password.value = password ?: ""
    }

}