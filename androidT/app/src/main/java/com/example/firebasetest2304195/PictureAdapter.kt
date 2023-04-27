package com.example.firebasetest2304195

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasetest2304195.databinding.PictureLayouytBinding

class PictureAdapter(val context: Context, val pictureList: MutableList<PictureData>):
RecyclerView.Adapter<PictureAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = PictureLayouytBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = pictureList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        val pictureData = pictureList.get(position)
        binding.tvEmail.text = pictureData.email
        binding.tvDate.text = pictureData.date
        binding.tvContent.text = pictureData.content
        //이미지는 로드가 되지않고 파이어베이스에  이미지명만 가져온 상태임. (pictureData.dotID)
        val pictureDAO = PictureDAO()
        val pictureRef = pictureDAO.storage?.reference?.child("images/${pictureData.docID}.png")
        pictureRef?.downloadUrl?.addOnCompleteListener {
            if (it.isSuccessful){
                Glide.with(context).load(it.result).into(binding.ivPicture)
            }
        }

    }
    class CustomViewHolder(val binding: PictureLayouytBinding) : RecyclerView.ViewHolder(binding.root)
}

