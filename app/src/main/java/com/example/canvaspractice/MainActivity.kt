package com.example.canvaspractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mWidget.goal = 97.8F


        mWidget.setListener(object : HalfProgressBar.LevelListener {
            override fun onInit() {
                Toast.makeText(this@MainActivity, "Start", Toast.LENGTH_SHORT).show()
            }

            override fun onGoal() {
                Toast.makeText(this@MainActivity, "Finish", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
