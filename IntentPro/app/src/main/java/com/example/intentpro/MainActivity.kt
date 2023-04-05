package com.example.intentpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCallActivity2.setOnClickListener {
            //명시적 intent
            val intent: Intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("name", "홍길동")
            intent.putExtra("age", 27)
            startActivity(intent)
        }
        binding.btnCallActivity3.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("name", "잘하자")
            intent.putExtra("age", 25)
            startActivity(intent)

        }
        //데이터를 보내고 받는것까지의 과정
        binding.btnCallActivity4.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity4::class.java)
            intent.putExtra("x", 45)
            intent.putExtra("y", 23)
            intent.putExtra("operator", "+")
            startActivityForResult(intent, 20)
        }

        binding.btnCallActivity5.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity5::class.java)
            intent.putExtra("x", 25)
            intent.putExtra("y", 12)
            intent.putExtra("operator", "-")
            startActivityForResult(intent, 10)

        }
        val activityResultLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                //call back함수
                if (it.data?.getIntExtra("requestCode", 0) == 70) {
                    Toast.makeText(applicationContext, "${intent?.getIntExtra("sum", 0)}", Toast.LENGTH_SHORT).show()
                } else if(it.data?.getIntExtra("requestCode", 0) == 80){
                    Toast.makeText(applicationContext, "${intent?.getIntExtra("sum", 0)}", Toast.LENGTH_SHORT).show()
                }

            }

        binding.btnCallActivity6.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)
            intent.putExtra("x", 52)
            intent.putExtra("y", 34)
            intent.putExtra("operator", "-")
            intent.putExtra("requestCode", 70)
            activityResultLauncher.launch(intent)
        }

        binding.btnCallActivity7.setOnClickListener {
            val intent = Intent(this, MainActivity7::class.java)
            intent.putExtra("x", 52)
            intent.putExtra("y", 34)
            intent.putExtra("operator", "*")
            intent.putExtra("requestCode", 80)
            activityResultLauncher.launch(intent)


        }
    }

    //결과값을 돌려준다
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 20 && resultCode == RESULT_OK) {
            Toast.makeText(applicationContext, "${intent?.getIntExtra("sum", 0)}", Toast.LENGTH_SHORT).show()
        }
    }
}
