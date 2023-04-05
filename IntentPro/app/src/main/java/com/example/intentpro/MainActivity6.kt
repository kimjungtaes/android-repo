package com.example.intentpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentpro.databinding.ActivityMain6Binding

class MainActivity6 : AppCompatActivity() {
    lateinit var binding: ActivityMain6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val x = intent.getIntExtra("x",-1)
        val y = intent.getIntExtra("y",-1)
        val operator = intent.getStringExtra("operator")
        var sum = 0
        when(operator){
            "+" -> sum = x + y
            "-" -> sum = x - y
            "*" -> sum = x * y
            "%" -> sum = x % y
        }
        binding.tvX.text = x.toString()
        binding.tvY.text = y.toString()
        binding.tvSUM.text = sum.toString()

        binding.btnReturnMain.setOnClickListener {
            intent.putExtra("sum",sum)
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}