package com.example.pooldiary

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pooldiary.models.User
import com.google.gson.Gson

class AboutUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_user)
        val extras = intent.extras




        val user = Gson().fromJson(intent.getStringExtra("user"), User::class.java)

        val tv = findViewById<TextView>(R.id.textView)
        tv.text = user.name
    }



}