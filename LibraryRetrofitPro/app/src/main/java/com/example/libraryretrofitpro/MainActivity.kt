package com.example.libraryretrofitpro


import DBHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libraryretrofitpro.data.Library
import com.example.libraryretrofitpro.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.*

import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object

    val DB_NAME = "libraryDB.db"
    val VERSION = 1
    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper
    var mutableList: MutableList<LibraryData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //데이터 베이스 설계
        mutableList = dbHelper.selectAll()
        if (mutableList!!.size <= 0) {
            //서울 도서관 정보를 가져오는 로딩 함수
            loadLibaries()
            binding.recyclerView.adapter = LibraryAdapter(dbHelper.selectAll()!!)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        } else {

            binding.recyclerView.adapter = LibraryAdapter(mutableList!!)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }


    }//end of create

    private fun loadLibaries() {
        val retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SeoulOpenService::class.java)

        service.getLibraryres(SeoulOpenApi.API_KEY, SeoulOpenApi.LIST_TOTAL_COUNT).enqueue(object :
            Callback<Library> {
            override fun onResponse(call: Call<Library>, response: Response<Library>) {
                val Library = response.body()
                showLibrary(Library)

            }//end of onResponse

            override fun onFailure(call: Call<Library>, t: Throwable) {
                Log.e("MainActivity", "서울 도서관 공공데이터를 가져올수 없습니다.${t.printStackTrace()}")
                Toast.makeText(this@MainActivity, "서울 도서관 공공 데이터를 가져올수 없습니다", Toast.LENGTH_SHORT)
            }//end of onFailure
        })
    }//end of loadLibaries

    private fun showLibrary(library: Library?) {

        library?.let {
            for (data in it.SeoulPublicLibraryInfo.row) {
                val code = data.LBRRY_SEQ_NO
                val name = data.LBRRY_NAME
                val phone = data.TEL_NO
                val address = data.ADRES
                val latitude = data.XCNTS
                val longtude = data.YDNTS
                val libraryData = LibraryData(code, name, phone, address, latitude, longtude)
                if (dbHelper.insertTBL(libraryData)) {
                    Log.e("MainActivity", "입력 성공${libraryData.toString()}")

                } else {
                    Log.e("MainActivity", "입력 실패${libraryData.toString()}")

                }
            }
        }
    }//end of showLibrary

}//end of class




