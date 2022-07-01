package com.example.codialup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codialup.databinding.ActivityMainBinding
import com.example.codialup.utils.Mydata

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}