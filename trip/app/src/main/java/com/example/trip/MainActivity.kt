package com.example.trip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var TripAdapter :TripAdapter
    lateinit var dataList: MutableList<Trip>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = mutableListOf()
        for (i in 1..50){
            if(i % 2 == 0){
            dataList.add(Trip("이름${i}","도로명주소", "지번"))
            }else{
               dataList.add(Trip("name${i}","road", "address"))
            }
        }

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.setHasFixedSize(true)
        TripAdapter = TripAdapter(dataList)
        binding.recyclerview.adapter = TripAdapter


    }
}