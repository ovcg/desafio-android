package com.example.desafiopitang.util.interfaces

interface RecyclerViewImpl<T> {
    fun clearList()
    fun addAll(list: ArrayList<T>)
}