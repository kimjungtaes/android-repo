package com.example.firebasetest2304195

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasetest2304195.databinding.ActivityPictureBinding
import com.example.firebasetest2304195.databinding.PictureLayouytBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class PictureActivity : AppCompatActivity() {
    lateinit var binding: ActivityPictureBinding
    lateinit var pictureadapter: PictureAdapter
    lateinit var pictureDataList: MutableList<PictureData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)


        pictureDataList = mutableListOf()
        pictureadapter = PictureAdapter(this,pictureDataList)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = pictureadapter


        //firebase에서 실시간 데이터 베이스에서 pictureData 로딩하고 스토리지에서 이미지까지 로딩
        pictureDataLoading()

        //사진첩 등록하기
        binding.pictureRegisterBtn.setOnClickListener {
            val intent = Intent(this@PictureActivity, PictureAddActivity::class.java)
            startActivity(intent)
        }


    }

    private fun pictureDataLoading() {
        val pictureDAO = PictureDAO()
        pictureDAO.pictureSelect()?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pictureDataList.clear()
                for (data in snapshot.children){
                  val pictureData = data.getValue(PictureData::class.java)
                    if (pictureData != null){
                        pictureDataList.add(pictureData)
                    }
                }//end of for
                pictureadapter.notifyDataSetChanged()
            }

            override fun onCancelled(e: DatabaseError) {
                Toast.makeText(applicationContext, "데이터 로딩 실패", Toast.LENGTH_SHORT).show()
                Log.e("PictureActivity", "${e.message}")
            }

        })
    }
}