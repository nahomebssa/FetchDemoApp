package com.nahomebssa.fetchdemoapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.nahomebssa.fetchdemoapp.R
import com.nahomebssa.fetchdemoapp.model.Item

class ItemsAdapter(list: List<Item>): RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {


    private var mList: List<Item> = list

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var (id, listId, name) = mList[position]
        //holder.itemView.findViewById<TextView>(R.id.txtId).text = id.toString()
        //holder.itemView.findViewById<TextView>(R.id.txtListId).text = listId.toString()
        holder.itemView.findViewById<TextView>(R.id.txtName).text = name
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}