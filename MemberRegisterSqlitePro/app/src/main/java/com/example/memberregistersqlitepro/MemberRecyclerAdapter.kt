package com.example.memberregistersqlitepro

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MemberRecyclerAdapter(val member: MutableList<Member>):RecyclerView.Adapter<MemberRecyclerAdapter.MemberHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemberRecyclerAdapter.MemberHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MemberRecyclerAdapter.MemberHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}

class MemberHolder(val itemListBinding:ItemListBinding) : RecyclerView.ViewHolder(itemListBinding)