package com.example.bursarybaby

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var etName: EditText
    private lateinit var etSurname: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button

    // Firebase
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // link to your XML file

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        // Link to XML IDs
        etName = findViewById(R.id.etName)
        etSurname = findViewById(R.id.etSurname)
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)

        // Ensure button has proper touch target size
        btnRegister.minHeight = 48 // dp standard
        btnRegister.setPadding(32, 24, 32, 24)

        // Handle Register button click
        btnRegister.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        val name = etName.text.toString().trim()
        val surname = etSurname.text.toString().trim()
        var username = etUsername.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()
        val testRef = FirebaseDatabase.getInstance().getReference("TestNode")
        testRef.setValue("Hello Firebase!")
            .addOnSuccessListener {
                Toast.makeText(this, "Database Connected ✅", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Database Error: ${it.message}", Toast.LENGTH_LONG).show()
            }


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(email) ||
            TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)
        ) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Register with Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = mAuth.currentUser
                    user?.let {
                        val userId = it.uid

                        // If username is empty, use email
                        if (TextUtils.isEmpty(username)) {
                            username = email
                        }

                        // Save user details in Firebase Realtime Database
                        val newUser = User(name, surname, username, email, phone)
                        mDatabase.child(userId).setValue(newUser).addOnCompleteListener { dbTask ->
                            if (dbTask.isSuccessful) {
                                Toast.makeText(this@RegisterActivity, "Registration successful!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Database error: ${dbTask.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Auth failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    // Model class for user data
    data class User(
        val name: String = "",
        val surname: String = "",
        val username: String = "",
        val email: String = "",
        val phone: String = ""
    )
}
