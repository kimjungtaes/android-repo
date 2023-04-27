package com.example.trip


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trip.databinding.ItemMainBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class TripAdapter(val dataList: MutableList<Trip>):
    RecyclerView.Adapter<TripAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)


        viewHolder.itemView.setOnClickListener {
        val position: Int = viewHolder.adapterPosition
        val Trip: Trip = dataList.get(position)
            Toast.makeText(parent.context, "${Trip.tvName}", Toast.LENGTH_SHORT).show()
        }
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