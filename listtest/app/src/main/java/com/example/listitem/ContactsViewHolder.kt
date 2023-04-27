package com.example.listitem

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ContactsViewHolder (v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: Contacts) {
        view.mName.text = item.name
        view.mTel.text = item.tel
    }
}