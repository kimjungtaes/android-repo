package com.example.doublerecyclerviewproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doublerecyclerviewproject.databinding.SubitemListBinding

class ListAdapter2(val data:MutableList<DataSub>):RecyclerView.Adapter<ListAdapter2.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.CustomViewHolder {
       val binding: SubitemListBinding = SubitemListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ListAdapter.CustomViewHolder, position: Int) {
      val binding = holder.binding
        binding.tvName.text = data[position].name.toString()
        binding.ivPicture.setImageResource(data[position].imadata)
            }

        }




    class CustomViewHolder(val binding: SubitemListBinding): RecyclerView.ViewHolder(binding.root)
    }
}


