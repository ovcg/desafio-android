package com.example.desafiopitang.ui.adapter

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class RecyclerItemDecoration(var space : Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space + 5
        } else {
            outRect.top = space
        }

        if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
            outRect.bottom = space * 2
        }
        outRect.left = space
        outRect.right = space
    }
}