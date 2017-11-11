package com.example.muhammed.myfireauth

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var btn : Button
    lateinit var btn1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.email)
        editText2 = findViewById(R.id.password)
        btn = findViewById(R.id.register)
        btn1 = findViewById(R.id.login)




        btn.setOnClickListener {
            val email = editText1.text.toString()
            val password = editText2.text.toString().trim()

            if(email.isEmpty() && password.isEmpty()){
            Toast.makeText(this,"Please enter your email & password",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else{
                signUp(email,password)
            }

        }



        btn1.setOnClickListener {
            view  ->
            val email = editText1.text.toString()
            val password = editText2.text.toString().trim()
            signIn(view,email,password)



        }


    }


    private fun  signIn(view:View,email:String,password:String){
        Toast.makeText(this,"Authenticating ... ",Toast.LENGTH_LONG).show()
        myAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
            if(task.isSuccessful){
     var intent = Intent(this,LoggedIn2Activity::class.java)
                intent.putExtra("id", myAuth.currentUser?.email)
                startActivity(intent)
            }else{
  Toast.makeText(this,"Sorry !!"+ task.exception?.message,Toast.LENGTH_LONG).show()
            }
        })

    }





    private fun signUp(email:String , password:String){

        myAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful){
    Toast.makeText(this,"Done successfully !!",Toast.LENGTH_LONG).show()
                    }else{
   Toast.makeText(this,"Sorry !!"+ task.exception?.message,Toast.LENGTH_LONG).show()
                    }
                })
    }


}
