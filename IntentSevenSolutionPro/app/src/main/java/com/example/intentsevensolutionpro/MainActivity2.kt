package com.example.intentsevensolutionpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.intentsevensolutionpro.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //1. 1단계는 액티비티 전환
        //2단계는 데이터 받기 id.pwd
        if (intent.hasExtra("no")) {
            when (intent.getIntExtra("no", 0)) {
                //2단계 데이터 보내기
                0 -> {
                    if (intent.hasExtra("id").equals("") && intent.hasExtra("pwd").equals("")) {
                        Toast.makeText(this, "id와 pwd가 없어요", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        val id = intent.getStringExtra("id")
                        val pwd = intent.getStringExtra("pwd")
                        Toast.makeText(this, "${id}와 ${pwd}가 없어요", Toast.LENGTH_SHORT).show()
                    }
                }
                //3단계 데이터객체보내기()
                3 -> {
                    val loginData = intent.getSerializableExtra("LoginData") as LoginData
                    Toast.makeText(this, "${loginData.id} ${loginData.pwd}", Toast.LENGTH_SHORT)
                        .show()
                }
                4 -> {
                    val loginDataparcel = intent.getParcelableExtra<LoginDataparcel>("LoginData")
                    Toast.makeText(
                        this,
                        "${loginDataparcel?.id} ${loginDataparcel?.pwd}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                5 -> {
                    val loginDataparcelList =
                        intent.getParcelableArrayListExtra<LoginDataparcel>("loginDataParcelList")
                    Toast.makeText(
                        this,
                        "${loginDataparcelList?.get(2)?.id} ${loginDataparcelList?.get(2)?.pwd}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }

    }//end of onCreate
}