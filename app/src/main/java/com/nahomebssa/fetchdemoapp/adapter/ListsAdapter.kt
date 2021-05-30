package com.nahomebssa.fetchdemoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nahomebssa.fetchdemoapp.R
import com.nahomebssa.fetchdemoapp.model.Item

class ListsAdapter(private val innerContext: Context) :
    RecyclerView.Adapter<ListsAdapter.ViewHolder>() {

    private var mMapOfLists = mapOf<Int, List<Item>>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Determine if there is a corresponding listId for the current position
        if (mMapOfLists[position] == null)
            return

        // Use the first items listId of the list to assume which list were displaying
        // Note: the only way there's a key is if there exists an item with said listId
        val listId = mMapOfLists[position]?.get(0)?.listId
        holder.itemView.findViewById<TextView>(R.id.txtListHeader).text =
            innerContext.getString(R.string.list_header).replace("<listId>", "$listId")

        // Setup the inner RecyclerView
        holder.itemView.findViewById<RecyclerView>(R.id.listsRecyclerView).apply {
            adapter = mMapOfLists[position]?.let { ItemsAdapter(it) }
            layoutManager = LinearLayoutManager(innerContext)
        }
    }

    override fun getItemCount(): Int {
        // Find the largest list id, the binding method
        // will determine if the listId exists
        return (mMapOfLists.keys).maxOrNull() ?: 0
    }

    /**
     * Update the adapter's data and trigger observer
     */
    fun setData(newData: Map<Int, List<Item>>) {
        mMapOfLists = newData
        notifyDataSetChanged()
    }
}