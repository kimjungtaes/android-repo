package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = MutableList<DataVO>()
        for (i in 1..30)
            if (i % 2 ==0){
                dataList.add(DataVO("임꺽정${i}","30세","schoollook585@gmail.com",R.drawable.man))
            }else{
                dataList.add(DataVO("춘향${i}","28세","biopolo30@naver.com",R.drawable.woman))
            }
    }
//    //그리드 2줄
//    val layoutManager = GridLayoutManager(this,2)
//    //그리드 가로3줄로 왼쪽부터
//    val layoutManager = GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false)
//    //그리드 가로3줄 오른쪽부터
//    val layoutManager = GridLayoutManager(this,3,GridLayoutManager,HORIZONTAL,true)
//    val layoutManager =
}