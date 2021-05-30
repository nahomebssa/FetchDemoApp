package com.nahomebssa.fetchdemoapp.util

class ListsManager<T>(list: List<T>, sort: Boolean = false) {

    private val mList = list
    private val mListOfLists = mutableListOf<List<T>>()
/*
    fun sortItems(isValid: (T) -> Boolean) {
        for (item in mList) {
            if (isValid(item)) {
                if (mListOfLists.get(item.listId) == null) {
                    mListOfLists.add(item.listId, mutableListOf())
                }
                listOfLists.get(item.listId).add(item)
            }
        }
    }
*/
    private fun placeItem() {

    }

}
/*

    inner class ItemsManager<T> {
        private val cleanList = mutableListOf<T>()
        private val listsMap = mutableMapOf<Int, MutableList<T>>()
        fun addItem() {

        }
    }

 */