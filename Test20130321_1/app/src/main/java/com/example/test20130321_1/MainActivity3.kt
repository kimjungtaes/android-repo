package com.example.test20130321_1

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import com.example.test20130321_1.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMain3Binding
    var pauseTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener(this)
        binding.btnStop.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnStart ->{
                binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
                binding.chronometer.start()
                binding.btnStart.isEnabled = false
                binding.btnStop.isEnabled = true
                binding.btnReset.isEnabled = true
                Toast.makeText(applicationContext, "시작합니다", Toast.LENGTH_SHORT).show()
            }
             R.id.btnStop ->{
                 pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
                 binding.chronometer.stop()
                 binding.btnStart.isEnabled = true
                 binding.btnStop.isEnabled = false
                 binding.btnReset.isEnabled = true
                 Toast.makeText(applicationContext, "중지합니다", Toast.LENGTH_SHORT).show()
             }
              R.id.btnReset ->{
                  binding.chronometer.base = SystemClock.elapsedRealtime()
                  binding.chronometer.stop()
                  pauseTime = 0L
                  binding.btnStart.isEnabled = true
                  binding.btnStop.isEnabled = false
                  binding.btnReset.isEnabled = false
                  Toast.makeText(applicationContext, "종료합니다",Toast.LENGTH_SHORT).show()
              }

        }
    }
}

