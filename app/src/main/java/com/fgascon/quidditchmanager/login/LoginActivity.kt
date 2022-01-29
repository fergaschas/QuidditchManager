package com.fgascon.quidditchmanager.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.fgascon.quidditchmanager.MainActivity
import com.fgascon.quidditchmanager.R
import com.fgascon.quidditchmanager.data.preferences.Prefs

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val prefs = Prefs(applicationContext)
        val isLogged = prefs.getLogged()

        //if (isLogged)
            //goToMainActivity()

        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private fun goToMainActivity() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
        this.finish()
    }
}