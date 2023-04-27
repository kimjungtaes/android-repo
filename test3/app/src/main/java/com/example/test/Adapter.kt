package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ItemMainBinding

class Adapter(val dataList: MutableList<Map>):
    RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

       val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       val ViewHolder =ViewHolder(binding)

        ViewHolder.itemView.setOnClickListener {
            val position: Int = ViewHolder.adapterPosition
            val map :Map = dataList.get(position)
            Toast.makeText(parent.context, "이름${map.tvName}", Toast.LENGTH_SHORT).show()

            ViewHolder.itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    val position: Int = ViewHolder.adapterPosition
                    val map : Map = dataList.get(position)
                }
            })
        }
        return ViewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val binding =(holder as ViewHolder).binding

        binding.tvName.text = dataList[position].tvName
        binding.tvAge.text = dataList[position].tvAge
        binding.tvWorld.text = dataList[position].tvWorld
    }

    override fun getItemCount(): Int = dataList.size


    class ViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

}