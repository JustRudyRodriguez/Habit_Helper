package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addButton.setOnClickListener{
            val intent= Intent(this, GoalSetupActivity::class.java )
            startActivity(intent);

        }
    }
}