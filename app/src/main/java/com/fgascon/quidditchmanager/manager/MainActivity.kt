package com.fgascon.quidditchmanager.manager

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.fgascon.quidditchmanager.R
import com.fgascon.quidditchmanager.data.preferences.Prefs
import com.fgascon.quidditchmanager.login.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = Prefs(applicationContext)
        val isLogged = prefs.getLogged()

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        if (isLogged)
            goToMainActivity()

    }

    private fun goToMainActivity() {
        navHostFragment.navController.navigate(LoginFragmentDirections.actionLoginFragmentToNavGraph())
    }
}