package com.example.test20130321_1

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import android.widget.Toast.Callback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.test20130321_1.databinding.ActivityMain6Binding
import com.example.test20130321_1.databinding.RegisterLayoutBinding
import com.example.test20130321_1.databinding.ToastLayoutBinding

class MainActivity6 : AppCompatActivity(), View.OnClickListener, OnDateSetListener,
    OnTimeSetListener {
    lateinit var binding: ActivityMain6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain6Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnToast.setOnClickListener(this)
        binding.btnData.setOnClickListener(this)
        binding.btnTime.setOnClickListener(this)
        binding.btnDialog.setOnClickListener(this)
        binding.btnItemDialog.setOnClickListener(this)
        binding.btnMultiItemDialog.setOnClickListener(this)
        binding.btnSingleItemDialog.setOnClickListener(this)
        binding.btnCustomDialog.setOnClickListener(this)


    }


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnToast -> {
                val toast = Toast.makeText(applicationContext, "잘해보자", Toast.LENGTH_SHORT)
                toast.duration = Toast.LENGTH_LONG
                toast.setGravity(Gravity.TOP, 0, 200)
//                toastLayout = LayoutInflater.from(applicationContext).inflate(R.layout.toast_layout,null)
//                toast.view = toastLayout.rootView
                var toastLayoutBinding: ToastLayoutBinding
                toastLayoutBinding = ToastLayoutBinding.inflate(layoutInflater)
                toast.view = toastLayoutBinding.root

                toast.show()
            }
            R.id.btnData -> {
                DatePickerDialog(this, this, 2023, 3 - 1, 23).show()
            }
            R.id.btnTime -> {
                TimePickerDialog(
                    this,
                    this, 13, 45, true
                ).show()
            }
            R.id.btnDialog -> {

                val eventeHandler = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(
                            this@MainActivity6, "${
                                if (which == -1) {
                                    "ok"
                                } else {
                                    "no"
                                }
                            }클릭하셨어요", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                AlertDialog.Builder(this).run {
                    setTitle("알림창")
                    setIcon(R.drawable.computer_24)
                    setMessage("알림창 정보를 보여드립니다")
                    setPositiveButton("Yes", eventeHandler)
                    setNegativeButton("No", eventeHandler)
                    show()

                }

            }
            R.id.btnItemDialog -> {
                var eventHandler = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        Toast.makeText(
                            this@MainActivity6, "${
                                if (which == -1) {
                                    "yes"
                                } else {
                                    "no"
                                }
                            }클릭", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                val items = arrayOf<String>("홍길동", "저길동", "구길동", "수길동")
                AlertDialog.Builder(this).run {
                    setTitle("알림창")
                    setIcon(R.drawable.computer_24)
                    setItems(items, object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            binding.btnItemDialog.text = items[which]
                            Toast.makeText(
                                applicationContext,
                                "내용 : ${items[which]}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                    setNegativeButton("No", null)
                    show()
                }
            }
            R.id.btnMultiItemDialog -> {
                val items = arrayOf<String>("홍길동", "저길동","구길동","수길동")
                AlertDialog.Builder(this).run {
                    setTitle("알림창")
                    setIcon(R.drawable.computer_24)
                    setMultiChoiceItems(items, booleanArrayOf(true,false,false,false), object: DialogInterface.OnMultiChoiceClickListener{
                        override fun onClick(
                            dialog: DialogInterface?,
                            which: Int,
                            isChecked: Boolean
                        ) {
                            if(isChecked == true){
                                binding.btnMultiItemDialog.text = items[which]
                            }
                        }

                    })
                    setPositiveButton("YES",object: DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Toast.makeText(applicationContext, "선택했습니다",Toast.LENGTH_SHORT).show()
                        }

                    })
                    setNegativeButton("Cancel",null)
                    show()


                }//end of Onclick

            }
            R.id.btnSingleItemDialog -> {
                val items = arrayOf<String>("홍길동", "저길동", "구길동", "수길동")
                AlertDialog.Builder(this).run {
                    setTitle("알림창")
                    setIcon(R.drawable.computer_24)
                    setSingleChoiceItems(items, 1, object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            binding.btnSingleItemDialog.text = items[which]
                        }
                    })


                    setNegativeButton("CLOSE", null)
                    setCancelable(false)
                    show()
                }.setCanceledOnTouchOutside(true)




                }
            R.id.btnCustomDialog -> {
                val userBinding: RegisterLayoutBinding
                val dialogBuilder = AlertDialog.Builder(this)
                var userDialog: AlertDialog
                userBinding = RegisterLayoutBinding.inflate(layoutInflater)
                dialogBuilder.setTitle("사용자 이름 입력하기 창")
                dialogBuilder.setIcon(R.drawable.computer_24)
                dialogBuilder.setView(userBinding.root)
                userDialog = dialogBuilder.create()
                userDialog.show()


               userBinding.btnCancel.setOnClickListener {
                   Toast.makeText(applicationContext,"취소되었습니다",Toast.LENGTH_SHORT).show()
               }
               userBinding.btnRegister.setOnClickListener {
                   binding.tvMessage.text = userBinding.edtName.text.toString()
                   userDialog.dismiss()
                }
               }
            R.id.btnFineLocate -> {
                val state = ContextCompat.checkSelfPermission(applicationContext,"android.permission.ACCESS_FINE_LOCATION")
                if (state == PackageManager.PERMISSION_GRANTED){
                    binding.tvMessage.text = "위치추적권한 허용"
                }else{
                    binding.tvMessage.text = "위치추적권한 불허"
                }
            }
            R.id.btnRington -> {
                //Uniform Resource Idnetifier, 리소스를 구별하는 식별자
                //안드로이드에서 URI의 역할은 리소스(외부앱, 이미지,텍스트등)에 접근할수있는 식별자역할
                val notiificationUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
               val rington = RingtoneManager.getRingtone(applicationContext,notiificationUri)
                rington.play()
            }

            }
            }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Toast.makeText(applicationContext, "${year}, ${month + 1}, ${dayOfMonth}", Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Toast.makeText(applicationContext, "${hourOfDay}시,${minute}분", Toast.LENGTH_SHORT).show()
    }
        }








