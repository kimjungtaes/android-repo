package com.example.memberregistersqlitepro

import android.content.Intent
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.memberregistersqlitepro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        val DB_NAME = "memberDB.db"
        val VERSION = 1
    }
    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DBHelper(applicationContext, MainActivity.DB_NAME, MainActivity.VERSION)
        binding.btnLogin.setOnClickListener(this)
        binding.btnSub.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnSub -> {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.btnLogin -> {
                //데이타베이스 생성을해서 SqlDatabase connection 가져온다.
                val dbHelper = DBHelper(applicationContext, MainActivity.DB_NAME, MainActivity.VERSION)
                val id = binding.edtID.text.toString()
                val password = binding.edtPWD.text.toString()
                //패턴검색 , 아이디, 패스워드 길이 체크
                if (id.isNullOrBlank() || id.isEmpty() || password.isNullOrBlank() || password.isEmpty()) {
                    Toast.makeText(this, "ID,PASSWORD 입력바람", Toast.LENGTH_SHORT).show()
                } else {
                    if (dbHelper.selectLogin(id, password)) {
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}

