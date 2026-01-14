package com.example.tracker.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.tracker.R
import com.example.tracker.database.AppDatabase
import com.example.tracker.database.DatabaseProvider
import com.example.tracker.dto.LoginRequest
import com.example.tracker.model.User
import com.example.tracker.service.AuthService
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.lang.RuntimeException


class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, 0)
            insets
        }

        val email = findViewById<TextInputEditText>(R.id.textViewEmail)
        val password = findViewById<TextInputEditText>(R.id.textViewPassword)
        val buttonSignIn = findViewById<Button>(R.id.buttonSignIn)

        buttonSignIn.setOnClickListener {
            finish()
            val mainPage = Intent(this, LayoutActivity:: class.java)
            startActivity(mainPage)
        }

        val signUp = findViewById<TextView>(R.id.textViewSignUp)
        signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity:: class.java)
            startActivity(intent)
        }

        db = DatabaseProvider.getDatabase(this)
        authService = AuthService(db.userDao(), db.credentialsDao())

        buttonSignIn.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val loginRequest = LoginRequest(
                        email.text.toString(),
                        password.text.toString()
                    )
                    val userId = authService.login(loginRequest)
                    val homePage = Intent(this@MainActivity, LayoutActivity::class.java)
                    homePage.putExtra("USER_ID", userId)
                    finish()
                    startActivity(homePage)
                } catch (e: RuntimeException) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }




    }

}