package com.example.recyclyeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclyeview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var datamap: MutableMap<String, MutableList<String>>
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datamap = mutableMapOf<String, MutableList<String>>()
        for(i in 1..10){
            datamap.set("사람${i}", mutableListOf("외국인1","외국인2","외국인3","외국인4"))
        }





    }

}