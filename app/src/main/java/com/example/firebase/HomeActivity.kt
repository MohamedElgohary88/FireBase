package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*

class HomeActivity : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var myIntent = intent
        var myUserEmail = myIntent.getStringExtra("id")
        userEmail.text = myUserEmail

        logout.setOnClickListener {
            Toast.makeText(this, " Logging Out ... " , Toast.LENGTH_SHORT).show()
            signOut()
        }
        myAuth.addAuthStateListener {
            if (myAuth.currentUser == null){
                this.finish()
            }
        }
    }

    private fun signOut() {
        myAuth.signOut()
    }
    }
