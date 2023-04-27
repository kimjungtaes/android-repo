package com.example.recyclerviewtest
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.recyclerviewtest.databinding.ActivityMainBinding
import com.example.recyclerviewtest.databinding.DialogCustomBinding


class CustomDialog(val context: Context, val mainBinding: ActivityMainBinding) {

    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(onClickListener: OnDialogClickListener)
    {
        this.onClickListener = onClickListener
    }

    fun showDialog()
    {
        val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()


        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnSave.setOnClickListener {
            //onClickListener.onClicked(CustomData(binding.nameEdit.text.toString(),"kim"))
            //메인바인딩으로 하면 내부인터페이스 설계없이 바로 처리할 수 있음.
            val tvName = binding.edtName.text.toString()
            val tvAge =  binding.edtAge.text.toString()
            val tvEmail = binding.edtEmail.text.toString()
            val dataVO = DataVO(tvName,tvAge,tvEmail, R.drawable.man)
            (context as MainActivity).refreshRecyclerView(dataVO)
            dialog.dismiss()
        }
    }

    interface OnDialogClickListener
    {
        fun onClicked(dataVO: DataVO)
    }
}


