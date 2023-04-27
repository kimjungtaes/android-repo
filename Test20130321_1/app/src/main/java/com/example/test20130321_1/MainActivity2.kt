package com.example.test20130321_1

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.test20130321_1.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMain2Binding
    var pauseTime = 0L
    var initTime = 0L
    override fun onBackPressed() {
                  if (System.currentTimeMillis() - initTime > 3000) {
                binding.tvMessage.text = getString(R.string.btn_back)
                Toast.makeText(this@MainActivity2, getString(R.string.btn_back), Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
            }else{
                super.onBackPressed()

            }
            }
//    }
            //    //백버튼을 가져올려고 한다(볼륨조절,전원,백버튼,키보드)
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//      if(keyCode == KeyEvent.KEYCODE_BACK){
//          if(System.currentTimeMillis() - initTime > 3000 ){
//              Toast.makeText(this@MainActivity2, "3초이내에 한번더 눌러주세요",Toast.LENGTH_SHORT).show()
//                  initTime = System.currentTimeMillis()
//              return true
//          }
//
//      }
//        return super.onKeyDown(keyCode, event)
//
//


            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityMain2Binding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.btnStart.setOnClickListener(this)
                binding.btnStop.setOnClickListener(this)
                binding.btnReset.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                when (v?.id) {
                    R.id.btnStart -> {
                        binding.tvMessage.text = getString(R.string.btn_start)
                        binding.tvMessage.textSize = resources.getDimension(R.dimen.text_size_small)
                        binding.tvMessage.setTextColor(ResourcesCompat.getColor(resources,R.color.txt_color2, null))
                        binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
                        binding.chronometer.start()
                        binding.btnStart.isEnabled = false
                        binding.btnStop.isEnabled = true
                        binding.btnReset.isEnabled = true
                        Toast.makeText(applicationContext, "시작합니다", Toast.LENGTH_SHORT).show()
                    }

                    R.id.btnStop -> {
                        pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
                        binding.tvMessage.textSize = resources.getDimension(R.dimen.text_size_medium)
                        binding.tvMessage.setTextColor(ResourcesCompat.getColor(resources,R.color.txt_color2, null))
                        binding.chronometer.stop()
                        binding.btnStart.isEnabled = true
                        binding.btnStop.isEnabled = false
                        binding.btnReset.isEnabled = true
                        Toast.makeText(applicationContext, "중지합니다", Toast.LENGTH_SHORT).show()
                    }
                    R.id.btnReset -> {
                        binding.tvMessage.text = getString(R.string.btn_reset)
                        binding.chronometer.base = SystemClock.elapsedRealtime()
                        binding.chronometer.stop()
                        pauseTime = 0L
                        binding.btnStart.isEnabled = true
                        binding.btnStop.isEnabled = false
                        binding.btnReset.isEnabled = false
                        Toast.makeText(applicationContext, "초기화합니다", Toast.LENGTH_SHORT).show()
                    }

                    else -> {}


                }
            }
        }


