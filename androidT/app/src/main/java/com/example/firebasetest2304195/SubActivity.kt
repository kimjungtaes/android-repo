package com.example.firebasetest2304195

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firebasetest2304195.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivitySubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener(this)
        binding.listBtn.setOnClickListener(this)
        binding.pictureBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            //firebase data save
            R.id.add_btn -> {
                val userDao = UserDAO()
                val no = binding.noEdit.text.toString()
                val name = binding.nameEdit.text.toString()
                val age = binding.noEdit.text.toString()
                val user = User("", no, name, age)
                userDao.fbInsert(user).addOnSuccessListener {
                    Toast.makeText(this, "파이어베이스 user 등록 성공", Toast.LENGTH_SHORT).show()
                    binding.noEdit.text.clear()
                    binding.nameEdit.text.clear()
                     binding.noEdit.text.clear()
                    binding.stateText.text = "파이어 베이스 등록성공"
                }.addOnFailureListener {
                    Toast.makeText(this, "파이어베이스 user 등록 실패", Toast.LENGTH_SHORT).show()
                    binding.stateText.text = "파이어 베이스 등록실패"
                }
            }
            R.id.list_btn -> {
                val intent = Intent(this@SubActivity, ListActivity::class.java)
                startActivity(intent)
            }

            R.id.pictureBtn ->{
               val intent = Intent(this@SubActivity, PictureActivity::class.java)
                startActivity(intent)
            }
        }
    }
}