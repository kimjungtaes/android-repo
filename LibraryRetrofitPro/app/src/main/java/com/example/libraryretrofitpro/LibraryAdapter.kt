package com.example.libraryretrofitpro


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryretrofitpro.data.Row
import com.example.libraryretrofitpro.databinding.ItemListBinding

class LibraryAdapter(val libraryList: MutableList<LibraryData>) :
    RecyclerView.Adapter<LibraryAdapter.RowHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val itemListBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(itemListBinding)
    }

    override fun getItemCount() = libraryList.size

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val itemListBinding = holder.itemListBinding
        itemListBinding.tvLbName.text = libraryList.get(position).code
        itemListBinding.tvGuCode.text = libraryList.get(position).name
        itemListBinding.tvAddress.text = libraryList.get(position).phone
        itemListBinding.tvTelNo.text = libraryList.get(position).adress
        itemListBinding.tvTelNo.text = libraryList.get(position).latitude
        itemListBinding.tvTelNo.text = libraryList.get(position).longitude


    }

    inner class RowHolder(val itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root) {}
}