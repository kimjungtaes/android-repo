package com.example.recyclerviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewtest.databinding.ItemMainBinding


class CustomAdapter(val dataList: MutableList<DataVO>) :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = CustomViewHolder(binding)

        customViewHolder.itemView .setOnClickListener {
            val position: Int = customViewHolder.adapterPosition
            val dataVO: DataVO = dataList.get(position)
            Toast.makeText(parent.context, "이름: ${dataVO.tvName}", Toast.LENGTH_SHORT).show()
        }

        customViewHolder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                val position: Int = customViewHolder.adapterPosition
                val dataVO: DataVO = dataList.get(position)
                (parent.context as MainActivity).refreshRecyclerViewDrop(dataVO)
                return true
            }

        })
        return customViewHolder
//        ================================================================
//        return CustomViewHolder(binding).apply {
//            itemView.setOnClickListener {
//                val position: Int = bindingAdapterPosition
//                val dataVO:DataVO = dataList.get(position)
//                Toast.makeText(parent.context, "이름: ${dataVO.tvName}", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        binding.tvName.text = dataList[position].tvName
        binding.tvAge.text = dataList[position].tvAge
        binding.tvMail.text = dataList[position].tvMail
        binding.ivPicture.setImageResource(dataList.get(position).ivPicture)
    }

    override fun getItemCount(): Int = dataList.size

    class CustomViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
}



