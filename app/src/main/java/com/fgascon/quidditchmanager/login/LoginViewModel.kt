package com.fgascon.quidditchmanager.login

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> get() = _user

    val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> get() = _errorText

    private val _email: MutableLiveData<String> = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password: MutableLiveData<String> = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val auth: FirebaseAuth= FirebaseAuth.getInstance()

    public fun canSignIn(): Boolean {
        var canSignIn = false

        auth.signInWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                    if (user.value!!.isEmailVerified) {
                        canSignIn = true
                    } else {
                        _errorText.value = "Tienes que verificar tu email"
                    }
                } else {
                    _errorText.value = "Login incorrecto"
                }
            }
        return canSignIn
    }

    fun verifyEmail() {

    }

    fun resetPassword() {
        if (!validateEmailText(email.value)) return

        auth.sendPasswordResetEmail(email.value!!)
    }

    fun validateEmailText(email: String?): Boolean {
        if (email.isNullOrEmpty())
            return false

        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches();
    }

    fun validatePasswordText(password: String?): Boolean {
        if (password.isNullOrEmpty())
            return false

        return true
    }

    fun validateRegistrationFields(): Boolean {
        return validateEmailText(email.value)
                && validatePasswordText(password.value)
    }

    public fun createAccount(): Boolean {

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

    public fun setEmail(email: String?){
        _email.value = email
    }
    public fun setPassword(password: String?){
        _password.value = password
    }

}