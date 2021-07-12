package com.example.qrcodev2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.splash_activity.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        Start.setOnClickListener {
            val intent: Intent = Intent(this, Splash_Activity::class.java).apply {
                putExtra("Id_hospital", txtIdHos.text.toString())
            }
            startActivity(intent)
        }
    }
}