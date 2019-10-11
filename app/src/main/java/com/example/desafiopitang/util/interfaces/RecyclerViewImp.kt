package com.example.desafiopitang.util.interfaces

interface RecyclerViewImpl<T> {
    fun clear()
    fun addAll(list: ArrayList<T>)
}