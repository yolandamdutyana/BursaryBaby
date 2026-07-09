package com.example.bursarybaby

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

class BursaryBabyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //initializing firebase as soon as the app starts GLOBALLY
        FirebaseApp.initializeApp(this)
        Log.d("FirebaseInit", "Firebase initialized")

    }
}