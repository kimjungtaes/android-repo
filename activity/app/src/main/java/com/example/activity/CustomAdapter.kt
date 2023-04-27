package com.example.activity

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.activity.databinding.ActivityMainBinding
import com.example.activity.databinding.ItemViewBinding

class CustomAdapter(val dataList: MutableList<DataList>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val itemViewBinding =  ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CustomViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val itemViewBinding = holder.itemViewBinding

        itemViewBinding.tvName.text = dataList.get(position).tvName
        itemViewBinding.tvAge.text = dataList.get(position).tvAge
        itemViewBinding.tvMail.text = dataList.get(position).tvEmail
        itemViewBinding.ivPicture.setImageResource(dataList.get(position).ivPicture)
        itemViewBinding.root.setOnClickListener {
            Toast.makeText(itemViewBinding.root.context,"${itemViewBinding.tvName.text}",Toast.LENGTH_SHORT).show()
        }

    }

    class CustomViewHolder(val itemViewBinding: ItemViewBinding):RecyclerView.ViewHolder(itemViewBinding.root)
}