package com.example.appbarpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.appbarpro.databinding.ActivityMainBinding
import com.example.appbarpro.databinding.UsertabButtonBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var customViewpagerAdapter: CustomViewpagerAdapter
    lateinit var tabTitleList:MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customViewpagerAdapter = CustomViewpagerAdapter(this)
        customViewpagerAdapter.addListFragment(OneFragment())
        customViewpagerAdapter.addListFragment(TwoFragment())
        customViewpagerAdapter.addListFragment(ThreeFragment())
        tabTitleList = mutableListOf("car","home","air")
        binding.viewpager2.adapter = customViewpagerAdapter


        TabLayoutMediator(binding.tablayout, binding.viewpager2){tab,position ->
            tab.setCustomView(tabCustomView(position))
        }.attach()
        binding.eftb.setOnClickListener {
            Toast.makeText(applicationContext,"확장탭",Toast.LENGTH_SHORT).show()
        }

    }

    fun tabCustomView(position: Int): View {
        val binding = UsertabButtonBinding.inflate(layoutInflater)
        when(position){
            0 -> binding.ivIcon.setImageResource(R.drawable.car_24)
            1 -> binding.ivIcon.setImageResource(R.drawable.house_24)
            2 -> binding.ivIcon.setImageResource(R.drawable.airplane_24)
        }
        binding.tvTabName.text = tabTitleList.get(position)

        return binding.root
    }
}