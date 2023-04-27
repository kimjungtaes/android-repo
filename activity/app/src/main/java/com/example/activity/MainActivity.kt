package com.example.activity

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dataList:MutableList<DataList>
    lateinit var dataMap: MutableMap<String, MutableList<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataList = mutableListOf<DataList>()
        for(i in 1..30){
            if(i % 2 == 0){
                dataList.add(DataList("홍길동${i}", "${20+i}세", "rlaeo${i}@nate.com", R.drawable.man))
            }else{
                dataList.add(DataList("홍길녀${i}", "${20+i}세", "rlaeo${i}@nate.com", R.drawable.man))
            }
        }

        //2. customAdapter(dataList)
        val customAdapter = CustomAdapter(dataList)

        //3. 리사클러뷰에 어뎁터를 적용시킴
        binding.recyclerView.adapter = customAdapter
        //4. LayoutManager 통해서 리사클러뷰에 방향을 설정
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager

    }
}



