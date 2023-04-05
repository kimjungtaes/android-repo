package com.example.onsaveinstancestatepro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onsaveinstancestatepro.databinding.ActivityAddBinding
import com.example.onsaveinstancestatepro.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dataList: MutableList<String>
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = mutableListOf<String>()
        //1. bumdle에 data가 있으면 가져와서 기존객체참조변수에 save하고 , 없으면 무시한다.
//        if(savedInstanceState != null){
//            dataList = savedInstanceState.getStringArrayList("dataList")!!.toMutableList()
//        }

        //2. intent를 돌려받을경우
        val activityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            var result: String = it.data?.getStringExtra("result").toString()

            if(result != null && !result.equals("")){
                dataList.add(result)
            }
            myAdapter.notifyDataSetChanged()
        }

        //3. MyAdapter와 RecyclerView와 연결하고 보여줄 list 모양결정
        val laoutManager = LinearLayoutManager(this)
        myAdapter = MyAdapter(dataList)
        binding.mainRecyclerView.layoutManager = laoutManager
        binding.mainRecyclerView.adapter = myAdapter
        binding.mainRecyclerView.addItemDecoration( DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        //4. floatin tab Click하면 intent 요청
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        //5. sharedPreference save하는 방법
        binding.btnShared.setOnClickListener {
            val sharedPreferences = getSharedPreferences("dataList", Context.MODE_PRIVATE)
            val editor = sharedPreferences!!.edit()
            val gson = Gson()
            val json: String = gson.toJson(dataList)
            editor.putString("oneMessage", json)
            editor.commit()
            Toast.makeText(this, "sharedPreference 저장", Toast.LENGTH_SHORT).show()
        }
        //저장내용을 다시 복구할때
        binding.btnRevert.setOnClickListener {
            var sharedPreferences = getSharedPreferences("dataList", Context.MODE_PRIVATE)
            val data = sharedPreferences.getString("oneMessage", null)
            val type: Type = object : TypeToken<ArrayList<String>?>() {}.type
            val gson = Gson()
            dataList = gson.fromJson<Any>(data, type) as ArrayList<String>

            val laoutManager = LinearLayoutManager(this)
            myAdapter = MyAdapter(dataList)
            binding.mainRecyclerView.layoutManager = laoutManager
            binding.mainRecyclerView.adapter = myAdapter
            binding.mainRecyclerView.addItemDecoration( DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

            Toast.makeText(this, "sharedPreference 복구", Toast.LENGTH_SHORT).show()
        }

    }

    //5. 화면을 회전할경우에 기존에 내용을 bundle에다 save한다.
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putStringArrayList("dataList", ArrayList(dataList))
//    }

}//end of class