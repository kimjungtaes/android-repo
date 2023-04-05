package com.example.intentsevensolutionpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.example.intentsevensolutionpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCallActivity.setOnClickListener(this)
        binding.btnsendData.setOnClickListener(this)
        binding.btnsendDataobject.setOnClickListener(this)
        binding.btnsendParcelObject.setOnClickListener(this)
        binding.btnsendParcelArrayList.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCallActivity -> {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
            R.id.btnsendData -> {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("id", "admin")
                intent.putExtra("pwd", "123456")
                startActivity(intent)
            }
            R.id.btnsendDataobject -> {
                val intent = Intent(this, MainActivity2::class.java)
                val loginData = LoginData("admin", "123456")
                intent.putExtra("no", 3)
                intent.putExtra("LoginData", loginData)
                startActivity(intent)
            }
            R.id.btnsendParcelObject -> {
                val intent = Intent(this, MainActivity2::class.java)
                val loginDataparcel = LoginDataparcel("admin", "123456")

                intent.putExtra("no", 4)
                intent.putExtra("loginDataParcel", loginDataparcel)
                startActivity(intent)
            }
            R.id.btnsendParcelArrayList -> {
                val intent = Intent(this, MainActivity2::class.java)
                val loginDataparcelList = arrayListOf<LoginDataparcel>()
                loginDataparcelList.add(LoginDataparcel("admin1", "123456-1"))
                loginDataparcelList.add(LoginDataparcel("admin2", "123456-2"))
                loginDataparcelList.add(LoginDataparcel("admin3", "123456-3"))
                loginDataparcelList.add(LoginDataparcel("admin4", "123456-4"))
                intent.putExtra("no", 5)
                intent.putExtra("LoginData", loginDataparcelList)
                startActivity(intent)
            }
        }
    }


}