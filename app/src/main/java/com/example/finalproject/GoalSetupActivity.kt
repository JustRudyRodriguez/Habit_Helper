package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GoalSetupActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.goalsetup_layout)

        val gButton = findViewById<Button>(R.id.goalSubmitButton)
        val goalSetup = findViewById<Button>(R.id.goalCancelButton)





    }
}