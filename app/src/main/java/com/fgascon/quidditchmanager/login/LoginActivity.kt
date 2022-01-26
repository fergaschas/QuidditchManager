package com.fgascon.quidditchmanager.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.fgascon.quidditchmanager.R

class LoginActivity : AppCompatActivity() {

    private companion object {
        private const val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.login_graph) as NavHostFragment
        val navController = navHostFragment.navController
    }

}