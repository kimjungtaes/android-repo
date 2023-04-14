package com.example.test

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ItemMainBinding

class CustomAdapter (val dataList: MutableList<DataVO>):RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomAdapter.CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        binding.tvName.text = dataList[position].tvName
        binding.tvAge.text = dataList[position].tvAge
        binding.tvMail.text = dataList[position].tvMail
        binding.ivPicture.setImageResource(dataList.get(position).ivPicture)

    }

    override fun getItemCount(): Int  {
        return dataList.size
    }
class CustomViewHolder(val binding: ItemMainBinding) :RecyclerView.ViewHolder(binding.root)

}








