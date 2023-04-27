package com.example.test20130321_1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowMetrics
import com.example.test20130321_1.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)





            //화면 정보를 가져오는데 버전에 따라 명령이 다르다. API 30 버전
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                var windowMetrics : WindowMetrics = windowManager.currentWindowMetrics
                Log.e("MainActivity4", "width : ${windowMetrics.bounds.width()}"+
                 "height :${windowMetrics.bounds.height()}")
            }else{
                val display = windowManager.defaultDisplay
                val displayMetrics = DisplayMetrics()
                display?.getRealMetrics(displayMetrics)

            }
    }//end of onCreate
}