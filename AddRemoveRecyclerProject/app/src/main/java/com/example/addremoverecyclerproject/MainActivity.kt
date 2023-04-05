package com.example.addremoverecyclerproject

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.addremoverecyclerproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnLongClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var dataList: MutableList<DataVO>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = mutableListOf<DataVO>()
        for(i in 1..30){
            if(i % 2 ==0){
                dataList.add(DataVO("홍길동${i}","${20+i}","rlaeo${i}",R.drawable.man))
            }else{
                dataList.add(DataVO("홍길녀${i}","${20+i}","rlaeo${i}",R.drawable.woman))
            }
           binding.recyclerView.adapter = CustomAdapter(dataList)
           val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
           binding.recyclerView.layoutManager =  layoutManager
           binding.recyclerView.setHasFixedSize(true)

           binding.btnFAB.setOnClickListener(this)


        }
    }
    override fun onLongClick(v: View?): Boolean {
        when(v?.id){
            R.id.btnFAB ->{
                val dialog = CustomDialog(this,binding)
                dialog.showDialog()
            }

        }
    }
    fun refreshRecyclerView(dataVO: DataVO)
    datalist.add(dataVO)
    bindig.recyclerView.adapter?.notifyDataSet

}
