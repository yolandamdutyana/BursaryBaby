package com.example.bursarybaby

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome) // links to activity_welcome.xml layout

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Get references to the views
        val emailInput = findViewById<EditText>(R.id.editTextEmail)
        val passwordInput = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val googleButton = findViewById<Button>(R.id.btnGoogle)
        val facebookButton = findViewById<Button>(R.id.btnFacebook)
        val microsoftButton = findViewById<Button>(R.id.btnMicrosoft)
        val registerText = findViewById<TextView>(R.id.tvRegister)

        // Handle Login Button (basic logic for now)
        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Just an example check - you’ll replace this with Firebase Auth later
            if (email.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login success → go to HomeActivity
                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish() // close login screen
                    } else {
                        // Login failed
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Handle Register Text Click
        registerText.setOnClickListener {
            val intent = Intent(this, Register::class.java) // your registration page
            startActivity(intent)
        }

        // For now, Google, Facebook, Microsoft buttons won’t do anything
        googleButton.setOnClickListener {
            // TODO: Add Google login later
        }
        facebookButton.setOnClickListener {
            // TODO: Add Facebook login later
        }
        microsoftButton.setOnClickListener {
            // TODO: Add Microsoft login later
        }
    }

    class Register(activity: WelcomeActivity, java: Any) {

    }
}
