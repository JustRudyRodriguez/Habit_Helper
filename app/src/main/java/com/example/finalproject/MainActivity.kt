package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val addButton = findViewById<FloatingActionButton>(R.id.Fab)
       // addButton.setOnClickListener{
            //val intent= Intent(this, GoalSetupActivity::class.java )
           // startActivity(intent);
        //}

                // setupActionBarWithNavController(findNavController(R.id.nav_fragmentController))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_fragmentController)
        return super.onSupportNavigateUp() || super.onSupportNavigateUp()
    }
}