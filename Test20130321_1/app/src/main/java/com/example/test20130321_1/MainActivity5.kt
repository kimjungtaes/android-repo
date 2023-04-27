package com.example.test20130321_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test20130321_1.databinding.ActivityMain5Binding

class MainActivity5 : AppCompatActivity() {
    lateinit var binding: ActivityMain5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}