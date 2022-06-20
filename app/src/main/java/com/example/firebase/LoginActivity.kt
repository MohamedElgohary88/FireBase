package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

     private var myAuth = FirebaseAuth.getInstance()

    private lateinit var editText1 : EditText
    private lateinit var editText2 : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editText1 = findViewById(R.id.email2)
        editText2 = findViewById(R.id.password2)

        login.setOnClickListener {
            val email = editText1.text.toString().trim()
            val password = editText2.text.toString().trim()
            if (email.isEmpty()){
                editText1.error = "Filed Required"

            }
            if (password.isEmpty()){
                editText1.error = "Filed Required"
            }
            signIn(email, password)
        }

        register2.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {


      //  Toast.makeText(this, " LoggedIn :)", Toast.LENGTH_SHORT).show()
        myAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this,HomeActivity::class.java)
                intent.putExtra("id",myAuth.currentUser!!.email)
                startActivity(intent)
            } else {
                Toast.makeText(this, " Sorry !! " + task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}