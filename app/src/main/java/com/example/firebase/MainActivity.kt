package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE_SIGNIN = 0
    var myAuth = FirebaseAuth.getInstance()

    lateinit var editText1 : EditText
    lateinit var editText2 : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.email)
        editText2 = findViewById(R.id.password)

        register.setOnClickListener {

            val email = editText1.text.toString().trim()
            val password = editText2.text.toString().trim()

            if (email.isEmpty()){
                editText1.error = "Filed Required"
                return@setOnClickListener
            }
            if (password.isEmpty()){
            editText1.error = "Filed Required"
                return@setOnClickListener
            }
            signUp(email , password)
        }

        login.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
/*

    private fun signIn(view: View, email: String, password: String) {
        Toast.makeText(this, " LoggedIn :)", Toast.LENGTH_SHORT).show()
        myAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                var intent = Intent(this,LoginActivity::class.java)
                intent.putExtra("id",myAuth.currentUser!!.email)
                startActivity(intent)
            } else {
                Toast.makeText(this, " Sorry !! " + task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
*/

    private fun signUp(email:String , password:String) {
      //  Toast.makeText(this, " LoggedIn :)", Toast.LENGTH_SHORT).show()
       myAuth.createUserWithEmailAndPassword(email,password)
           .addOnCompleteListener { task ->
           if (task.isSuccessful) {
               val intent = Intent(this,HomeActivity::class.java)
               intent.putExtra("id",myAuth.currentUser!!.email)
               startActivity(intent)
               Toast.makeText(this, " Successful ", Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(this, " Sorry !! " + task.exception?.message, Toast.LENGTH_SHORT).show()
           }
       }
    }
}