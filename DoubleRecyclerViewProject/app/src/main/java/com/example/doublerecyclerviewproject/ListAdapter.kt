package com.example.doublerecyclerviewproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doublerecyclerviewproject.databinding.ItemListBinding

class ListAdapter(val data: MutableMap<String, MutableList<String>>): RecyclerView.Adapter<ListAdapter.CustomViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
       var binding: ItemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding
        binding.tvName.text = data.keys.elementAt(position).toString()
        //recyclerView2 : adapter, LinearlayoutMagager 제공해야됨. adapter(data)
        var dataSub = MutableList<DataSub>()
        for(i in 1..4) {
            if (i % 2 == 0) {
                dataSub.add((DataSub("${data.values.elementAt(position)[i-1]}", R.drawable.face_man)))
            } else {
                dataSub.add(DataSub("${data.values.elementAt(position)[i-1]}", R.drawable.face_women))
            }
        }


        binding.recyclerview2.apply {
        adapter = ListAdapter2(dataSub)


            binding.recyclerview2.adapter = ListAdapter2(dataSub)
         binding.recyclerview2.layoutManager = LinearLayoutManager(binding.recyclerview2.context)

        }

    }



    class CustomViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)


}//end of class