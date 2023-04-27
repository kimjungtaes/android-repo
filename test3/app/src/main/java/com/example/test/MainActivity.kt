package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter
    lateinit var dataList: MutableList<Map>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = mutableListOf()
        for (i in 1..50) {
            if (i % 2 == 0) {
                dataList.add(Map("나라${i}", "30", "이름"))
            } else {
                dataList.add(Map("world${i}", "40", "name"))
            }
        }



        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.setHasFixedSize(true)
        adapter = Adapter(dataList)
        binding.recycler.adapter = adapter
    }
}