package com.example.memberregistersqlitepro

import android.content.Intent
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memberregistersqlitepro.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    lateinit var dbHelper: DBHelper
    lateinit var adapter: MemberRecyclerAdapter
    lateinit var member: Member
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(applicationContext,MainActivity.DB_NAME,MainActivity.VERSION)
//        adapter = MemberRecyclerAdapter
    }



}
