package com.example.recyclerviewtest

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var customAdapter: CustomAdapter
    lateinit var dataList: MutableList<DataVO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = mutableListOf<DataVO>()
        for (i in 1..30) {
            if (i % 2 == 0) {
                dataList.add(DataVO("홍길동${i}", "29세", "rlaeogus@nate.com", R.drawable.man))
            } else {
                dataList.add(DataVO("홍길녀${i}", "25세", "rlaeogus@nate.com", R.drawable.woman))
            }
        }

//        val layoutManager = GridLayoutManager(this, 2)  //그리드 2줄
        //그리드 가로로 3줄 왼쪽부터배치
//        val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false)
        //그리드 가로로 3줄 오른쪽부터 배치
//        val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, true)
//        val layoutManager = LinearLayoutManager(this)   //세로
//        val layoutManager = LinearLayoutManager(this)   //가로
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//          높이가 불규칙환그리드로 배치하기
//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.setHasFixedSize(true)
        customAdapter = CustomAdapter(dataList)
        binding.recyclerview.adapter = customAdapter
        binding.recyclerview.addItemDecoration(MyDecoration(this))

        binding.fab.setOnClickListener {
            val dialog = CustomDialog(this, binding)
            dialog.showDialog()
        }
    }

    fun refreshRecyclerView(dataVO: DataVO) {
        dataList.add(dataVO)
        customAdapter.notifyDataSetChanged()
        Toast.makeText(this, "추가되었어요", Toast.LENGTH_SHORT).show()
    }

    fun refreshRecyclerViewDrop(dataVO: DataVO) {
        dataList.remove(dataVO)
        customAdapter.notifyDataSetChanged()
        Toast.makeText(this, "삭제되었어요", Toast.LENGTH_SHORT).show()

//        view.setBackgroundColor(Color.parseColor("#28A0ff"))
//        ViewCompat.setElevation(view, 20.0f)
    }
}




