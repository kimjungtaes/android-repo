package com.example.test20130321_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.test20130321_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick1.setOnClickListener(this)
        binding.btnClick2.setOnClickListener(this)
        binding.btnClick3.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                Toast.makeText(applicationContext, "버튼3클릭", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnClick2.setOnClickListener {
            Toast.makeText(applicationContext, "버튼2클릭", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnClick1 -> Toast.makeText(applicationContext, "버튼1클릭", Toast.LENGTH_SHORT).show()
            R.id.btnClick2 -> Toast.makeText(applicationContext, "버튼2클릭", Toast.LENGTH_SHORT).show()
        }
    }
}