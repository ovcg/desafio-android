package com.example.desafiopitang.util.interfaces

interface RecyclerViewImpl<T> {
    fun clear()
    fun addAll(list: ArrayList<T>)
    fun add(element: T)
    fun add(position: Int, element: T)
    fun remove(position: Int)
}