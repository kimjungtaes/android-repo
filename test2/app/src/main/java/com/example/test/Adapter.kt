package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ItemMainBinding

class Adapter(val dataList: MutableList<User>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)


        viewHolder.itemView.setOnClickListener {
            val position: Int = viewHolder.adapterPosition
            val User: User = dataList.get(position)
            Toast.makeText(parent.context, "${User.tvName}", Toast.LENGTH_SHORT).show()
        }
        viewHolder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val position: Int = viewHolder.adapterPosition
                val User: User = dataList.get(position)
                (parent.context as MainActivity)
            }

        })
        return viewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = (holder as ViewHolder).binding
        binding.tvName.text = dataList[position].tvName
        binding.tvRoad.text = dataList[position].tvRoad
        binding.tvAddress.text = dataList[position].tvAddress
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
}