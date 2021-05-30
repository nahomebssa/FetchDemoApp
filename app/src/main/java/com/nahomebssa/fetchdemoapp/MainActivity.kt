package com.nahomebssa.fetchdemoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nahomebssa.fetchdemoapp.adapter.ListsAdapter
import com.nahomebssa.fetchdemoapp.model.Item
import com.nahomebssa.fetchdemoapp.repository.Repository

class MainActivity : AppCompatActivity() {

    /**
     * Use a viewmodel to store view data
     */
    private lateinit var viewModel: MainViewModel

    /**
     * Lists' RecyclerView adapter
     */
    private val listsAdapter by lazy { ListsAdapter(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            // Setup lists' RecyclerView
            val context = this
            findViewById<RecyclerView>(R.id.recyclerView).apply {
                adapter = context.listsAdapter
                layoutManager = LinearLayoutManager(context)
        }

        // Initialize the view model
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // Observe MutableLiveData to watch for changes
        viewModel.myResponse.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    listsAdapter.setData(parseData(it))
                }
            } else {
                Toast.makeText(
                    this,
                    "Could not get a response.\n\nError code ${response.code()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        // Perform network operations (getting the items)
        // TODO: Set the observer first?
        viewModel.getItems()
    }

    /**
     * Transforms a list of Items and makes a map of which
     * list an item belongs to
     * @param it the list of items
     * @return map of listId to list of Items
     */
    private fun parseData(it: List<Item>): Map<Int, List<Item>> {
        val mapOfLists = mutableMapOf<Int, MutableList<Item>>()
        for (item in it) {
            // Filter out any items where "name" is blank or null.
            val isValid = item.name != null && item.name.isNotEmpty()
            if (isValid) {
                // Display all the items grouped by "listId"
                mapOfLists.putIfAbsent(item.listId, mutableListOf())
                mapOfLists.get(item.listId)?.add(item)
            }
        }
        // Sort the results first by "listId" then by "name" when displaying.
        for (key in mapOfLists.keys) {
            mapOfLists.get(key)?.sortBy { it.name }
        }
        return mapOfLists
    }
}