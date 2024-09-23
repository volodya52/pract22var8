package com.example.pract22var8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class signin : AppCompatActivity() {
    lateinit var email1:EditText
    lateinit var password1:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        email1=findViewById(R.id.email)
        password1=findViewById(R.id.password)
    }
    fun Next(view: View){
        if (email1.text.isNotEmpty()&&password1.text.isNotEmpty()){
            val intent=Intent(this,firstdisplay::class.java)
            startActivity(intent)
        }else{
            val sn=Snackbar.make(view,"Вы не заполнили окно ввода",Snackbar.LENGTH_LONG)
            sn.show()
        }
    }

}