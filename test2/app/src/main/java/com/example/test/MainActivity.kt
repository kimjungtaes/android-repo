package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var Adapter :Adapter
    lateinit var dataList: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = mutableListOf()
        for (i in 1..20){
          if(i % 2 == 0){
        dataList.add(User("이름${i}", "도로명주소", "지번"))

          }//end of if
          }//end of for

         binding.recyclerView.layoutManager = LinearLayoutManager(this)
         binding.recyclerView.setHasFixedSize(true)
         Adapter =Adapter(dataList)
         binding.recyclerView.adapter = Adapter

        }
    }
