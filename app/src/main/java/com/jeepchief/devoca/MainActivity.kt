package com.jeepchief.devoca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jeepchief.devoca.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun initUI() {
        binding.apply {
            btnVoca.setOnClickListener {

            }

            btnSetting.setOnClickListener {

            }
        }
    }
}